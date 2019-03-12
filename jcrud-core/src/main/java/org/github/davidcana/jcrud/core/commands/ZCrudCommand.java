package org.github.davidcana.jcrud.core.commands;

import org.github.davidcana.jcrud.core.responses.AbstractZCrudResponse;

public interface ZCrudCommand {

	public AbstractZCrudResponse buildResponse();
}
