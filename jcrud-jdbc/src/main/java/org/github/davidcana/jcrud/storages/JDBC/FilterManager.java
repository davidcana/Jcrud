package org.github.davidcana.jcrud.storages.JDBC;

import java.sql.PreparedStatement;

import org.github.davidcana.jcrud.core.ZCrudEntity;
import org.github.davidcana.jcrud.core.requests.ISearchFieldData;
import org.github.davidcana.jcrud.storages.StorageException;

public interface FilterManager<F extends ZCrudEntity> {

	public String buildWhere(ISearchFieldData<F> iSearchFieldData, String addToWhere) throws StorageException;
	
	public void updateStatement(ISearchFieldData<F> iSearchFieldData, PreparedStatement preparedStatement) throws StorageException;
}
