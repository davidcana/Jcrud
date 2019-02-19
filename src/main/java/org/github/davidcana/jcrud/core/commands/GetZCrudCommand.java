package org.github.davidcana.jcrud.core.commands;

import org.github.davidcana.jcrud.core.requests.GetZCrudRequest;
import org.github.davidcana.jcrud.core.responses.AbstractZCrudResponse;
import org.github.davidcana.jcrud.core.responses.GetZCrudResponse;
import org.github.davidcana.jcrud.storages.Storage;

public class GetZCrudCommand implements ZCrudCommand {

	private GetZCrudRequest zCrudRequest;
	private Storage storage;
	
	public GetZCrudCommand(GetZCrudRequest zCrudRequest, Storage storage) {
		super();
		this.zCrudRequest = zCrudRequest;
		this.storage = storage;
	}
	
	@Override
	public AbstractZCrudResponse buildResponse() {
		
		GetZCrudResponse zcrudResponse = new GetZCrudResponse();
		
		try {			
			this.storage.fillGetCRUDResponse(zcrudResponse, this.zCrudRequest);
			zcrudResponse.isOK(true);

		} catch (Exception e) {
			zcrudResponse.setMessage(e.getMessage());
			zcrudResponse.isOK(false);
		}
		
		return zcrudResponse;
	}

}
