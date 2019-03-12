package org.github.davidcana.jcrud.storages.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.github.davidcana.jcrud.storages.StorageException;

public class JDBCStorageUtils {
	
	static private JDBCStorageUtils instance; 
	private Connection connection;
	
	private JDBCStorageUtils(){}

	public boolean exists(String field, String value, String table) throws StorageException {
		return 1 == this.count(field, value, table);
	}
	public boolean exists(String field, int value, String table) throws StorageException {
		return 1 == this.count(field, value, table);
	}
	
	public int count(String field, String value, String table) throws StorageException {

		String sql = "SELECT count(*) FROM " + table + " WHERE " + field + " = ?;";
		
		try (PreparedStatement preparedStatement = this.getConnection().prepareStatement(sql)) {
			preparedStatement.setString(1, value);
			ResultSet rs = preparedStatement.executeQuery();
			
			rs.next();
			return rs.getInt(1);

		} catch (Exception e) {
			throw new StorageException(e);
		}
	}
	
	public int count(String field, int value, String table) throws StorageException {

		String sql = "SELECT count(*) FROM " + table + " WHERE " + field + " = ?;";
		
		try (PreparedStatement preparedStatement = this.getConnection().prepareStatement(sql)) {
			preparedStatement.setInt(1, value);
			ResultSet rs = preparedStatement.executeQuery();
			
			rs.next();
			return rs.getInt(1);

		} catch (Exception e) {
			throw new StorageException(e);
		}
	}
	
	public void remove(String field, String key, String table) throws StorageException {
		
		String sql = "DELETE FROM " + table + " WHERE " + field + "=?;";
		
		try (PreparedStatement preparedStatement = this.getConnection().prepareStatement(sql)) {
			preparedStatement.setString(1, key);
			preparedStatement.executeUpdate();
			
		} catch (Exception e) {
			throw new StorageException(e);
		}
	}
	
	public void removeAll(String table) throws StorageException {
		
		String sql = "DELETE FROM " + table + " ;";
		
		try (PreparedStatement preparedStatement = this.getConnection().prepareStatement(sql)) {
			preparedStatement.executeUpdate();
			
		} catch (Exception e) {
			throw new StorageException(e);
		}
	}
	
	public Connection getConnection() throws StorageException {
		
		if (this.connection == null){
			this.connection = getNewConnection();
		}
		
		return this.connection;
	}
	
	static private Connection getNewConnection() throws StorageException {

		try {
			Class.forName("org.postgresql.Driver");
			
			return DriverManager.getConnection(
					"jdbc:postgresql://" + Constants.DB_HOST + ":" + Constants.DB_PORT + "/" + Constants.DB_NAME, 
					Constants.DB_USER,
					Constants.DB_PASSWORD);

		} catch (ClassNotFoundException e) {
			throw new StorageException(
					"Where is your PostgreSQL JDBC Driver? Include in your library path!");
			
		} catch (SQLException e) {
			throw new StorageException(
					"Connection Failed! " + e.getMessage());
		}
	}
	
	static public JDBCStorageUtils getInstance(){
		
		if ( instance == null ){
			instance = new JDBCStorageUtils();
		}
		
		return instance;
	}

}
