package org.github.davidcana.jcrud.core.model.storages;

import org.github.davidcana.jcrud.core.model.Complex;
import org.github.davidcana.jcrud.storages.JDBC.JDBCStorage;

public class ComplexJDBCStorage extends JDBCStorage<Complex, Integer, Complex> {
	
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
