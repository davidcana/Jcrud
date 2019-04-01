package org.github.davidcana.jcrud.core.commands;

import org.github.davidcana.jcrud.core.ZCrudEntity;
import org.github.davidcana.jcrud.core.requests.UpdateZCrudRequest;
import org.github.davidcana.jcrud.core.responses.AbstractZCrudResponse;
import org.github.davidcana.jcrud.core.responses.UpdateZCrudResponse;
import org.github.davidcana.jcrud.storages.Storage;

public class UpdateZCrudCommand<T extends ZCrudEntity, K, F extends ZCrudEntity> implements ZCrudCommand {

	private UpdateZCrudRequest<T, F> zCrudRequest;
	private Storage<T,K,F> storage;
	
	public UpdateZCrudCommand(UpdateZCrudRequest<T, F> zCrudRequest, Storage<T,K,F> storage) {
		super();
		this.zCrudRequest = zCrudRequest;
		this.storage = storage;
	}
	
	@Override
	public AbstractZCrudResponse buildResponse() {
		
		UpdateZCrudResponse<T> jsonResponse = new UpdateZCrudResponse<>();
		
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
