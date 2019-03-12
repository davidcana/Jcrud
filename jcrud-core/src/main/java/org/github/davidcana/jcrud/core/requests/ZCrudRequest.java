package org.github.davidcana.jcrud.core.requests;

import org.github.davidcana.jcrud.core.commands.ZCrudCommand;
import org.github.davidcana.jcrud.storages.Storage;

public interface ZCrudRequest {

	public ZCrudCommand buildCommand(Storage storage);
}
