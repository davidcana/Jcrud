package org.github.davidcana.jcrud.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.github.davidcana.jcrud.core.model.Secondary;
import org.github.davidcana.jcrud.storages.JDBC.SecondaryJDBCStorage;

@WebServlet("/secondaryRequest.do")
public class SecondaryRequest extends AbstractOptionsRequest<Secondary, Integer, Secondary> {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		super.service(response, SecondaryJDBCStorage.getInstance());
	}

}
