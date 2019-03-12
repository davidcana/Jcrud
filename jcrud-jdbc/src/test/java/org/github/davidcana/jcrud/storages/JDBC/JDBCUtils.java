package org.github.davidcana.jcrud.storages.JDBC;

import java.io.IOException;

import org.github.davidcana.jcrud.storages.StorageException;

public class JDBCUtils {
	
	static private JDBCUtils instance; 
	
	private JDBCUtils(){}
	
	public void executeSqlFile(String scriptPath) throws IOException, InterruptedException, StorageException {
		
        Runtime rt = Runtime.getRuntime();
        String command = "psql -f " + scriptPath + " " + Constants.DB_NAME;
        Process pr = rt.exec(command);
        int exitVal = pr.waitFor();
        if (exitVal != 0){
        	throw new StorageException("Command '" + command + "' exited with error code " + exitVal);
        }
	}
	
	static public JDBCUtils getInstance(){
		
		if ( instance == null ){
			instance = new JDBCUtils();
		}
		
		return instance;
	}

}
