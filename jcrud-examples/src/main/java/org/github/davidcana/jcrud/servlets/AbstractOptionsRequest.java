package org.github.davidcana.jcrud.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.github.davidcana.jcrud.core.ObjectMapperProvider;
import org.github.davidcana.jcrud.core.ZCrudEntity;
import org.github.davidcana.jcrud.core.options.Option;
import org.github.davidcana.jcrud.core.options.OptionsResponse;
import org.github.davidcana.jcrud.storages.Storage;

import com.fasterxml.jackson.core.JsonProcessingException;

abstract public class AbstractOptionsRequest<T extends ZCrudEntity, K, F extends ZCrudEntity> extends JsonServlet {

	private static final long serialVersionUID = 139851818223035833L;
	
	protected void service(HttpServletResponse response, Storage<T, K, F> crudHelper)
			throws ServletException, IOException {
		
		super.prepareResponse(response);
								   
		OptionsResponse<K> jsonResponse = new OptionsResponse<>();
		try {
			this.service(response, jsonResponse, crudHelper.getAllAsOptions());
			
		} catch (Exception e) {
			this.treatError(jsonResponse, e);
		}
	}
	
	protected void service(HttpServletResponse response, OptionsResponse<K> jsonResponse, List<Option<K>> list)
			throws ServletException, IOException {
		
		super.prepareResponse(response);

		try {
			jsonResponse.setOptions(list);
			jsonResponse.done(true);
			
		} catch (Exception e) {
			this.treatError(jsonResponse, e);
		}

		this.printJSONResponse(response, jsonResponse);
	}

	protected void printJSONResponse(HttpServletResponse response, OptionsResponse<K> jsonResponse)
			throws JsonProcessingException, IOException {
		
		String json = ObjectMapperProvider.getInstance().get().writeValueAsString(jsonResponse);
		response.getWriter().print(json);
	}
	
	protected void treatError(OptionsResponse<K> jsonResponse, Exception e){
		
		jsonResponse.setMessage(
				e.getMessage());
		jsonResponse.done(false);
	}
}
