package org.github.davidcana.jcrud.storages.JDBC;

import org.github.davidcana.jcrud.core.model.Simple2Detail;

public class Simple2DetailJDBCStorage extends AbstractJDBCStorage<Simple2Detail, Integer, Simple2Detail> {
	
	static private Simple2DetailJDBCStorage instance; 
	
	private Simple2DetailJDBCStorage() {
		super(Simple2JDBCStorage.getInstance());
	}

	static public Simple2DetailJDBCStorage getInstance() {
		
		if ( instance == null ){
			instance = new Simple2DetailJDBCStorage();
		}
		
		return instance;
	}

}
