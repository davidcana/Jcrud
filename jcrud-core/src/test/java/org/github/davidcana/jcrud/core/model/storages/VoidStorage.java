package org.github.davidcana.jcrud.core.model.storages;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;

import org.github.davidcana.jcrud.core.File;
import org.github.davidcana.jcrud.core.ZCrudEntity;
import org.github.davidcana.jcrud.core.requests.GetZCrudRequest;
import org.github.davidcana.jcrud.core.requests.ISearchFieldData;
import org.github.davidcana.jcrud.core.requests.ListZCrudRequest;
import org.github.davidcana.jcrud.core.requests.UpdateZCrudRequest;
import org.github.davidcana.jcrud.core.responses.GetZCrudResponse;
import org.github.davidcana.jcrud.core.responses.ListZCrudResponse;
import org.github.davidcana.jcrud.storages.AbstractStorage;
import org.github.davidcana.jcrud.storages.Storage;
import org.github.davidcana.jcrud.storages.StorageException;

public class VoidStorage<T extends ZCrudEntity, K, F extends ZCrudEntity> extends AbstractStorage<T, K, F> implements Storage<T, K, F> {
	
	public VoidStorage(){
		super();
	}
	
	@Override
	public List<T> getAll() throws StorageException {
		throw new StorageException("Not implemented method!");
	}

	@Override
	public void doCRUD(UpdateZCrudRequest<T, K, F> updateRequest) throws StorageException {
		throw new StorageException("Not implemented method!");
	}

	@Override
	protected boolean resolveClassAnnotation(Annotation annotation) {
		return false;
	}

	@Override
	protected boolean resolveFieldAnnotation(Annotation annotation, Field field) {
		return false;
	}

	@Override
	public void fillListCRUDResponse(ListZCrudResponse<T> listCRUDResponse, ListZCrudRequest<T, K, F> listCRUDRequest)
			throws StorageException {
		throw new StorageException("Not implemented method!");
	}

	@Override
	public void fillGetCRUDResponse(GetZCrudResponse<T> getCRUDResponse, GetZCrudRequest<T, K, F> getCRUDRequest)
			throws StorageException {
		throw new StorageException("Not implemented method!");
	}

	@Override
	public long getNumberOfRecords(ISearchFieldData<F> iSearchFieldData) throws StorageException {
		throw new StorageException("Not implemented method!");
	}

	@Override
	public File getFile(String key, String fileFieldName, boolean getContents) throws StorageException {
		throw new StorageException("Not implemented method!");
	}

}
