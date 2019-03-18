package org.github.davidcana.jcrud.storages.JDBC;

import java.lang.reflect.Field;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.github.davidcana.jcrud.core.requests.ZCrudRecords;

public class SQLFieldGroup {

	private List<Field> fields;
	private Map<String,Object> values = new HashMap<>();
	private boolean updatable = false;
	private JDBCStorage storage;
	private JDBCStorage parentStorage;
	private Object parentKey;
	private int size = 0;
	
	private static final Map<String, Boolean> NOT_UPDATABLE_TYPES = new HashMap<String, Boolean>();
	static {
		NOT_UPDATABLE_TYPES.put("list", true);
		NOT_UPDATABLE_TYPES.put("zcrudrecords", true);
	}
	
	public SQLFieldGroup(Object record) throws IllegalArgumentException, IllegalAccessException {
		this.build(record);
	}
	public SQLFieldGroup(Object record, JDBCStorage storage, JDBCStorage parentStorage, Object parentKey) throws IllegalArgumentException, IllegalAccessException {
		
		this(record);
		this.storage = storage;
		this.parentStorage = parentStorage;
		this.parentKey = parentKey;
	}
	
	public int size() {
		return this.size;
	}

	public List<Field> getFields() {
		return fields;
	}
	
	public Object getValue(String fieldName){
		return this.values.get(fieldName);
	}
	
	public static boolean fieldIsUpdatable(Field field){
		return fieldIsUpdatable(getType(field));
	}
	public static boolean fieldIsUpdatable(String type){
		return ! NOT_UPDATABLE_TYPES.containsKey(type);
	}
	
	private void build(Object record) throws IllegalArgumentException, IllegalAccessException {
		
		this.fields = new ArrayList<>();
		this.values = new HashMap<>();
		
		for (Field field : record.getClass().getDeclaredFields()) {
			field.setAccessible(true);
			Object value = field.get(record);
			if (value != null) {
				this.fields.add(field);
				this.values.put(field.getName(), value);
				
				String type = getType(field);
				if (! "list".equals(type) && ! "zcrudrecords".equals(type)) {
					this.updatable = true;
					this.size++;
				}
			}
		}
	}
	
	public boolean isUpdatable() {
		return updatable;
	}

	//(id, name)
	public String getInsertIntoNamesPart() {
		return this.buildFieldNameList(false, true);
	}
	
	//(?,?)
	public String getInsertIntoArgumentPart() {
		
		StringBuilder sb = new StringBuilder("(");
		int c = 0;
		for (int i = 0; i < this.fields.size(); ++i) {
			Field field = this.fields.get(i); 
			if (! fieldIsUpdatable(field)){
				continue;
			}
			if (c++ > 0) {
				sb.append(',');
			}
			sb.append('?');
		}
		
		if (this.storage != null){
			if (c++ > 0) {
				sb.append(',');
			}
			sb.append('?');
		}
		
		sb.append(')');
		return sb.toString();
	}
	
	//id=?, name=?
	public String getUpdatePart() {		
		return this.buildFieldNameList(true, false);
	}
	
	private String buildFieldNameList(boolean appendEqualsPart, boolean appendParenthesis) {
		
		StringBuilder sb = new StringBuilder();
		if (appendParenthesis) {
			sb.append('(');
		}
		
		int c = 0;
		for (Field field : this.fields) {
			if (! fieldIsUpdatable(field)){
				continue;
			}
			if (c++ > 0) {
				sb.append(", ");
			}
			sb.append(
					buildSQLName(field));
			if (appendEqualsPart) {
				sb.append("=?");
			}
		}
		
		if (this.storage != null){
			if (c++ > 0) {
				sb.append(", ");
			}
			sb.append(
					this.buildParentKeyFieldName()
			);
			if (appendEqualsPart) {
				sb.append("=?");
			}
		}
		
		if (appendParenthesis) {
			sb.append(')');
		}
		
		return sb.toString();
	}
	
	private String buildParentKeyFieldName() {
		return this.storage.buildParentKeyFieldName();
	}
	
	static private String buildSQLName(Field field){
		return buildSQLName(field.getName());
	}

	public static String buildSQLName(String name) {
		
		StringBuilder sb = new StringBuilder();
		
		final CharacterIterator it = new StringCharacterIterator(name);
		for (char c = it.first(); c != CharacterIterator.DONE; c = it.next()) {
			if (Character.isUpperCase(c)){
				sb.append('_').append(Character.toLowerCase(c));
			} else {
				sb.append(c);
			}
		}
		
		return sb.toString();
	}

	static public String buildFieldName(String sqlFieldName){
		
		StringBuilder sb = new StringBuilder();
		
		boolean toUpperCase = false;
		final CharacterIterator it = new StringCharacterIterator(sqlFieldName);
		for (char c = it.first(); c != CharacterIterator.DONE; c = it.next()) {
			if (c == '_'){
				toUpperCase = true;
				continue;
			}
			if (toUpperCase){
				c = Character.toUpperCase(c);
				toUpperCase = false;
			}
			sb.append(c);
		}
		
		return sb.toString();
	}

	/*
	static public String getType(Field field) {
		
		String fullType = field.getType().getName().toLowerCase();
		int i = fullType.lastIndexOf('.');
		return i == -1? fullType: fullType.substring(1 + i);
	}
	*/
	static public String getType(Field field) {
		return getType(field.getType());
	}
	static public String getType(Class<?> clazz) {
		
		String fullType = clazz.getName().toLowerCase();
		int i = fullType.lastIndexOf('.');
		return i == -1? fullType: fullType.substring(1 + i);
	}
	
	public void updateStatement(PreparedStatement preparedStatement) throws SQLException, IllegalArgumentException {
		
		int c = 1;
		for (Field field : this.fields) {
			Object value = this.values.get(field.getName());
			String type = getType(field);
			
			if (! fieldIsUpdatable(type)){
				continue;
			}
			if (setValueOfStatement(preparedStatement, c, value, type)){
				++c;
			}
		}
		
		if (this.storage != null){
			Field parentKeyField = this.parentStorage.getKeyField();
			String type = getType(parentKeyField);
			Class<?> parentClass = parentKeyField.getClass();
			setValueOfStatement(preparedStatement, c++, this.parentKey, type);
		}
		
	}

	static public boolean setValueOfStatement(PreparedStatement preparedStatement, int pos, Object value, String type) throws SQLException {
		
		switch (type) {
		case "boolean":
			preparedStatement.setBoolean(pos, (Boolean) value);
			return true;
		case "date":
			preparedStatement.setDate(pos, (Date) value);
			return true;
		case "float":
			preparedStatement.setFloat(pos, (Float) value);
			return true;
		case "integer":
			preparedStatement.setInt(pos, (Integer) value);
			return true;
		case "long":
			preparedStatement.setLong(pos, (Long) value);
			return true;
		case "string":
			preparedStatement.setString(pos, (String) value);
			return true;
		case "localtime":
			preparedStatement.setTime(pos, Time.valueOf((LocalTime) value));
			return true;
		case "timestamp":
			preparedStatement.setTimestamp(pos, (Timestamp) value);
			return true;
		case "list":
		case "zcrudrecords":
			// Ignore lists and ZCrudRecords
			return false;
		default:
			throw new IllegalArgumentException("Type " + type + " not allowed in SQLfieldGroup!");
		}
	}
	
	static public Object castStringToType(String string, String type) {
		
		switch (type) {
		case "boolean":
			return Boolean.valueOf(string);
		case "date":
			return Date.valueOf(string);
		case "float":
			return Float.valueOf(string);
		case "integer":
			return Integer.valueOf(string);
		case "long":
			return Long.valueOf(string);
		case "string":
			return string;
		case "localtime":
			return LocalTime.parse(string, null);
		case "timestamp":
			return Timestamp.valueOf(string);
		default:
			throw new IllegalArgumentException("Type " + type + " not allowed in SQLfieldGroup!");
		}
	}
	
	static public Object getFieldValue(Field field, Object value) throws SQLException {
		
		String type = getType(field);
		switch (type) {
			case "boolean":
			case "date":
			case "float":
			case "integer":
			case "long":
			case "string":
			case "timestamp":
				return value;
			case "localtime":
				return ((Time) value).toLocalTime();
			//case "list":
			//case "zcrudrecords":
			default:
				throw new IllegalArgumentException("Type " + type + " not allowed in SQLfieldGroup!");
		}
	}
	
	public Map<String, ZCrudRecords> getLists() {
		
		Map<String, ZCrudRecords> result = new HashMap<>();
		
		for (Field field : this.fields) {
			String type = getType(field);
			if ("zcrudrecords".equals(type)) {
				Object value = this.values.get(field.getName());
				if (value instanceof ZCrudRecords) {
					ZCrudRecords zCrudRecords = (ZCrudRecords) value;
					result.put(
							field.getName(),
							zCrudRecords);
				}
			}
		}
		
		return result;
	}

}
