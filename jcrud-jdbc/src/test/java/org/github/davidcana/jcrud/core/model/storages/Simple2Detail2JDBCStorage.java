package org.github.davidcana.jcrud.core.model.storages;

import org.github.davidcana.jcrud.core.model.Simple2Detail2;
import org.github.davidcana.jcrud.storages.JDBC.JDBCStorage;

public class Simple2Detail2JDBCStorage extends JDBCStorage<Simple2Detail2, Integer, Simple2Detail2> {
	
	static private Simple2Detail2JDBCStorage instance; 
	
	private Simple2Detail2JDBCStorage() {
		super(Simple2JDBCStorage.getInstance());
	}

	static public Simple2Detail2JDBCStorage getInstance() {
		
		if ( instance == null ){
			instance = new Simple2Detail2JDBCStorage();
		}
		
		return instance;
	}

}
