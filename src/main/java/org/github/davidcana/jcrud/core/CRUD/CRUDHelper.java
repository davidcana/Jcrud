package org.github.davidcana.jcrud.core.CRUD;

import org.github.davidcana.jcrud.core.requests.GetZCrudRequest;
import org.github.davidcana.jcrud.core.requests.ListZCrudRequest;
import org.github.davidcana.jcrud.core.requests.UpdateZCrudRequest;
import org.github.davidcana.jcrud.core.responses.GetZCrudResponse;
import org.github.davidcana.jcrud.core.responses.ListZCrudResponse;
import org.github.davidcana.jcrud.storages.StorageException;

public interface CRUDHelper {
	
	public void fillListCRUDResponse(ListZCrudResponse listCRUDResponse, ListZCrudRequest listRequest) throws StorageException;
	
	public void fillGetCRUDResponse(GetZCrudResponse getCRUDResponse, GetZCrudRequest getRequest) throws StorageException;
	
	public void doCRUD(@SuppressWarnings("rawtypes") UpdateZCrudRequest updateRequest) throws StorageException;
	
	public Class<?> getDeserializeClass();
}
