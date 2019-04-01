package org.github.davidcana.jcrud.core.requests;

import org.github.davidcana.jcrud.core.ZCrudEntity;
import org.github.davidcana.jcrud.core.commands.ZCrudCommand;
import org.github.davidcana.jcrud.storages.Storage;

public interface ZCrudRequest<F extends ZCrudEntity> {

	public ZCrudCommand buildCommand(Storage<?,?,F> storage);
}
