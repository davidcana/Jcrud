package org.github.davidcana.jcrud.storages;

import java.util.List;

import org.github.davidcana.jcrud.core.ZCrudEntity;
import org.github.davidcana.jcrud.core.requests.GetZCrudRequest;
import org.github.davidcana.jcrud.core.requests.ListZCrudRequest;
import org.github.davidcana.jcrud.core.requests.UpdateZCrudRequest;
import org.github.davidcana.jcrud.core.responses.GetZCrudResponse;
import org.github.davidcana.jcrud.core.responses.ListZCrudResponse;

public interface Storage<T extends ZCrudEntity> {
	
	public List<T> getAll() throws StorageException;
	
	public void fillListCRUDResponse(ListZCrudResponse listCRUDResponse, ListZCrudRequest listRequest) throws StorageException;

	public void fillGetCRUDResponse(GetZCrudResponse getCRUDResponse, GetZCrudRequest getRequest) throws StorageException;
	
	public void doCRUD(UpdateZCrudRequest<T> updateRequest) throws StorageException;
	
	public T get(String key) throws StorageException;
	
	public long getTotalNumberOfRecords() throws StorageException;
	
	//public Storage getSubformStorage(String fieldName);
}
