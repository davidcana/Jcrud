package org.github.davidcana.jcrud.storages.JDBC;

import org.github.davidcana.jcrud.core.model.Simple2;

public class Simple2JDBCStorage extends AbstractJDBCStorage<Simple2, Integer, Simple2> {
	
	static private Simple2JDBCStorage instance; 
	
	private Simple2JDBCStorage() {
		super();
	}
	
	static public Simple2JDBCStorage getInstance() {
		
		if ( instance == null ){
			instance = new Simple2JDBCStorage();
		}
		
		return instance;
	}

}
