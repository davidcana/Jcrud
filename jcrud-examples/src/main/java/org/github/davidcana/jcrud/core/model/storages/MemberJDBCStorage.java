package org.github.davidcana.jcrud.core.model.storages;

import org.github.davidcana.jcrud.core.model.Member;
import org.github.davidcana.jcrud.core.model.storages.MemberJDBCStorage;
import org.github.davidcana.jcrud.storages.JDBC.JDBCStorage;

public class MemberJDBCStorage extends JDBCStorage<Member, Integer, Member> {
	
	static private MemberJDBCStorage instance; 
	
	private MemberJDBCStorage() {
		super();
	}

	static public MemberJDBCStorage getInstance() {
		
		if ( instance == null ){
			instance = new MemberJDBCStorage();
		}
		
		return instance;
	}

}
