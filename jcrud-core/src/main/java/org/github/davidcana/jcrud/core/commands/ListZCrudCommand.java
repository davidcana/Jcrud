package org.github.davidcana.jcrud.core.commands;

import org.github.davidcana.jcrud.core.ZCrudEntity;
import org.github.davidcana.jcrud.core.requests.ListZCrudRequest;
import org.github.davidcana.jcrud.core.responses.AbstractZCrudResponse;
import org.github.davidcana.jcrud.core.responses.ListZCrudResponse;
import org.github.davidcana.jcrud.storages.Storage;

public class ListZCrudCommand<T extends ZCrudEntity, K, F extends ZCrudEntity> implements ZCrudCommand {

	private ListZCrudRequest<F> zCrudRequest;
	private Storage<T,K,F> storage;
	
	public ListZCrudCommand(ListZCrudRequest<F> zCrudRequest, Storage<T,K,F> storage) {
		super();
		this.zCrudRequest = zCrudRequest;
		this.storage = storage;
	}

	@Override
	public AbstractZCrudResponse buildResponse() {
		
		ListZCrudResponse<T> zcrudResponse = new ListZCrudResponse<>();
		
		try {			
			this.storage.fillListCRUDResponse(zcrudResponse, this.zCrudRequest);
			zcrudResponse.isOK(true);

		} catch (Exception e) {
			zcrudResponse.setMessage(e.getMessage());
			zcrudResponse.isOK(false);
		}
		
		return zcrudResponse;
	}

}
