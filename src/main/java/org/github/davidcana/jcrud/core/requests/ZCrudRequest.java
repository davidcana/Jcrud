package org.github.davidcana.jcrud.core.requests;

import org.github.davidcana.jcrud.core.CRUD.CRUDHelper;
import org.github.davidcana.jcrud.core.commands.ZCrudCommand;

public interface ZCrudRequest {

	public ZCrudCommand buildCommand(CRUDHelper crudHelper);
}
