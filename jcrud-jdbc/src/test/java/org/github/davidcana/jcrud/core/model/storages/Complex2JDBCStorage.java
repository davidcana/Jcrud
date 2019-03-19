package org.github.davidcana.jcrud.core.model.storages;

import org.github.davidcana.jcrud.core.model.Complex2;
import org.github.davidcana.jcrud.core.model.storages.Complex2JDBCStorage;
import org.github.davidcana.jcrud.storages.JDBC.JDBCStorage;

public class Complex2JDBCStorage extends JDBCStorage<Complex2, Integer, Complex2> {
	
	static private Complex2JDBCStorage instance; 
	
	private Complex2JDBCStorage() {
		super();
	}
	
	static public Complex2JDBCStorage getInstance() {
		
		if ( instance == null ){
			instance = new Complex2JDBCStorage();
		}
		
		return instance;
	}

}
