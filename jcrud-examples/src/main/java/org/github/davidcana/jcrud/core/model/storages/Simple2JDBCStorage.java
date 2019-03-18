package org.github.davidcana.jcrud.core.model.storages;

import org.github.davidcana.jcrud.core.model.Simple2;
import org.github.davidcana.jcrud.core.model.storages.Simple2JDBCStorage;
import org.github.davidcana.jcrud.storages.JDBC.JDBCStorage;

public class Simple2JDBCStorage extends JDBCStorage<Simple2, Integer, Simple2> {
	
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
