package org.github.davidcana.jcrud.core.commands;

import org.github.davidcana.jcrud.core.CRUD.CRUDHelper;
import org.github.davidcana.jcrud.core.requests.GetZCrudRequest;
import org.github.davidcana.jcrud.core.responses.GenericZCrudResponse;
import org.github.davidcana.jcrud.core.responses.GetZCrudResponse;

public class GetZCrudCommand implements ZCrudCommand {

	private GetZCrudRequest zCrudRequest;
	private CRUDHelper crudHelper;
	
	public GetZCrudCommand(GetZCrudRequest zCrudRequest, CRUDHelper crudHelper) {
		super();
		this.zCrudRequest = zCrudRequest;
		this.crudHelper = crudHelper;
	}
	
	@Override
	public GenericZCrudResponse buildResponse() {
		
		GetZCrudResponse zcrudResponse = new GetZCrudResponse();
		
		try {			
			this.crudHelper.fillGetCRUDResponse(zcrudResponse, this.zCrudRequest);
			zcrudResponse.isOK(true);

		} catch (Exception e) {
			zcrudResponse.setMessage(e.getMessage());
			zcrudResponse.isOK(false);
		}
		
		return zcrudResponse;
	}

}
