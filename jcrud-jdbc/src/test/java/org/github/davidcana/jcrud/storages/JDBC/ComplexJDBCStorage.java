package org.github.davidcana.jcrud.storages.JDBC;

import org.github.davidcana.jcrud.core.model.Complex;

public class ComplexJDBCStorage extends AbstractJDBCStorage<Complex, Integer, Complex> {
	
	static private ComplexJDBCStorage instance; 
	
	private ComplexJDBCStorage() {
		super();
	}
	
	static public ComplexJDBCStorage getInstance() {
		
		if ( instance == null ){
			instance = new ComplexJDBCStorage();
		}
		
		return instance;
	}

}
