package org.github.davidcana.jcrud.storages.JDBC;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;

import org.github.davidcana.jcrud.core.ZCrudEntity;
import org.github.davidcana.jcrud.core.requests.ListZCrudRequest;
import org.github.davidcana.jcrud.storages.StorageException;

public class DefaultFilterManager<F  extends ZCrudEntity> implements FilterManager<F> {
	
	DefaultFilterManager(){}

	@Override
	public String buildWhere(ListZCrudRequest<F> listRequest) throws StorageException {
		
		try {
			StringBuilder sb = new StringBuilder();
			
			F filter = listRequest.getFilter();
			SQLFieldGroup sqlFieldGroup = new SQLFieldGroup(filter);
			
			/*
			Map<String, Object> values = sqlFieldGroup.getValues();
			for (Map.Entry<String, Object> entry : values.entrySet()){
				String fieldName = entry.getKey();
				Object value = entry.getValue();
				this.appendPart(fieldName, value, ++c);
			}
			*/
			int c = 0;
			for (Field field : sqlFieldGroup.getFields()){
				String fieldName = field.getName();
				String sqlName = SQLFieldGroup.buildSQLName(fieldName);
				Object value = sqlFieldGroup.getValue(fieldName);
				String type = SQLFieldGroup.getType(field);
				c += this.appendPart(sb, type, sqlName, value, c);
			}
			
			return sb.length() == 0? "": "WHERE " + sb.toString();

		} catch (Exception e) {
			throw new StorageException(e);
		}
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
	public void updateStatement(ListZCrudRequest<F> listRequest, PreparedStatement preparedStatement) throws StorageException {

		try {
			F filter = listRequest.getFilter();
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
