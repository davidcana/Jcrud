package org.github.davidcana.jcrud.core;

import java.io.IOException;
import java.io.Reader;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.github.davidcana.jcrud.core.CRUD.CRUDHelper;
import org.github.davidcana.jcrud.core.commands.ZCrudCommand;
import org.github.davidcana.jcrud.core.requests.ZCrudRequest;
import org.github.davidcana.jcrud.core.responses.SimpleZCrudResponse;
import org.github.davidcana.jcrud.core.responses.ZCrudResponse;
import org.github.davidcana.jcrud.core.utils.CoreUtils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class DefaultCRUDManager {
	
	public static final String COMMAND_LIST_URL_PARAMETER = "LIST";
	public static final String COMMAND_GET_URL_PARAMETER = "GET";
	public static final String COMMAND_UPDATE_URL_PARAMETER = "BATCH_UPDATE";
	
	private static final String COMMAND_URL_PARAMETER = "cmd";
	private static final String TABLE_URL_PARAMETER = "table";
	
	static private DefaultCRUDManager instance;
	
	private DefaultCRUDManager(){}
	
	public ZCrudResponse buildCRUDResponse(HttpServletRequest request, Map<String, CRUDHelper> crudHelpers){
		
		ZCrudRequest zcrudRequest = null;
		ZCrudResponse zcrudResponse = null;
		
		try {
			// Build zcrudRequest
			CRUDHelper crudHelper = this.buildCRUDHelper(request, crudHelpers);
			zcrudRequest = this.getRequest(
					request.getParameter(COMMAND_URL_PARAMETER), 
					request.getReader(), 
					crudHelper
			);
			
			// Build zcrudCommand
			ZCrudCommand zcrudCommand = zcrudRequest.buildCommand(crudHelper);
			
			// Build zcrudResponse
			zcrudResponse = zcrudCommand.buildResponse();
			
		} catch (Exception e) {
			zcrudResponse = new SimpleZCrudResponse(e.getMessage());
		}
		
		return zcrudResponse;
	}
	
	private CRUDHelper buildCRUDHelper(HttpServletRequest request, Map<String, CRUDHelper> crudHelpers){
		
		String table = request.getParameter(TABLE_URL_PARAMETER);
		CRUDHelper result = crudHelpers.get(table);

		if (result != null){
			return result;
		}
		
		throw new IllegalArgumentException("Unknown table in CRUD: " + table);
	}
	
	public ZCrudRequest getRequest(String cmd, Reader jsonReader, CRUDHelper crudHelper) throws JsonParseException, JsonMappingException, IOException {
		
		String json = CoreUtils.getInstance().getStringFromReader(jsonReader);
		
		switch (cmd){
		case COMMAND_LIST_URL_PARAMETER:
			return ObjectMapperProvider.getInstance().getListRequest(json, crudHelper);
			
		case COMMAND_GET_URL_PARAMETER:
			return ObjectMapperProvider.getInstance().getGetRequest(json, crudHelper);
			
		case COMMAND_UPDATE_URL_PARAMETER:
			return ObjectMapperProvider.getInstance().getUpdateRequest(json, crudHelper);
			
		case "":
			throw new IllegalArgumentException("CMD is null!");
			
		default:
			throw new IllegalArgumentException("Unknown CMD: '" + cmd + "'!");
		}
	}
	
	static public DefaultCRUDManager getInstance(){
		
		if ( instance == null ){
			instance = new DefaultCRUDManager();
		}
		
		return instance;
	}

}
