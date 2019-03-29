package org.github.davidcana.jcrud.core.model.storages;

import org.github.davidcana.jcrud.core.model.Member;
import org.github.davidcana.jcrud.core.model.OriginalMember;
import org.github.davidcana.jcrud.core.model.filters.FormFilterManager;
import org.github.davidcana.jcrud.core.model.filters.MemberFilter;
import org.github.davidcana.jcrud.core.requests.ListZCrudRequest;
import org.github.davidcana.jcrud.storages.JDBC.JDBCStorage;

import com.fasterxml.jackson.core.type.TypeReference;

public class OriginalMemberJDBCStorage extends JDBCStorage<OriginalMember, Integer, Member> {
	
	static private OriginalMemberJDBCStorage instance; 
	
	private OriginalMemberJDBCStorage() {
		super();
		
		this.filterManager = FormFilterManager.getInstance();
		this.setListRequestTypeReference(
				new TypeReference<ListZCrudRequest<MemberFilter>>() { }
		);
	}

	static public OriginalMemberJDBCStorage getInstance() {
		
		if ( instance == null ){
			instance = new OriginalMemberJDBCStorage();
		}
		
		return instance;
	}

}
