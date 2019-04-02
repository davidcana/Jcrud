package org.github.davidcana.jcrud.core.model.storages;

import org.github.davidcana.jcrud.core.model.VerifiedMember;
import org.github.davidcana.jcrud.core.model.filters.FormFilterManager;
import org.github.davidcana.jcrud.core.model.filters.MemberFilter;
import org.github.davidcana.jcrud.core.model.storages.VerifiedMemberJDBCStorage;
import org.github.davidcana.jcrud.storages.JDBC.JDBCStorage;

public class VerifiedMemberJDBCStorage extends JDBCStorage<VerifiedMember, Integer, MemberFilter> {
	
	static private VerifiedMemberJDBCStorage instance; 
	
	private VerifiedMemberJDBCStorage() {
		super();
		
		this.filterManager = FormFilterManager.getInstance();
	}

	static public VerifiedMemberJDBCStorage getInstance() {
		
		if ( instance == null ){
			instance = new VerifiedMemberJDBCStorage();
		}
		
		return instance;
	}

}
