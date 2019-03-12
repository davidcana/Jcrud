package org.github.davidcana.jcrud.storages.JDBC;

import org.github.davidcana.jcrud.core.model.Secondary;

public class SecondaryJDBCStorage extends AbstractJDBCStorage<Secondary, Integer, Secondary> {
	
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
