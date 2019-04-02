package org.github.davidcana.jcrud.storages.JDBC;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.github.davidcana.jcrud.core.ZCrudEntity;
import org.github.davidcana.jcrud.core.requests.GetZCrudRequest;
import org.github.davidcana.jcrud.core.requests.ISearchFieldData;
import org.github.davidcana.jcrud.core.requests.ListZCrudRequest;
import org.github.davidcana.jcrud.core.requests.SearchFieldData;
import org.github.davidcana.jcrud.core.requests.UpdateZCrudRequest;
import org.github.davidcana.jcrud.core.responses.GetZCrudResponse;
import org.github.davidcana.jcrud.core.responses.ListZCrudResponse;
import org.github.davidcana.jcrud.core.responses.ResponseFieldData;
import org.github.davidcana.jcrud.core.utils.CoreUtils;
import org.github.davidcana.jcrud.storages.AbstractStorage;
import org.github.davidcana.jcrud.storages.Storage;
import org.github.davidcana.jcrud.storages.StorageException;
import org.github.davidcana.jcrud.storages.StorageResolver;
import org.github.davidcana.jcrud.storages.JDBC.annotations.JDBCEntity;
import org.github.davidcana.jcrud.storages.JDBC.annotations.JDBCId;
import org.github.davidcana.jcrud.storages.JDBC.annotations.JDBCOneToMany;
import org.github.davidcana.jcrud.storages.JDBC.annotations.JDBCOrderedByDefault;

public class JDBCStorage<T extends ZCrudEntity, K, F extends ZCrudEntity> extends AbstractStorage<T, K, F> implements Storage<T, K, F> {
	
	private String tableName;
	private Field keyField;
	private String defaultOrderFieldName;
	private String defaultOrderType;
	protected Map<String, Class<? extends JDBCStorage<?, ?, ?>>> allSubformStorageClass = new HashMap<>();
	protected Map<String, JDBCOneToMany> allJDBCOneToMany = new HashMap<>();
	protected Map<String, JDBCStorage<?, ?, ?>> allSubformStorage = new HashMap<>();
	protected JDBCId jdbcId;
	protected FilterManager<F> filterManager = new DefaultFilterManager<F>();
	protected JDBCStorage<?, ?, ?> parentStorage;
	
	public JDBCStorage() {
		super();
		this.resolveAll();
	}
	protected JDBCStorage(JDBCStorage<?, ?, ?> parentStorage) {
		super();
		this.resolveAll();
		this.parentStorage = parentStorage;
	}
	
	public JDBCStorage<?, ?, ?> getParentStorage() {
		
		if (parentStorage == null){
			throw new IllegalArgumentException("parentStorage not set!");
		}
		
		return parentStorage;
	}
	public String getTableName() {
		
		if (tableName == null){
			throw new IllegalArgumentException("tableName not set in annotation!");
		}
		
		return tableName;
	}
	
	public Field getKeyField() {
		
		if (keyField == null){
			throw new IllegalArgumentException("keyField not set in annotation!");
		}
	
		return keyField;
	}
	
	public String getKeyFieldName() {
		return this.getKeyField().getName();
	}

	public String getDefaultOrderFieldName() {
		return defaultOrderFieldName;
	}

	public String getDefaultOrderType() {
		return defaultOrderType;
	}

	public JDBCId getJdbcId() {
		return jdbcId;
	}
	
	public FilterManager<F> getFilterManager() {
		return filterManager;
	}
	public void setFilterManager(FilterManager<F> filterManager) {
		this.filterManager = filterManager;
	}
	
	@Override
	protected boolean resolveClassAnnotation(Annotation annotation) {
		
	    if (annotation instanceof JDBCEntity){
	    	this.resolveJDBCEntity( (JDBCEntity) annotation);
	    	return true;
	    }
	    
	    return false;
	}
	@Override
	protected boolean resolveFieldAnnotation(Annotation annotation, Field field) {
		
	    if (annotation instanceof JDBCId){
	    	this.resolveJDBCId( (JDBCId) annotation, field);
	    	return true;
	    }
	    if (annotation instanceof JDBCOrderedByDefault){
	    	this.resolveJDBCOrderedByDefault( (JDBCOrderedByDefault) annotation, field);
	    	return true;
	    }
	    if (annotation instanceof JDBCOneToMany){
	    	this.resolveJDBCOneToMany( (JDBCOneToMany) annotation, field);
	    	return true;
	    }
	    
	    return false;
	}
	
	private void resolveJDBCEntity(JDBCEntity jdbcEntity) {
		this.tableName = jdbcEntity.table() != null? jdbcEntity.table(): SQLFieldGroup.buildSQLName(this.clazz.getSimpleName());
	}
	
	private void resolveJDBCId(JDBCId jdbcId, Field field) {
		
		this.keyField = field;
		this.jdbcId = jdbcId;
	}

	private void resolveJDBCOrderedByDefault(JDBCOrderedByDefault jdbcOrderedByDefault, Field field) {
		
		this.defaultOrderFieldName = field.getName();
		this.defaultOrderType = jdbcOrderedByDefault.type();
	}
	
	private void resolveJDBCOneToMany(JDBCOneToMany jdbcOneToMany, Field field) {
		
		Class<? extends JDBCStorage<?, ?, ?>> storageClass = jdbcOneToMany.storage();
		this.allSubformStorageClass.put(field.getName(), storageClass);
		this.allJDBCOneToMany.put(field.getName(), jdbcOneToMany);
	}
	
	/* Start JDBC methods */
	
	protected Connection getConnection() throws StorageException {
		return JDBCStorageUtils.getInstance().getConnection();
	}
	
	@Override
	public List<T> getAll() throws StorageException {
		
		String sql = "SELECT * FROM " + this.getTableName() + this.buildOrderBy() + ";";
		
		try (PreparedStatement preparedStatement = this.getConnection().prepareStatement(sql)) {
			
			ResultSet rs = preparedStatement.executeQuery();
			
			List<T> result = new ArrayList<>();
			
			while (rs.next()) {
				result.add(
						this.buildInstance(rs, false)
				);
			}
			
			return result;

		} catch (Exception e) {
			throw new StorageException(e);
		}
	}
	
	@Override
	public void fillListCRUDResponse(ListZCrudResponse<T> listCRUDResponse, ListZCrudRequest<T, K, F> listCRUDRequest) throws StorageException {
		
		try {
			long totalNumberOfRecords = this.getNumberOfRecords(listCRUDRequest);
			listCRUDResponse.setTotalNumberOfRecords(totalNumberOfRecords);
			
			List<T> records = totalNumberOfRecords == 0? new ArrayList<>(): this.getListRequestRecordsObjects(listCRUDRequest); 
			listCRUDResponse.setRecords(records);
			
		} catch (Exception e) {
			throw new StorageException(e);
		}
	}

	@Override
	public void fillGetCRUDResponse(GetZCrudResponse<T> getCRUDResponse, GetZCrudRequest<T, K, F> getRequest) throws StorageException {
		
		// Build record
		String key = getRequest.getKey();
		T record = key == null? 
				this.get(
						getRequest.getSearchFieldsData(), getCRUDResponse): 
				this.get(
						getRequest.getSearchFieldsData(), getCRUDResponse, key);
		
		// Set record to getCRUDResponse
		getCRUDResponse.setRecord(record);
	}
	
	protected T get(Map<String, SearchFieldData<F>> searchFieldsData, GetZCrudResponse<T> getCRUDResponse, String key) throws StorageException {
		
		String sql = "SELECT * FROM " + this.getTableName() + " WHERE " + this.getKeyFieldName() + "=?;";
		
		try (PreparedStatement preparedStatement = this.getConnection().prepareStatement(sql)) {
			
			this.setKey(key, 1, preparedStatement);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			T result = null;
			if (rs.next()) {
				result = this.buildInstance(rs, false);
				if (rs.next()) {
					throw new SQLException("More than one result found in table " + this.getTableName() + " using key: " + key);
				}
			} else {
				throw new SQLException("No result found in table " + this.getTableName() + " using key: " + key);
			}
			
			this.addSubformsToInstance(result, true, searchFieldsData, getCRUDResponse);
			
			return result;

		} catch ( StorageException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new StorageException(e);
		}
	}
	
	protected T get(Map<String, SearchFieldData<F>> searchFieldsData, GetZCrudResponse<T> getCRUDResponse) throws StorageException {
		
		try {
			T result = this.buildInstance();
			
			this.addSubformsToInstance(result, false, searchFieldsData, getCRUDResponse);
			
			return result;

		} catch ( StorageException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new StorageException(e);
		}
	}
	
	protected void addSubformsToInstance(T instance, boolean useKey, Map<String, SearchFieldData<F>> searchFieldsData, GetZCrudResponse<T> getCRUDResponse) throws StorageException {
		
		try {
			for (Map.Entry<String, JDBCOneToMany> entry : this.allJDBCOneToMany.entrySet()){
				String zcrudRecordsFieldName = entry.getKey();
				String fieldName = CoreUtils.getInstance().getPropertyIdFromZCrudRecordsFieldName(zcrudRecordsFieldName);
				
				// Get the list, initialize it if it is null
				List<T> list = (List<T>) getGetterValue(instance, fieldName);
				if (list == null){
					list = new ArrayList<>();
					this.setSetterValue(instance, fieldName, list);
				}
				
				// Get an instance of SearchFieldData, built it if it is null
				SearchFieldData<?> subformSearchFieldData = searchFieldsData == null? null: searchFieldsData.get(fieldName);
				if (subformSearchFieldData == null){
					subformSearchFieldData = new SearchFieldData<F>();
				}
				
				this.add1SubformToInstance(
						fieldName,
						instance, 
						this.getSubformStorage(zcrudRecordsFieldName), 
						list,
						useKey,
						subformSearchFieldData,
						getCRUDResponse
				);
			}

		} catch (Exception e) {
			throw new StorageException(e);
		}
	}
	
	protected void add1SubformToInstance(String fieldName, T instance, JDBCStorage subformStorage, List<T> list, boolean useKey, SearchFieldData<?> searchFieldData, GetZCrudResponse<T> getCRUDResponse) throws StorageException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, InstantiationException, SQLException, InvocationTargetException, IntrospectionException {
		
		list.clear();
		subformStorage.buildSubformList(
				fieldName, 
				list, 
				useKey? this.getKey(instance).toString(): null,
				useKey,
				searchFieldData,
				getCRUDResponse
		);
	}
	
	protected void setKey(String key, int pos, PreparedStatement preparedStatement) throws NumberFormatException, SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException{
		
		this.setValueOfStatement(
				this.getKeyFieldName(), 
				key, 
				pos, 
				preparedStatement);
	}
	
	private void setValueOfStatement(String fieldName, String stringValue, int pos, PreparedStatement preparedStatement) throws NumberFormatException, SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException{
		
		T targetObject = this.buildInstance();
		Field keyField = this.getField(targetObject, fieldName);
		String type = SQLFieldGroup.getType(keyField);
		Object value = SQLFieldGroup.castStringToType(stringValue, type);

		SQLFieldGroup.setValueOfStatement(preparedStatement, pos, value, type);
	}
	
	private void setValueOfStatementUsingValue(String stringValue, String type, int pos, PreparedStatement preparedStatement) throws NumberFormatException, SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException{
		
		Object value = SQLFieldGroup.castStringToType(stringValue, type);
		SQLFieldGroup.setValueOfStatement(preparedStatement, pos, value, type);
	}
	
	private String getLimitPart(ISearchFieldData<F> iSearchFieldData){
		
		StringBuilder sb = new StringBuilder();
		
		int limit = iSearchFieldData.getPageSize();
		int offset = (iSearchFieldData.getPageNumber() - 1) * limit;
		if (limit > 0) {
			sb.append("LIMIT ")
				.append(limit)
				.append(" ");

            if (offset > 0) {
            	sb.append(" OFFSET ")
            		.append(offset);
            }
        }
		
		return sb.toString();
	}

	private String buildGetRecordsSQL(ISearchFieldData<F> iSearchFieldData, String addToWhere) throws StorageException {
		
		// Order
		String orderFieldId = iSearchFieldData.getSortFieldId() != null? iSearchFieldData.getSortFieldId(): this.getDefaultOrderFieldName();
		String orderSQLFieldId = SQLFieldGroup.buildSQLName(orderFieldId);
		String orderType = iSearchFieldData.getSortType() != null? iSearchFieldData.getSortType(): this.getDefaultOrderType();
		String orderBy = orderSQLFieldId != null && orderType != null?
				" ORDER BY " + orderSQLFieldId + " " + orderType + " " + getLimitPart(iSearchFieldData):
				"";
		
		// Where
		String where = this.filterManager.buildWhere(iSearchFieldData, addToWhere);
		
		// Sample: SELECT * FROM TABLE LIMIT 10, OFFSET 20 ORDER BY id;
		return "SELECT *"
				+ " FROM " + this.getTableName()+ " " 
				+ where 
				+ orderBy 
				+ ";";
	}
	/*
	private String buildGetRecordsSQL(ListZCrudRequest<T> listRequest) throws StorageException {
		
		// Sample: SELECT * FROM TABLE LIMIT 10, OFFSET 20 ORDER BY id;
		String where = this.filterManager.buildWhere(listRequest); 
		String sqlTemplate = "SELECT * FROM %s " + where + " ORDER BY %s %s %s;";
		
		// Order
		String orderFieldId = listRequest.getSortFieldId() != null? listRequest.getSortFieldId(): this.getDefaultOrderFieldName();
		String orderType = listRequest.getSortType() != null? listRequest.getSortType(): this.getDefaultOrderType();
		
		// Build sql
		return String.format( 
				sqlTemplate, 
				this.getTableName(),
				orderFieldId,
				orderType,
				getLimitPart(listRequest));
	}*/
	
	protected List<T> getListRequestRecordsObjects(ListZCrudRequest<T, K, F> listRequest) throws StorageException {
		
		String sql = this.buildGetRecordsSQL(listRequest, null);
		
		try (PreparedStatement preparedStatement = this.getConnection().prepareStatement(sql)) {
			
			this.filterManager.updateStatement(listRequest, preparedStatement);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			List<T> result = new ArrayList<>();
			
			while ( rs.next() ) {
				result.add(
						this.buildInstance(rs, false)
				);
			}
			
			return result;

		} catch ( Exception e ) {
			throw new StorageException( e );
		}
	}
	
	@Override
	public long getNumberOfRecords(ISearchFieldData<F> listZCrudRequest) throws StorageException {
		
		// Build SQL
		String where = this.filterManager.buildWhere(listZCrudRequest, null);
		String sql = "SELECT COUNT(*) FROM " + this.getTableName() + " " + where + ";";
		
		try (PreparedStatement preparedStatement = this.getConnection().prepareStatement(sql)) {
			
			// Configure statement
			this.filterManager.updateStatement(listZCrudRequest, preparedStatement);
			
			// Execute query
			ResultSet rs = preparedStatement.executeQuery();
			rs.next();
			
			return rs.getLong(1);
			
		} catch (StorageException e) {
			throw e;
			
		} catch (Exception e) {
			throw new StorageException(e);
		}	
	}
	
	@Override
	public void doCRUD(UpdateZCrudRequest<T, K, F> updateRequest) throws StorageException {
		
		try {
			JDBCWorker<T, K, F> worker = new JDBCWorker<>(this, updateRequest);
			worker.run();
			
		} catch (SQLException e) {
			throw new StorageException(e);
		}
	}

	/*
	public void remove(String id) throws StorageException {
		JDBCStorageUtils.getInstance().remove(DEFAULT_KEY_FIELD, id, this.getTableName());
	}
	*/
	
	public JDBCStorage<?, ?, ?> getSubformStorage(String fieldName) throws StorageException {
		
		if (this.allSubformStorage.containsKey(fieldName)) {
			return this.allSubformStorage.get(fieldName);
		}
		
		Class<? extends JDBCStorage<?, ?, ?>> storageClass = this.allSubformStorageClass.get(fieldName);
		
		if (storageClass == null) {
			throw new IllegalArgumentException("Storage for field '" + fieldName + "' not set in annotation!");
		}
		
		try {
			JDBCStorage<?, ?, ?> subformStorage = (JDBCStorage<?, ?, ?>) StorageResolver.getInstance().resolve(storageClass);
			this.allSubformStorage.put(fieldName, subformStorage);
			return subformStorage;
			
		} catch (Exception e) {
			throw new StorageException(e);
		}
	}

	protected T buildInstance() throws InstantiationException, IllegalAccessException {
		return this.clazz.newInstance();
	}
	
	protected T buildInstance(ResultSet rs, boolean excludeMasterKey) throws SQLException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, InstantiationException {
		
		String masterKey = excludeMasterKey? this.buildParentKeyFieldName(): null;
		T instance = this.buildInstance();
		
		ResultSetMetaData rsmd = rs.getMetaData();
		for (int i = 1; i <= rsmd.getColumnCount(); i++){
			String sqlFieldName = rsmd.getColumnName(i);
			String fieldName = SQLFieldGroup.buildFieldName(sqlFieldName);
			if (sqlFieldName.equals(masterKey)){
				continue;
			}
			Object fieldValue = rs.getObject(i);
			this.setField(instance, fieldName, fieldValue);
		}
		
		return instance;
	}
	
	private void setField(Object targetObject, String fieldName, Object fieldValue) throws SQLException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException {
		
	    Field field = this.getField(targetObject, fieldName);
	    field.setAccessible(true);
	   
    	Object fixedValue =  SQLFieldGroup.getFieldValue(field, fieldValue);
        field.set(targetObject, fixedValue);
	}

	private Field getField(Object targetObject, String fieldName) throws NoSuchFieldException {
		
		Field field;
	    try {
	        field = targetObject.getClass().getDeclaredField(fieldName);
	    } catch (NoSuchFieldException e) {
	        field = null;
	    }
	    @SuppressWarnings("rawtypes")
		Class superClass = targetObject.getClass().getSuperclass();
	    while (field == null && superClass != null) {
	        try {
	            field = superClass.getDeclaredField(fieldName);
	        } catch (NoSuchFieldException e) {
	            superClass = superClass.getSuperclass();
	        }
	    }
	    if (field == null) {
	        throw new NoSuchFieldException("Field '" + fieldName + "' not found managing table '" +  this.getTableName() + "'!");
	    }
		return field;
	}

	protected void setMasterKey(String key, int pos, PreparedStatement preparedStatement) throws NumberFormatException, SQLException, IllegalAccessException, InstantiationException, NoSuchFieldException {
		
		this.setValueOfStatementUsingValue(
				key, 
				SQLFieldGroup.getType(
						this.getParentStorage().getKeyField()
				),
				pos, 
				preparedStatement
		);
	}
	
	public List<T> buildSubformList(String fieldName, List<T> subformList, String masterKey, boolean useMasterKey, SearchFieldData searchFieldData, GetZCrudResponse<T> getCRUDResponse) throws SQLException, StorageException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, InstantiationException {
		
		long totalNumberOfRecords = this.getNumberOfRecords(searchFieldData);
		getCRUDResponse.putResponseFieldData(
				fieldName,
				new ResponseFieldData(totalNumberOfRecords)
		);
		
		String sql = this.buildGetRecordsSQL(
				searchFieldData, 
				useMasterKey? this.buildParentKeyFieldName() + "=?": null
		);
		
		try (PreparedStatement preparedStatement = this.getConnection().prepareStatement(sql)) {

			if (useMasterKey){
				this.setMasterKey(masterKey, 1, preparedStatement);
			}
			
			this.filterManager.updateStatement(searchFieldData, preparedStatement);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				subformList.add(
						this.buildInstance(rs, useMasterKey)
				);
			}
			return subformList;
		}
	}
	
	private String buildOrderBy() {
		
		return this.getDefaultOrderFieldName() != null &&  this.getDefaultOrderType() != null?
				" ORDER BY " + this.getDefaultOrderFieldName() + " " + this.getDefaultOrderType():
				"";
	}
	
	public String buildParentKeyFieldName() {
		return this.getParentStorage().getTableName() + "_id";
	}
	public String buildKeyFieldName() {
		return this.getTableName() + "_id";
	}

	private Object getGetterValue(T instance, String fieldName) throws IllegalAccessException, InvocationTargetException, IntrospectionException {
		
		return new PropertyDescriptor(
				fieldName, 
				this.getDeserializeClass()
		).getReadMethod().invoke(instance);
	}
	private void setSetterValue(T instance, String fieldName, Object value) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		Class<? extends Object> parameterClass = value.getClass() == ArrayList.class? List.class: value.getClass();
		Method method = this.getDeserializeClass().getMethod(
			   "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1), 
			   new Class[] { 
					   parameterClass 
			   }
		);               
	    method.invoke(instance, value);
	}
	
	K getKey(T instance) throws IllegalAccessException, InvocationTargetException, IntrospectionException {
		
		return (K) this.getGetterValue(
				instance, 
				this.getKeyFieldName()
		);
	}
	
	void setKey(T instance, Object value) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		this.setSetterValue(
				instance, 
				this.getKeyFieldName(), 
				value
		);
	}
}
