package org.github.davidcana.jcrud.core.model.storages;

import org.github.davidcana.jcrud.core.model.Secondary;
import org.github.davidcana.jcrud.core.model.storages.SecondaryJDBCStorage;
import org.github.davidcana.jcrud.storages.JDBC.JDBCStorage;

public class SecondaryJDBCStorage extends JDBCStorage<Secondary, Integer, Secondary> {
	
	static private SecondaryJDBCStorage instance; 
	
	private SecondaryJDBCStorage() {
		super();
	}
	
	static public SecondaryJDBCStorage getInstance() {
		
		if (instance == null){
			instance = new SecondaryJDBCStorage();
		}
		
		return instance;
	}

}
