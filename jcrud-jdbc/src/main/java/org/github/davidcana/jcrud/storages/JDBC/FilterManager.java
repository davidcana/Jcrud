package org.github.davidcana.jcrud.storages.JDBC;

import java.sql.PreparedStatement;

import org.github.davidcana.jcrud.core.ZCrudEntity;
import org.github.davidcana.jcrud.core.requests.ListZCrudRequest;
import org.github.davidcana.jcrud.storages.StorageException;

public interface FilterManager<F extends ZCrudEntity> {

	public String buildWhere(ListZCrudRequest<F> listRequest) throws StorageException;
	
	public void updateStatement(ListZCrudRequest<F> listRequest, PreparedStatement preparedStatement) throws StorageException;
}
