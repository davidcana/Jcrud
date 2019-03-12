package org.github.davidcana.jcrud.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.github.davidcana.jcrud.core.DefaultCRUDManager;
import org.github.davidcana.jcrud.core.responses.ZCrudResponse;

@WebServlet("/CRUDManager.do")
public class CRUDManager extends JsonServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		super.prepareResponse( response );
		
		ZCrudResponse jsonResponse = DefaultCRUDManager.getInstance().buildCRUDResponse(request);
		
		String json = jsonResponse.buildJSON();
		response.getWriter().print(json);
	}

}
