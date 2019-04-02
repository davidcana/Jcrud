package org.github.davidcana.jcrud.storages.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.github.davidcana.jcrud.core.ZCrudEntity;
import org.github.davidcana.jcrud.core.requests.IZCrudRecords;
import org.github.davidcana.jcrud.core.utils.RandomGenerator;
import org.github.davidcana.jcrud.storages.StorageException;
import org.github.davidcana.jcrud.storages.JDBC.annotations.JDBCId;

public class JDBCWorkerItem<T extends ZCrudEntity, K, F extends ZCrudEntity> {
	
	private JDBCStorage<T, K, F> storage;
	private IZCrudRecords<T> zcrudRecords;
	private List<PreparedStatement> stList; 
	private Connection connection;
	private JDBCStorage<?, ?, ?> parentStorage;
	private Object parentKey;
	
	public JDBCWorkerItem(JDBCStorage<T, K, F> storage, IZCrudRecords<T> zcrudRecords, List<PreparedStatement> stList, Connection connection) throws StorageException {
		
		this.storage = storage;
		this.zcrudRecords = zcrudRecords;
		this.stList = stList;
		this.connection = connection;
		
		this.generateIds();
	}
	
	public JDBCWorkerItem(JDBCStorage<T, K, F> storage, IZCrudRecords<T> zcrudRecords, List<PreparedStatement> stList, Connection connection, JDBCStorage<?, ?, ?> parentStorage, Object parentKey) throws StorageException {
		
		this(storage, zcrudRecords, stList, connection);
		this.parentStorage = parentStorage;
		this.parentKey = parentKey;
	}
	
	private void generateIds() throws StorageException {
		
		try {
			JDBCId jdbcId = this.storage.getJdbcId();
			if (jdbcId == null || !jdbcId.generate()){
				return;
			}
			
			for (T record : this.zcrudRecords.getNewRecords()) {
				this.storage.setKey(
						record,
						RandomGenerator.getInstance().generateString()
				);
			}
		} catch (Exception e) {
			throw new StorageException(e);
		} 
	}
	
	public void run() throws IllegalArgumentException, IllegalAccessException, SQLException, InstantiationException, NoSuchFieldException {
		
		this.doNewRecords();
		this.doExistingRecords();
		this.doRecordsToRemove();
	};
	
	private void doRecordsToRemove() throws SQLException, NumberFormatException, InstantiationException, IllegalAccessException, NoSuchFieldException {

		List<String> recordsToRemove = this.zcrudRecords.getRecordsToRemove();
		for (String key : recordsToRemove) {
			String sql = "DELETE FROM " + this.storage.getTableName() + " WHERE " + this.storage.getKeyFieldName() + "=?;";
			PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
			this.stList.add(preparedStatement);

			this.storage.setKey(
					key, 
					1, 
					preparedStatement);
			preparedStatement.executeUpdate();
		}
	}

	private void doExistingRecords() throws SQLException, IllegalArgumentException, IllegalAccessException, InstantiationException, NoSuchFieldException {
		
		Map<String,T> existingRecords = this.zcrudRecords.getExistingRecords();
		for (Entry<String, T> entry : existingRecords.entrySet()){
			String currentId = entry.getKey();
			ZCrudEntity record = entry.getValue();
			
			SQLFieldGroup sqlFieldGroup = new SQLFieldGroup(record);
			if (! sqlFieldGroup.isUpdatable()) {
				continue;
			}
			
			String sql = "UPDATE " + this.storage.getTableName()
				+ " SET " + sqlFieldGroup.getUpdatePart() + " WHERE " + this.storage.getKeyFieldName() + "=?;";
			PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
			
			this.stList.add(preparedStatement);
			sqlFieldGroup.updateStatement(preparedStatement);

			this.storage.setKey(
					currentId, 
					sqlFieldGroup.size() + 1, 
					preparedStatement);
			preparedStatement.executeUpdate();
		}
	}

	private void doNewRecords() throws SQLException, IllegalArgumentException, IllegalAccessException {
		
		List<T> newRecords = this.zcrudRecords.getNewRecords();
		for (ZCrudEntity record : newRecords) {
			
			SQLFieldGroup sqlFieldGroup = new SQLFieldGroup(record, this.storage, this.parentStorage, this.parentKey);
			if (! sqlFieldGroup.isUpdatable()) {
				continue;
			}
			
			String sql = "INSERT INTO " + this.storage.getTableName()
				+ " " 
				//+ "(id, name)"
				+ sqlFieldGroup.getInsertIntoNamesPart()
				+ " VALUES "
				//+ "(?,?)"
				+ sqlFieldGroup.getInsertIntoArgumentPart()
				+ ";";
			PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
			
			this.stList.add(preparedStatement);
			sqlFieldGroup.updateStatement(preparedStatement);
			
			preparedStatement.executeUpdate();
		}
	}

}
