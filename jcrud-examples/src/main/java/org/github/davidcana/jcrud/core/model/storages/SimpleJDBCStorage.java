package org.github.davidcana.jcrud.core.model.storages;

import org.github.davidcana.jcrud.core.model.Simple;
import org.github.davidcana.jcrud.core.model.storages.SimpleJDBCStorage;
import org.github.davidcana.jcrud.storages.JDBC.JDBCStorage;

public class SimpleJDBCStorage extends JDBCStorage<Simple, Integer, Simple> {
	
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
