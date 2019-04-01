package org.github.davidcana.jcrud.core.commands;

import org.github.davidcana.jcrud.core.ZCrudEntity;
import org.github.davidcana.jcrud.core.requests.GetZCrudRequest;
import org.github.davidcana.jcrud.core.responses.AbstractZCrudResponse;
import org.github.davidcana.jcrud.core.responses.GetZCrudResponse;
import org.github.davidcana.jcrud.storages.Storage;

public class GetZCrudCommand<T extends ZCrudEntity, K, F extends ZCrudEntity> implements ZCrudCommand {

	private GetZCrudRequest<F> zCrudRequest;
	private Storage<T,K,F> storage;
	
	public GetZCrudCommand(GetZCrudRequest<F> zCrudRequest, Storage<T,K,F> storage) {
		super();
		this.zCrudRequest = zCrudRequest;
		this.storage = storage;
	}
	
	@Override
	public AbstractZCrudResponse buildResponse() {
		
		GetZCrudResponse<T> zcrudResponse = new GetZCrudResponse<>();
		
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
