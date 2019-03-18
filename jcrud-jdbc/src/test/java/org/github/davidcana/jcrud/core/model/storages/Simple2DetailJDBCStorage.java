package org.github.davidcana.jcrud.core.model.storages;

import org.github.davidcana.jcrud.core.model.Simple2Detail;
import org.github.davidcana.jcrud.storages.JDBC.JDBCStorage;

public class Simple2DetailJDBCStorage extends JDBCStorage<Simple2Detail, Integer, Simple2Detail> {
	
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
