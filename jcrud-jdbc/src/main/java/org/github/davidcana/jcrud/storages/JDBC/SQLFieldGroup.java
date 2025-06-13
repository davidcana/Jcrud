package org.github.davidcana.jcrud.storages.JDBC;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.github.davidcana.jcrud.core.IncludesNotUpdatableFieldsSet;
import org.github.davidcana.jcrud.core.requests.ZCrudRecords;

public class SQLFieldGroup {

	private List<Field> fields;
	private Map<String,Object> values = new HashMap<>();
	private boolean updatable = false;
	private JDBCStorage<?, ?, ?> storage;
	private JDBCStorage<?, ?, ?> parentStorage;
	private Object parentKey;
	private int size = 0;
	
	private static final Map<String, Boolean> NOT_UPDATABLE_TYPES = new HashMap<String, Boolean>();
	static {
		NOT_UPDATABLE_TYPES.put("list", true);
		NOT_UPDATABLE_TYPES.put("zcrudrecords", true);
	}
	
	private static final Map<String, Boolean> SIMPLE_TYPES = new HashMap<String, Boolean>();
	static {
		SIMPLE_TYPES.put("boolean", true);
		SIMPLE_TYPES.put("date", true);
		SIMPLE_TYPES.put("float", true);
		SIMPLE_TYPES.put("integer", true);
		SIMPLE_TYPES.put("long", true);
		SIMPLE_TYPES.put("string", true);
		SIMPLE_TYPES.put("localtime", true);
		SIMPLE_TYPES.put("timestamp", true);
	}
	
	public SQLFieldGroup(Object record) throws IllegalArgumentException, IllegalAccessException {
		this.build(record);
	}
	public SQLFieldGroup(Object record, JDBCStorage<?, ?, ?> storage, JDBCStorage<?, ?, ?> parentStorage, Object parentKey) throws IllegalArgumentException, IllegalAccessException {
		
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
		
		for (Field field : getAllModelFields(record.getClass())) {
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
	
	static private List<Field> getAllModelFields(Class<?> clazz) {
		
	    List<Field> fields = new ArrayList<>();
	    do {
	    	/*
	        Collections.addAll(
	        		fields,
	        		clazz.getDeclaredFields()
	        );
	        */
	    	// Get all non static fields
	    	List<Field> temp = Arrays.stream(
	    			clazz.getDeclaredFields()
	    	).filter(
	    			f -> !Modifier.isStatic(f.getModifiers())
	    	).toList();
	    	
	    	// Add them
	    	fields.addAll(temp);
	    	
	    	// Get the superclass and continue
	        clazz = clazz.getSuperclass();
	    } while (clazz != null);
	    
	    return fields;
	}

	public boolean isUpdatable() {
		return updatable;
	}

	//(id, name)
	public String getInsertIntoNamesPart(Object object ) throws IllegalArgumentException, IllegalAccessException {
		return this.buildFieldNameList(object, false, true);
	}
	
	//(?,?)
	public String getInsertIntoArgumentPart(Object object) throws IllegalArgumentException, IllegalAccessException {
		
		StringBuilder sb = new StringBuilder("(");
		
		ReferenceInteger c = new ReferenceInteger();
		iterateFieldsForGetInsertIntoArgumentPart(
			filterFields(this.fields, object),
			this.values,
			sb,
			c
		);
		
		if (this.storage != null && this.parentStorage != null && this.parentStorage.isKeyNeeded()){
			if (c.getInteger() > 0) {
				c.inc();
				sb.append(',');
			}
			sb.append('?');
		}
		
		sb.append(')');
		return sb.toString();
	}
	/*
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
		
		if (this.storage != null && this.parentStorage != null && this.parentStorage.isKeyNeeded()){
			if (c++ > 0) {
				sb.append(',');
			}
			sb.append('?');
		}
		
		sb.append(')');
		return sb.toString();
	}
	*/
	static private void iterateFieldsForGetInsertIntoArgumentPart(List<Field> fields, Map<String,Object> values, StringBuilder sb, ReferenceInteger c) throws IllegalArgumentException, IllegalAccessException {
		
		for (int i = 0; i < fields.size(); ++i) {
			Field field = fields.get(i);
			if (! fieldIsUpdatable(field)){
				continue;
			}
			
			String type = getType(field);
			if (SIMPLE_TYPES.containsKey(type)) {
				// Simple type
				if (c.getInteger() > 0) {
					sb.append(',');
				}
				c.inc();
				sb.append('?');
				
			} else {
				// Complex type
				String fieldName = field.getName();
				Object object = values.get(fieldName);
				SQLFieldGroup sqlFieldGroup = new SQLFieldGroup(object);
				iterateFieldsForGetInsertIntoArgumentPart(
						filterFields(sqlFieldGroup.fields, object),
						sqlFieldGroup.values,
						sb,
						c
				);
			}
			
		}
	}
	
	//id=?, name=?
	public String getUpdatePart(Object record ) throws IllegalArgumentException, IllegalAccessException {		
		return this.buildFieldNameList(record, true, false);
	}
	
	private String buildFieldNameList(Object object , boolean appendEqualsPart, boolean appendParenthesis) throws IllegalArgumentException, IllegalAccessException {
		
		StringBuilder sb = new StringBuilder();
		if (appendParenthesis) {
			sb.append('(');
		}
		
		ReferenceInteger c = new ReferenceInteger();
		iterateFieldsForBuildFieldNameList(
			filterFields(this.fields, object),
			this.values,
			sb,
			appendEqualsPart,
			c,
			null
		);
		/*
		for (Field field : this.fields) {
			if (! fieldIsUpdatable(field)){
				continue;
			}

			if (c++ > 0) {
				sb.append(", ");
			}
			sb.append(
					buildSQLName(field)
			);
			if (appendEqualsPart) {
				sb.append("=?");
			}
		}
		*/
		if (this.storage != null && this.parentStorage != null && this.parentStorage.isKeyNeeded()){
			if (c.getInteger() > 0) {
				sb.append(", ");
			}
			c.inc();
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
		
		this.size = c.getInteger();
		
		return sb.toString();
	}
	
	static private void iterateFieldsForBuildFieldNameList(List<Field> fields, Map<String,Object> values, StringBuilder sb, boolean appendEqualsPart, ReferenceInteger c, String parentFieldName) throws IllegalArgumentException, IllegalAccessException {
		
		for (Field field : fields) {
			if (! fieldIsUpdatable(field)){
				continue;
			}
			String type = getType(field);
			if (SIMPLE_TYPES.containsKey(type)) {
				// Simple type
				if (c.getInteger() > 0) {
					sb.append(", ");
				}
				c.inc();
				sb.append(
						buildSQLName(field, parentFieldName)
				);
				if (appendEqualsPart) {
					sb.append("=?");
				}
			} else {
				// Complex type
				String fieldName = field.getName();
				Object record = values.get(fieldName);
				SQLFieldGroup sqlFieldGroup = new SQLFieldGroup(record);
				iterateFieldsForBuildFieldNameList(
						filterFields(sqlFieldGroup.fields, record),
						sqlFieldGroup.values,
						sb,
						appendEqualsPart,
						c,
						fieldName
				);
			}
		}
	}
	
	private String buildParentKeyFieldName() {
		return this.storage.buildParentKeyFieldName();
	}
	
	static private String buildSQLName(Field field, String parentFieldName){
		return parentFieldName == null?
				buildSQLName(field.getName()):
				parentFieldName + "_" + buildSQLName(field.getName());
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
	
	public void updateStatement(PreparedStatement preparedStatement, Object object) throws SQLException, IllegalArgumentException, IllegalAccessException {
		
		Integer c = 1;
		iterateFieldsForUpdateStatement(
				preparedStatement,
				filterFields(this.fields, object),
				this.values,
				c
		);
		/*
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
		*/
		
		if (this.storage != null && this.parentStorage != null && this.parentStorage.isKeyNeeded()){
			Field parentKeyField = this.parentStorage.getKeyField();
			String type = getType(parentKeyField);
			setValueOfStatement(preparedStatement, c++, this.parentKey, type);
		}
		
	}
	
	static private List<Field> filterFields(List<Field> fields, Object object){
		
		// Do nothing if object does not include  not updatable fields
		if (!(object instanceof IncludesNotUpdatableFieldsSet)) {
			return fields;
		}
		
		// Get the notUpdatableFields Set
		IncludesNotUpdatableFieldsSet instance = (IncludesNotUpdatableFieldsSet) object;
		Set<String> notUpdatableFields = instance.generateNotUpdatableFieldsSet();
		
		// Must iterate using an iterator as we are going to remove items
		Iterator <Field> i = fields.iterator();
		while (i.hasNext()) {
			Field field = i.next();
			if (notUpdatableFields.contains(field.getName())){
				fields.remove(field);
			}
		}
		
		return fields;
	} 
	
	static private void iterateFieldsForUpdateStatement(PreparedStatement preparedStatement, List<Field> fields, Map<String,Object> values, Integer c) throws SQLException, IllegalArgumentException, IllegalAccessException {
		
		for (Field field : fields) {
			Object value = values.get(field.getName());
			String type = getType(field);
			
			if (! fieldIsUpdatable(type)){
				continue;
			}
			
			if (SIMPLE_TYPES.containsKey(type)) {
				// Simple type
				if (setValueOfStatement(preparedStatement, c, value, type)){
					++c;
				}
			} else {
				// Complex type
				String fieldName = field.getName();
				Object record = values.get(fieldName);
				SQLFieldGroup sqlFieldGroup = new SQLFieldGroup(record);
				iterateFieldsForUpdateStatement(
						preparedStatement,
						filterFields(sqlFieldGroup.fields, record),
						sqlFieldGroup.values,
						c
				);
			}
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
	
	public Map<String, ZCrudRecords<?>> getLists() {
		
		Map<String, ZCrudRecords<?>> result = new HashMap<>();
		
		for (Field field : this.fields) {
			String type = getType(field);
			if ("zcrudrecords".equals(type)) {
				Object value = this.values.get(field.getName());
				if (value instanceof ZCrudRecords) {
					ZCrudRecords<?> zCrudRecords = (ZCrudRecords<?>) value;
					result.put(
							field.getName(),
							zCrudRecords);
				}
			}
		}
		
		return result;
	}

}
