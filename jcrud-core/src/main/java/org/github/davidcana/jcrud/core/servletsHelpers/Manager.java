package org.github.davidcana.jcrud.core.servletsHelpers;

import javax.servlet.http.HttpServletRequest;

import org.github.davidcana.jcrud.core.responses.ZCrudResponse;

public interface Manager {

	public ZCrudResponse buildZCrudResponse(HttpServletRequest request);
	
}
