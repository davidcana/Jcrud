package org.github.davidcana.jcrud.core.commands;

import org.github.davidcana.jcrud.core.CRUD.CRUDHelper;
import org.github.davidcana.jcrud.core.requests.UpdateZCrudRequest;
import org.github.davidcana.jcrud.core.responses.AbstractZCrudResponse;
import org.github.davidcana.jcrud.core.responses.UpdateZCrudResponse;

public class UpdateZCrudCommand implements ZCrudCommand {

	@SuppressWarnings("rawtypes")
	private UpdateZCrudRequest zCrudRequest;
	private CRUDHelper crudHelper;
	
	public UpdateZCrudCommand(@SuppressWarnings("rawtypes") UpdateZCrudRequest zCrudRequest, CRUDHelper crudHelper) {
		super();
		this.zCrudRequest = zCrudRequest;
		this.crudHelper = crudHelper;
	}
	
	@Override
	public AbstractZCrudResponse buildResponse() {
		
		UpdateZCrudResponse jsonResponse = new UpdateZCrudResponse();
		
		try {			
			this.crudHelper.doCRUD(this.zCrudRequest);
			jsonResponse.isOK(true);

		} catch (Exception e) {
			jsonResponse.setMessage(e.getMessage());
			jsonResponse.isOK(false);
		}
		
		return jsonResponse;
	}

}
