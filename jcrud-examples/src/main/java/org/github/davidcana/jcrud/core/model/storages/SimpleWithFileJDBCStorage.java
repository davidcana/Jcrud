package org.github.davidcana.jcrud.core.model.storages;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.github.davidcana.jcrud.core.File;
import org.github.davidcana.jcrud.core.model.SimpleWithFile;
import org.github.davidcana.jcrud.core.model.storages.SimpleWithFileJDBCStorage;
import org.github.davidcana.jcrud.servlets.DownloadFileServlet;
import org.github.davidcana.jcrud.storages.JDBC.JDBCStorage;
import org.github.davidcana.jcrud.storages.JDBC.SQLFieldGroup;

public class SimpleWithFileJDBCStorage extends JDBCStorage<SimpleWithFile, Integer, SimpleWithFile> {

	private static final String FILE_FIELD = "file";
	private static final boolean TREAT_FILE_CONTENTS_AS_NORMAL_FIELD = false;
	
	static private SimpleWithFileJDBCStorage instance; 
	
	private SimpleWithFileJDBCStorage() {
		super();
	}
	
	protected SimpleWithFile buildInstance(ResultSet rs, boolean excludeMasterKey) throws SQLException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, InstantiationException {
		
		String filePrefix = FILE_FIELD + "_";
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
				
				// Do not set contents field if configured 
				if (!"contents".equals(fieldName) || this.treatFileContentsAsNormalField()) {
					this.setField(file, fieldName, fieldValue);
				}
			}
		}
		
		// Set the URL of the file if there is a file and contents was not added to file
		File file = instance.getFile();
		if (file != null && !this.treatFileContentsAsNormalField()) {
			file.setUrl(
					DownloadFileServlet.buildDownloadURL(
							"simpleWithFile",
							instance.getId(),
							FILE_FIELD
					)
			);
		}
		
		return instance;
	}
	
	@Override
	public boolean treatFileContentsAsNormalField() {
		return TREAT_FILE_CONTENTS_AS_NORMAL_FIELD;
	}
	
	static public SimpleWithFileJDBCStorage getInstance() {
		
		if ( instance == null ){
			instance = new SimpleWithFileJDBCStorage();
		}
		
		return instance;
	}

}
