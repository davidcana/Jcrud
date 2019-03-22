package org.github.davidcana.jcrud.storages.JDBC;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;

import org.github.davidcana.jcrud.core.ZCrudEntity;
import org.github.davidcana.jcrud.core.requests.ISearchFieldData;
import org.github.davidcana.jcrud.storages.StorageException;

public class DefaultFilterManager<F  extends ZCrudEntity> implements FilterManager<F> {
	
	DefaultFilterManager(){}

	@Override
	public String buildWhere(ISearchFieldData<F> iSearchFieldData, String addToWhere) throws StorageException {
		
		try {
			StringBuilder sb = new StringBuilder();
			
			F filter = iSearchFieldData.getFilter();
			if (filter != null){
				SQLFieldGroup sqlFieldGroup = new SQLFieldGroup(filter);
				
				int c = 0;
				for (Field field : sqlFieldGroup.getFields()){
					String fieldName = field.getName();
					String sqlName = SQLFieldGroup.buildSQLName(fieldName);
					Object value = sqlFieldGroup.getValue(fieldName);
					String type = SQLFieldGroup.getType(field);
					c += this.appendPart(sb, type, sqlName, value, c);
				}
			}
			
			//return sb.length() == 0? "": "WHERE " + sb.toString();
			return add(sb.toString(), addToWhere);
			
		} catch (Exception e) {
			throw new StorageException(e);
		}
	}
	
	static protected String add(String sb, String addToWhere){
		
		if (sb.length() == 0 && addToWhere == null){
			return "";
		}
		
		if (addToWhere == null){
			return "WHERE " + sb.toString();
		}
		
		if (sb.length() == 0){
			return "WHERE " + addToWhere;
		}
		
		return "WHERE " + sb.toString() + " AND " + addToWhere;
	}
	
	protected int appendPart(StringBuilder sb, String type, String sqlName, Object value, Integer c) {
		
		String operator = " AND ";
		
		switch (type) {
		case "string":
			this.appendOperator(sb, c, operator);
			sb.append(sqlName + " LIKE ?");
			return 1;
		case "boolean":
		case "date":
		case "localtime":
		case "timestamp":
			this.appendOperator(sb, c, operator);
			sb.append(sqlName + " = '?'");
			return 1;
		case "float":
		case "integer":
		case "long":
			this.appendOperator(sb, c, operator);
			sb.append(sqlName + " = ?");
			return 1;
		case "list":
		case "zcrudrecords":
			return 0;
		default:
			throw new IllegalArgumentException("Type " + type + " not allowed in filter!");
		}
	}
	
	protected void appendOperator(StringBuilder sb, Integer c, String operator) {
		
		if (c > 0){
			sb.append(operator);
		}
	};
	
	@Override
	public void updateStatement(ISearchFieldData<F> iSearchFieldData, PreparedStatement preparedStatement) throws StorageException {

		try {
			F filter = iSearchFieldData.getFilter();
			if (filter == null){
				return;
			}
			SQLFieldGroup sqlFieldGroup = new SQLFieldGroup(filter);
			int pos = 0;
			for (Field field : sqlFieldGroup.getFields()){
				String fieldName = field.getName();
				Object value = sqlFieldGroup.getValue(fieldName);
				String type = SQLFieldGroup.getType(field);
				SQLFieldGroup.setValueOfStatement(
						preparedStatement, 
						++pos, 
						type.equals("string")? "%" + value + "%": value, 
						type
				);
			}
			
		} catch (Exception e) {
			throw new StorageException(e);
		}
	}

}
