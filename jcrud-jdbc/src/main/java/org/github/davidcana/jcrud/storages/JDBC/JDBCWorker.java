package org.github.davidcana.jcrud.storages.JDBC;

import java.beans.IntrospectionException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.github.davidcana.jcrud.core.ZCrudEntity;
import org.github.davidcana.jcrud.core.requests.IZCrudRecords;
import org.github.davidcana.jcrud.core.requests.ZCrudRecords;
import org.github.davidcana.jcrud.storages.StorageException;

public class JDBCWorker<T extends ZCrudEntity, K, F extends ZCrudEntity> {
	
	private AbstractJDBCStorage<T, K, F> storage;
	private IZCrudRecords<T> zcrudRecords;
	
	public JDBCWorker(AbstractJDBCStorage<T, K, F> storage, IZCrudRecords<T> zcrudRecords){
		this.storage = storage;
		this.zcrudRecords = zcrudRecords;
	}

	public void run() throws StorageException, SQLException {
		
		Connection connection = null;
		List<PreparedStatement> stList = new ArrayList<>();
		
		try {
			// Get and prepare connection
			connection = JDBCStorageUtils.getInstance().getConnection();
			connection.setAutoCommit(false);
			
			// Iterate and run
			for (JDBCWorkerItem<T, K, F> worker : this.buildListOfWorkers(connection, stList)) {
				worker.run();
			}
			
			connection.commit();
			
		} catch (Exception e) {
			connection.rollback();
			throw new StorageException(e);
			
		} finally {
			for (PreparedStatement st : stList) {
				st.close();
			}
	        connection.setAutoCommit(true);
		}
	}
	
	private List<JDBCWorkerItem<T, K, F>> buildListOfWorkers(Connection connection, List<PreparedStatement> stList) throws IllegalArgumentException, IllegalAccessException, StorageException, InvocationTargetException, IntrospectionException{
		
		List<JDBCWorkerItem<T, K, F>> workerItemsList = new ArrayList<>();
		
		workerItemsList.add(
				new JDBCWorkerItem<>(this.storage, this.zcrudRecords, stList, connection));
		
		this.addWorkersOfSubformsFromExistingRecords(connection, stList, workerItemsList);
		this.addWorkersOfSubformsFromNewRecords(connection, stList, workerItemsList);
		
		return workerItemsList;
	}

	private void addWorkersOfSubformsFromExistingRecords(Connection connection, List<PreparedStatement> stList, List<JDBCWorkerItem<T, K, F>> workerItemsList) 
			throws IllegalArgumentException, IllegalAccessException, StorageException {
		
		for (Map.Entry<String, T> entry : this.zcrudRecords.getExistingRecords().entrySet()){
			T record = entry.getValue();
			String key = entry.getKey();
			this.addWorkersOfSubform(connection, stList, workerItemsList, record, false, key);
		}
	}
	
	private void addWorkersOfSubformsFromNewRecords(Connection connection, List<PreparedStatement> stList, List<JDBCWorkerItem<T, K, F>> workerItemsList) 
			throws IllegalArgumentException, IllegalAccessException, StorageException, InvocationTargetException, IntrospectionException {
		
		for (T record : this.zcrudRecords.getNewRecords()){
			//String key = record.getKey();
			String key = this.storage.getKey(record).toString();
			this.addWorkersOfSubform(connection, stList, workerItemsList, record, true, key);
		}
	}
	
	private void addWorkersOfSubform(Connection connection, List<PreparedStatement> stList, List<JDBCWorkerItem<T, K, F>> workerItemsList, T record, boolean isNew, String key) 
			throws IllegalArgumentException, IllegalAccessException, StorageException {

		SQLFieldGroup sqlFieldGroup = new SQLFieldGroup(record);
		for (Map.Entry<String, ZCrudRecords> entry : sqlFieldGroup.getLists().entrySet()){
			String fieldName = entry.getKey();
			ZCrudRecords subformZCrudRecords = entry.getValue();
			
			workerItemsList.add(
					new JDBCWorkerItem<>(
							this.storage.getSubformStorage(fieldName), 
							subformZCrudRecords, 
							stList, 
							connection,
							this.storage,
							this.castParentKey(key)
					)
			);
		}
	}
	
	private Object castParentKey(String stringValue){
		
		Field parentKeyField = this.storage.getKeyField();
		String type = SQLFieldGroup.getType(parentKeyField);
		
		return SQLFieldGroup.castStringToType(stringValue, type);
	}

}
