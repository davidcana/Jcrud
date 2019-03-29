package org.github.davidcana.jcrud.core.model.storages;

import org.github.davidcana.jcrud.core.model.Form;
import org.github.davidcana.jcrud.core.model.Member;
import org.github.davidcana.jcrud.core.model.filters.MemberFilter;
import org.github.davidcana.jcrud.core.requests.GetZCrudRequest;
import org.github.davidcana.jcrud.storages.JDBC.JDBCStorage;

import com.fasterxml.jackson.core.type.TypeReference;

public class FormStorage extends JDBCStorage<Form, Integer, Member> {
	
	static private FormStorage instance;
	
	public FormStorage(){
		super();
		
		this.keyNeeded = false;
		this.setGetRequestTypeReference(
				new TypeReference<GetZCrudRequest<MemberFilter>>() { }
		);
	}

	static public FormStorage getInstance() {
		
		if (instance == null){
			instance = new FormStorage();
		}
		
		return instance;
	}
}
