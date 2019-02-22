package org.github.davidcana.jcrud.storages;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;

import org.github.davidcana.jcrud.core.model.Simple;
import org.github.davidcana.jcrud.core.requests.GetZCrudRequest;
import org.github.davidcana.jcrud.core.requests.ListZCrudRequest;
import org.github.davidcana.jcrud.core.requests.UpdateZCrudRequest;
import org.github.davidcana.jcrud.core.responses.GetZCrudResponse;
import org.github.davidcana.jcrud.core.responses.ListZCrudResponse;

public class SimpleVoidStorage extends AbstractStorage<Simple, Integer> implements Storage<Simple, Integer> {
	
	static private SimpleVoidStorage instance;
	
	@Override
	public void configure() throws StorageException {
		// Nothing to do
	}
	
	@Override
	public List<Simple> getAll() throws StorageException {
		throw new StorageException("Not implemented method!");
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
	public void doCRUD(UpdateZCrudRequest<Simple> updateRequest) throws StorageException {
		throw new StorageException("Not implemented method!");
	}

	@Override
	public Simple get(String key) throws StorageException {
		throw new StorageException("Not implemented method!");
	}

	@Override
	public long getTotalNumberOfRecords() throws StorageException {
		throw new StorageException("Not implemented method!");
	}

	@Override
	public Class<?> getDeserializeClass() {
		return Simple.class;
	}

	@Override
	protected boolean resolveClassAnnotation(Annotation annotation) {
		return false;
	}

	@Override
	protected boolean resolveFieldAnnotation(Annotation annotation, Field field) {
		return false;
	}
	
	static public SimpleVoidStorage getInstance(){
		
		if (instance == null){
			instance = new SimpleVoidStorage();
		}
		
		return instance;
	}

}
