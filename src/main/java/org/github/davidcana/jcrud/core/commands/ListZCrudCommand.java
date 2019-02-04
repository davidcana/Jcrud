package org.github.davidcana.jcrud.core.commands;

import org.github.davidcana.jcrud.core.CRUD.CRUDHelper;
import org.github.davidcana.jcrud.core.requests.ListZCrudRequest;
import org.github.davidcana.jcrud.core.responses.GenericZCrudResponse;
import org.github.davidcana.jcrud.core.responses.ListZCrudResponse;

public class ListZCrudCommand implements ZCrudCommand {

	private ListZCrudRequest zCrudRequest;
	private CRUDHelper crudHelper;
	
	public ListZCrudCommand(ListZCrudRequest zCrudRequest, CRUDHelper crudHelper) {
		super();
		this.zCrudRequest = zCrudRequest;
		this.crudHelper = crudHelper;
	}

	@Override
	public GenericZCrudResponse buildResponse() {
		
		ListZCrudResponse zcrudResponse = new ListZCrudResponse();
		
		try {			
			this.crudHelper.fillListCRUDResponse(zcrudResponse, this.zCrudRequest);
			zcrudResponse.isOK(true);

		} catch (Exception e) {
			zcrudResponse.setMessage(e.getMessage());
			zcrudResponse.isOK(false);
		}
		
		return zcrudResponse;
	}

}
