package org.github.davidcana.jcrud.core.commands;

import org.github.davidcana.jcrud.core.requests.ListZCrudRequest;
import org.github.davidcana.jcrud.core.responses.AbstractZCrudResponse;
import org.github.davidcana.jcrud.core.responses.ListZCrudResponse;
import org.github.davidcana.jcrud.storages.Storage;

public class ListZCrudCommand implements ZCrudCommand {

	private ListZCrudRequest zCrudRequest;
	private Storage storage;
	
	public ListZCrudCommand(ListZCrudRequest zCrudRequest, Storage storage) {
		super();
		this.zCrudRequest = zCrudRequest;
		this.storage = storage;
	}

	@Override
	public AbstractZCrudResponse buildResponse() {
		
		ListZCrudResponse zcrudResponse = new ListZCrudResponse();
		
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
