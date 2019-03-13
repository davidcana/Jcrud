package org.github.davidcana.jcrud.core.servletsHelpers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.github.davidcana.jcrud.core.responses.ZCrudResponse;

abstract public class ManagerServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	protected void service(HttpServletRequest request, HttpServletResponse response, Manager manager) throws ServletException, IOException {
		
		try {
			response.setContentType("application/json"); 
			
			ZCrudResponse zcrudResponse = manager.buildZCrudResponse(request);
			String json = zcrudResponse.buildJSON();
			response.getWriter().print(json);
			
			response.setStatus(HttpServletResponse.SC_OK);
	        
		} catch (Exception e) {
	        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}
}
