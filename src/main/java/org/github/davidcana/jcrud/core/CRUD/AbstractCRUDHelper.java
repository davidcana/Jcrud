package org.github.davidcana.jcrud.core.CRUD;

import java.util.ArrayList;
import java.util.List;

import org.github.davidcana.jcrud.core.ZCrudEntity;
import org.github.davidcana.jcrud.core.options.Option;
import org.github.davidcana.jcrud.core.options.OptionProvider;
import org.github.davidcana.jcrud.core.requests.GetZCrudRequest;
import org.github.davidcana.jcrud.core.requests.ListZCrudRequest;
import org.github.davidcana.jcrud.core.requests.UpdateZCrudRequest;
import org.github.davidcana.jcrud.core.responses.GetZCrudResponse;
import org.github.davidcana.jcrud.core.responses.ListZCrudResponse;
import org.github.davidcana.jcrud.storages.Storage;
import org.github.davidcana.jcrud.storages.StorageException;

abstract public class AbstractCRUDHelper<T extends ZCrudEntity, K> implements CRUDHelper {
	
	protected Storage<T> storage;
	
	public AbstractCRUDHelper(Storage<T> storage) {
		super();
		this.storage = storage;
	}
	
	public List<T> getAll() throws StorageException {
		return this.storage.getAll();
	}
	
	public List<Option<K>> getAllAsOptions() throws StorageException {
		
		try {
			List<Option<K>> result = new ArrayList<>();
			
			for (T object : this.getAll()){
				OptionProvider<K> optionProvider = (OptionProvider<K>) object;
				result.add(
						optionProvider.buildOption());
			}
			
			return result;
			
		} catch (ClassCastException e) {
			throw new StorageException(e);
		}
	}
	
	@Override
	public void fillListCRUDResponse(ListZCrudResponse listCRUDResponse, ListZCrudRequest listRequest) throws StorageException {
		this.storage.fillListCRUDResponse(listCRUDResponse, listRequest);
	}

	@Override
	public void fillGetCRUDResponse(GetZCrudResponse getCRUDResponse, GetZCrudRequest getRequest) throws StorageException {
		this.storage.fillGetCRUDResponse(getCRUDResponse, getRequest);
	}
	
	@Override
	public void doCRUD(@SuppressWarnings("rawtypes") UpdateZCrudRequest updateRequest)
			throws StorageException {
		this.storage.doCRUD(updateRequest);
	}
	
	@Override
	abstract public Class<?> getDeserializeClass();

}
