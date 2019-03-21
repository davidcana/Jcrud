package org.github.davidcana.jcrud.core.model.storages;

import org.github.davidcana.jcrud.core.model.Form;
import org.github.davidcana.jcrud.core.model.OriginalMember;
import org.github.davidcana.jcrud.storages.JDBC.JDBCStorage;

public class OriginalMemberJDBCStorage extends JDBCStorage<OriginalMember, Integer, Form> {
	
	static private OriginalMemberJDBCStorage instance; 
	
	private OriginalMemberJDBCStorage() {
		super();
	}

	static public OriginalMemberJDBCStorage getInstance() {
		
		if ( instance == null ){
			instance = new OriginalMemberJDBCStorage();
		}
		
		return instance;
	}

}
