package org.github.davidcana.jcrud.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.github.davidcana.jcrud.core.servletsHelpers.DefaultOptionsManager;
import org.github.davidcana.jcrud.core.servletsHelpers.ManagerServlet;

@WebServlet("/OptionsManager.do")
public class OptionsManager extends ManagerServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		super.service(
				request, 
				response, 
				DefaultOptionsManager.getInstance()
		);
	}

}
