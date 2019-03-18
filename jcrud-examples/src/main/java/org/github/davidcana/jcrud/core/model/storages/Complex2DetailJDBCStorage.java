package org.github.davidcana.jcrud.core.model.storages;

import org.github.davidcana.jcrud.core.model.Complex2Detail;
import org.github.davidcana.jcrud.core.model.storages.Complex2DetailJDBCStorage;
import org.github.davidcana.jcrud.storages.JDBC.JDBCStorage;

public class Complex2DetailJDBCStorage extends JDBCStorage<Complex2Detail, Integer, Complex2Detail> {
	
	static private Complex2DetailJDBCStorage instance; 
	
	private Complex2DetailJDBCStorage() {
		super(Complex2JDBCStorage.getInstance());
	}

	static public Complex2DetailJDBCStorage getInstance() {
		
		if ( instance == null ){
			instance = new Complex2DetailJDBCStorage();
		}
		
		return instance;
	}

}
