package org.github.davidcana.jcrud.storages.JDBC;

import org.github.davidcana.jcrud.core.model.Simple;

public class SimpleJDBCStorage extends AbstractJDBCStorage<Simple, Integer, Simple> {
	
	static private SimpleJDBCStorage instance; 
	
	private SimpleJDBCStorage() {
		super();
	}

	static public SimpleJDBCStorage getInstance() {
		
		if ( instance == null ){
			instance = new SimpleJDBCStorage();
		}
		
		return instance;
	}

}
