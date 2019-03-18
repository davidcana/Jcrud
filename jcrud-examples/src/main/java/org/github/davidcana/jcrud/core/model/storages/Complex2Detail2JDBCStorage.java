package org.github.davidcana.jcrud.core.model.storages;

import org.github.davidcana.jcrud.core.model.Complex2Detail2;
import org.github.davidcana.jcrud.core.model.storages.Complex2Detail2JDBCStorage;
import org.github.davidcana.jcrud.storages.JDBC.JDBCStorage;

public class Complex2Detail2JDBCStorage extends JDBCStorage<Complex2Detail2, Integer, Complex2Detail2> {
	
	static private Complex2Detail2JDBCStorage instance; 
	
	private Complex2Detail2JDBCStorage() {
		super(Complex2JDBCStorage.getInstance());
	}

	static public Complex2Detail2JDBCStorage getInstance() {
		
		if ( instance == null ){
			instance = new Complex2Detail2JDBCStorage();
		}
		
		return instance;
	}

}
