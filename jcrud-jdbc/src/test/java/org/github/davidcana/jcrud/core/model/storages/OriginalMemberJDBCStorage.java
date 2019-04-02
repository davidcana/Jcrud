package org.github.davidcana.jcrud.core.model.storages;

import org.github.davidcana.jcrud.core.model.OriginalMember;
import org.github.davidcana.jcrud.core.model.filters.FormFilterManager;
import org.github.davidcana.jcrud.core.model.filters.MemberFilter;
import org.github.davidcana.jcrud.storages.JDBC.JDBCStorage;

public class OriginalMemberJDBCStorage extends JDBCStorage<OriginalMember, Integer, MemberFilter> {
	
	static private OriginalMemberJDBCStorage instance; 
	
	private OriginalMemberJDBCStorage() {
		super();
		
		this.filterManager = FormFilterManager.getInstance();
	}

	static public OriginalMemberJDBCStorage getInstance() {
		
		if ( instance == null ){
			instance = new OriginalMemberJDBCStorage();
		}
		
		return instance;
	}

}
