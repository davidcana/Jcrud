package org.github.davidcana.jcrud.core.commands;

import org.github.davidcana.jcrud.core.responses.GenericZCrudResponse;

public interface ZCrudCommand {

	public GenericZCrudResponse buildResponse();
}
