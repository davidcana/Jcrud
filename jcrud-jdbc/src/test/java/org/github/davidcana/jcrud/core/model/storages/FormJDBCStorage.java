package org.github.davidcana.jcrud.core.model.storages;

import org.github.davidcana.jcrud.core.model.Form;
import org.github.davidcana.jcrud.core.model.filters.MemberFilter;
import org.github.davidcana.jcrud.storages.JDBC.JDBCStorage;

public class FormJDBCStorage extends JDBCStorage<Form, Integer, MemberFilter> {
	
	static private FormJDBCStorage instance;
	
	public FormJDBCStorage(){
		super();
		
		this.keyNeeded = false;
	}

	static public FormJDBCStorage getInstance() {
		
		if (instance == null){
			instance = new FormJDBCStorage();
		}
		
		return instance;
	}
}
