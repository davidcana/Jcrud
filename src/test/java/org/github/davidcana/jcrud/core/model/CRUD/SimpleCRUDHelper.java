package org.github.davidcana.jcrud.core.model.CRUD;

import org.github.davidcana.jcrud.core.CRUD.CRUDHelper;
import org.github.davidcana.jcrud.core.model.Simple;
import org.github.davidcana.jcrud.core.requests.GetZCrudRequest;
import org.github.davidcana.jcrud.core.requests.ListZCrudRequest;
import org.github.davidcana.jcrud.core.requests.UpdateZCrudRequest;
import org.github.davidcana.jcrud.core.responses.GetZCrudResponse;
import org.github.davidcana.jcrud.core.responses.ListZCrudResponse;
import org.github.davidcana.jcrud.storages.StorageException;

public class SimpleCRUDHelper implements CRUDHelper {

	static public final String TABLE_PARAMETER_VALUE = "simple";
	static private SimpleCRUDHelper instance; 
	
	public SimpleCRUDHelper() {}
	
	@Override
	public Class<?> getDeserializeClass() {
		return Simple.class;
	}

	@Override
	public void fillListCRUDResponse(ListZCrudResponse listCRUDResponse, ListZCrudRequest listRequest)
			throws StorageException {
		throw new StorageException("Not implemented method!");		
	}

	@Override
	public void fillGetCRUDResponse(GetZCrudResponse getCRUDResponse, GetZCrudRequest getRequest)
			throws StorageException {
		throw new StorageException("Not implemented method!");
	}

	@Override
	public void doCRUD(UpdateZCrudRequest updateRequest) throws StorageException {
		throw new StorageException("Not implemented method!");
	}
	
	static public SimpleCRUDHelper getInstance(){
		
		if (instance == null){
			instance = new SimpleCRUDHelper();
		}
		
		return instance;
	}
}
