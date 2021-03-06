package org.github.davidcana.jcrud.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.github.davidcana.jcrud.core.servletsHelpers.DefaultCRUDManager;
import org.github.davidcana.jcrud.core.servletsHelpers.ManagerServlet;

@WebServlet("/CRUDManager.do")
public class CRUDManager extends ManagerServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		super.service(
				request, 
				response, 
				DefaultCRUDManager.getInstance()
		);
	}

}
