package org.github.davidcana.jcrud.core.responses;

import org.github.davidcana.jcrud.core.ZCrudEntity;

abstract public class GenericZCrudResponse<T extends ZCrudEntity> extends AbstractZCrudResponse {

	@Override
	public String toString() {
		return "GenericZCrudResponse []";
	}
	
}
