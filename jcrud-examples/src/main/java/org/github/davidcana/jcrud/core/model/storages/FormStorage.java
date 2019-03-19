package org.github.davidcana.jcrud.core.model.storages;

import java.util.List;

import org.github.davidcana.jcrud.core.model.Form;
import org.github.davidcana.jcrud.core.options.Option;
import org.github.davidcana.jcrud.core.requests.GetZCrudRequest;
import org.github.davidcana.jcrud.core.requests.ListZCrudRequest;
import org.github.davidcana.jcrud.core.requests.UpdateZCrudRequest;
import org.github.davidcana.jcrud.core.responses.GetZCrudResponse;
import org.github.davidcana.jcrud.core.responses.ListZCrudResponse;
import org.github.davidcana.jcrud.storages.Storage;
import org.github.davidcana.jcrud.storages.StorageException;

public class FormStorage implements Storage<Form, Integer, Form> {

	@Override
	public void fillGetCRUDResponse(GetZCrudResponse<Form> getCRUDResponse, GetZCrudRequest getCRUDRequest)
			throws StorageException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doCRUD(UpdateZCrudRequest<Form> updateCRUDRequest) throws StorageException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Class<?> getDeserializeClass() {
		return Form.class;
	}
	
	@Override
	public void fillListCRUDResponse(ListZCrudResponse<Form> listCRUDResponse, ListZCrudRequest<Form> listCRUDRequest)
			throws StorageException {
		throw new StorageException("Not implemented method!");
	}
	
	@Override
	public List<Form> getAll() throws StorageException {
		throw new StorageException("Not implemented method!");
	}
	
	@Override
	public long getNumberOfRecords(ListZCrudRequest<Form> listCRUDRequest) throws StorageException {
		throw new StorageException("Not implemented method!");
	}

	@Override
	public List<Option<Integer>> getAllAsOptions() throws StorageException {
		throw new StorageException("Not implemented method!");
	}

}
