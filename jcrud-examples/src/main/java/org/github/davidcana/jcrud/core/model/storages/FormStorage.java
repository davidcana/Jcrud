package org.github.davidcana.jcrud.core.model.storages;

import org.github.davidcana.jcrud.core.model.Form;
import org.github.davidcana.jcrud.storages.JDBC.JDBCStorage;

public class FormStorage extends JDBCStorage<Form, Integer, Form> {
	
	static private FormStorage instance;
	
	public FormStorage(){
		super();
		
		this.keyNeeded = false;
	}

	static public FormStorage getInstance() {
		
		if (instance == null){
			instance = new FormStorage();
		}
		
		return instance;
	}
}
