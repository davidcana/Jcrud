package org.github.davidcana.jcrud.servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

abstract public class JsonServlet extends HttpServlet {

	private static final long serialVersionUID = 1101851592103805205L;

	protected void prepareResponse(HttpServletResponse response){
		
        response.setStatus(HttpServletResponse.SC_OK);  
        response.setContentType("application/json");  
	}
}
