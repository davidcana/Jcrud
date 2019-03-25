package org.github.davidcana.jcrud.storages;

import java.util.List;

import org.github.davidcana.jcrud.core.ZCrudEntity;
import org.github.davidcana.jcrud.core.options.Option;
import org.github.davidcana.jcrud.core.requests.GetZCrudRequest;
import org.github.davidcana.jcrud.core.requests.ISearchFieldData;
import org.github.davidcana.jcrud.core.requests.ListZCrudRequest;
import org.github.davidcana.jcrud.core.requests.UpdateZCrudRequest;
import org.github.davidcana.jcrud.core.responses.GetZCrudResponse;
import org.github.davidcana.jcrud.core.responses.ListZCrudResponse;

import com.fasterxml.jackson.core.type.TypeReference;

public interface Storage<T extends ZCrudEntity, K, F extends ZCrudEntity> {
	
	public List<T> getAll() throws StorageException;
	
	public void fillListCRUDResponse(ListZCrudResponse<T> listCRUDResponse, ListZCrudRequest<F> listCRUDRequest) throws StorageException;

	public void fillGetCRUDResponse(GetZCrudResponse<T> getCRUDResponse, GetZCrudRequest<F> getCRUDRequest) throws StorageException;
	
	public void doCRUD(UpdateZCrudRequest<T> updateCRUDRequest) throws StorageException;
	
	public long getNumberOfRecords(ISearchFieldData<F> iSearchFieldData) throws StorageException;
	
	public Class<?> getDeserializeClass();
	
	public void setListRequestTypeReference(TypeReference<?> typeReference);
	public TypeReference<?> getListRequestTypeReference();
	public void setGetRequestTypeReference(TypeReference<?> typeReference);
	public TypeReference<?> getGetRequestTypeReference();
	public void setUpdateRequestTypeReference(TypeReference<?> typeReference);
	public TypeReference<?> getUpdateRequestTypeReference();
	
	public List<Option<K>> getAllAsOptions() throws StorageException;
	
	public boolean isKeyNeeded();
}
