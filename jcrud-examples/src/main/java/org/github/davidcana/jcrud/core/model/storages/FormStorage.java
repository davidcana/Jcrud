package org.github.davidcana.jcrud.core.model.storages;

import java.util.ArrayList;
import java.util.List;

import org.github.davidcana.jcrud.core.model.Form;
import org.github.davidcana.jcrud.core.model.OriginalMember;
import org.github.davidcana.jcrud.core.model.VerifiedMember;
import org.github.davidcana.jcrud.core.options.Option;
import org.github.davidcana.jcrud.core.requests.GetZCrudRequest;
import org.github.davidcana.jcrud.core.requests.ListZCrudRequest;
import org.github.davidcana.jcrud.core.requests.UpdateZCrudRequest;
import org.github.davidcana.jcrud.core.responses.GetZCrudResponse;
import org.github.davidcana.jcrud.core.responses.ListZCrudResponse;
import org.github.davidcana.jcrud.storages.Storage;
import org.github.davidcana.jcrud.storages.StorageException;

public class FormStorage implements Storage<Form, Integer, Form> {
	
	static private FormStorage instance;
	
	@Override
	public void fillGetCRUDResponse(GetZCrudResponse<Form> getCRUDResponse, GetZCrudRequest<Form> getCRUDRequest)
			throws StorageException {
		
		// Create an instance of Form
		Form form = new Form();
		form.setOriginalMembers(
				getOriginalMembers(getCRUDRequest)
		);
		form.setVerifiedMembers(
				getVerifiedMembers(getCRUDRequest)
		);
		
		// Set the form as the record of the CRUD response
		getCRUDResponse.setRecord(form);
	}

	static private List<OriginalMember> getOriginalMembers(GetZCrudRequest<Form> getCRUDRequest) throws StorageException{
		
		// Build list request
		ListZCrudRequest<Form> listCRUDRequest = new ListZCrudRequest<>();
		listCRUDRequest.setCommand("LIST");
		listCRUDRequest.setPageNumber(1);
		listCRUDRequest.setPageSize(10);
		listCRUDRequest.setSortFieldId("id");
		listCRUDRequest.setSortType("ASC");
		listCRUDRequest.setFilter(
				getCRUDRequest.getFilter() != null? getCRUDRequest.getFilter(): new Form()
						
		);
		
		// Build list response
		ListZCrudResponse<OriginalMember> listCRUDResponse = new ListZCrudResponse<>();
		
		OriginalMemberJDBCStorage.getInstance().fillListCRUDResponse(listCRUDResponse, listCRUDRequest);
		return listCRUDResponse.getRecords();
		
		/*List<Member> originalMembers = new ArrayList<>();
		return originalMembers;*/
	}
	
	static private List<VerifiedMember> getVerifiedMembers(GetZCrudRequest<Form> getCRUDRequest){
		
		List<VerifiedMember> verifiedMembers = new ArrayList<>();
		return verifiedMembers;
	}
	
	@Override
	public void doCRUD(UpdateZCrudRequest<Form> updateCRUDRequest) throws StorageException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Class<?> getDeserializeClass() {
		return Form.class;
	}
	
	@Override
	public void fillListCRUDResponse(ListZCrudResponse<Form> listCRUDResponse, ListZCrudRequest<Form> listCRUDRequest)
			throws StorageException {
		throw new StorageException("Not implemented method in FormStorage: fillListCRUDResponse");
	}
	
	@Override
	public List<Form> getAll() throws StorageException {
		throw new StorageException("Not implemented method in FormStorage: getAll");
	}
	
	@Override
	public long getNumberOfRecords(ListZCrudRequest<Form> listCRUDRequest) throws StorageException {
		throw new StorageException("Not implemented method in FormStorage: getNumberOfRecords");
	}

	@Override
	public List<Option<Integer>> getAllAsOptions() throws StorageException {
		throw new StorageException("Not implemented method in FormStorage: getAllAsOptions");
	}
	
	static public FormStorage getInstance() {
		
		if (instance == null){
			instance = new FormStorage();
		}
		
		return instance;
	}
}
