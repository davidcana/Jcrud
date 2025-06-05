package org.github.davidcana.jcrud.core.model.storages;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.github.davidcana.jcrud.core.model.File;
import org.github.davidcana.jcrud.core.model.SimpleWithFile;
import org.github.davidcana.jcrud.core.model.storages.SimpleWithFileJDBCStorage;
import org.github.davidcana.jcrud.storages.JDBC.JDBCStorage;
import org.github.davidcana.jcrud.storages.JDBC.SQLFieldGroup;

public class SimpleWithFileJDBCStorage extends JDBCStorage<SimpleWithFile, Integer, SimpleWithFile> {
	
	static private SimpleWithFileJDBCStorage instance; 
	
	private SimpleWithFileJDBCStorage() {
		super();
	}
	
	protected SimpleWithFile buildInstance(ResultSet rs, boolean excludeMasterKey) throws SQLException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, InstantiationException {
		
		String filePrefix = "file_";
		String masterKey = excludeMasterKey? this.buildParentKeyFieldName(): null;
		SimpleWithFile instance = this.buildInstance();
		
		ResultSetMetaData rsmd = rs.getMetaData();
		for (int i = 1; i <= rsmd.getColumnCount(); i++){
			String sqlFieldName = rsmd.getColumnName(i);
			Object fieldValue = rs.getObject(i);
			
			if (!sqlFieldName.startsWith(filePrefix)) {
				// Ordinary fields (not related to file)
				String fieldName = SQLFieldGroup.buildFieldName(sqlFieldName);
				if (sqlFieldName.equals(masterKey)){
					continue;
				}				
				this.setField(instance, fieldName, fieldValue);
				
			} else {
				// Do not instance an empty File instance
				if (fieldValue == null) {
					continue;
				}
				
				// Related to file field
				File file = instance.getFile();
				
				// Create a File instance if needed
				if (file == null) {
					file = new File();
					instance.setFile(file);
				}
				
				String fieldName = SQLFieldGroup.buildFieldName(
						sqlFieldName.substring(filePrefix.length())
				);
				this.setField(file, fieldName, fieldValue);
			}
		}
		
		return instance;
	}
	
	static public SimpleWithFileJDBCStorage getInstance() {
		
		if ( instance == null ){
			instance = new SimpleWithFileJDBCStorage();
		}
		
		return instance;
	}

}
