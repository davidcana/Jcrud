package org.github.davidcana.jcrud.core.commands;

import org.github.davidcana.jcrud.core.requests.UpdateZCrudRequest;
import org.github.davidcana.jcrud.core.responses.AbstractZCrudResponse;
import org.github.davidcana.jcrud.core.responses.UpdateZCrudResponse;
import org.github.davidcana.jcrud.storages.Storage;

public class UpdateZCrudCommand implements ZCrudCommand {

	private UpdateZCrudRequest zCrudRequest;
	private Storage storage;
	
	public UpdateZCrudCommand(UpdateZCrudRequest zCrudRequest, Storage storage) {
		super();
		this.zCrudRequest = zCrudRequest;
		this.storage = storage;
	}
	
	@Override
	public AbstractZCrudResponse buildResponse() {
		
		UpdateZCrudResponse jsonResponse = new UpdateZCrudResponse();
		
		try {			
			this.storage.doCRUD(this.zCrudRequest);
			jsonResponse.isOK(true);

		} catch (Exception e) {
			jsonResponse.setMessage(e.getMessage());
			jsonResponse.isOK(false);
		}
		
		return jsonResponse;
	}

}
