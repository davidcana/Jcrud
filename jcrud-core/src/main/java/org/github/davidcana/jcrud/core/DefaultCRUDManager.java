package org.github.davidcana.jcrud.core;

import java.io.IOException;
import java.io.Reader;

import javax.servlet.http.HttpServletRequest;

import org.github.davidcana.jcrud.core.commands.ZCrudCommand;
import org.github.davidcana.jcrud.core.requests.ZCrudRequest;
import org.github.davidcana.jcrud.core.responses.SimpleZCrudResponse;
import org.github.davidcana.jcrud.core.responses.ZCrudResponse;
import org.github.davidcana.jcrud.core.utils.CoreUtils;
import org.github.davidcana.jcrud.storages.Storage;
import org.github.davidcana.jcrud.storages.StorageResolver;

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
	
	public ZCrudResponse buildCRUDResponse(HttpServletRequest request){
		
		ZCrudRequest zcrudRequest = null;
		ZCrudResponse zcrudResponse = null;
		
		try {
			// Build zcrudRequest
			Storage storage = StorageResolver.getInstance().get(
					request.getParameter(TABLE_URL_PARAMETER)
			);
			zcrudRequest = this.getRequest(
					request.getParameter(COMMAND_URL_PARAMETER), 
					request.getReader(), 
					storage
			);
			
			// Build zcrudCommand
			ZCrudCommand zcrudCommand = zcrudRequest.buildCommand(storage);
			
			// Build zcrudResponse
			zcrudResponse = zcrudCommand.buildResponse();
			
		} catch (Exception e) {
			zcrudResponse = new SimpleZCrudResponse(e.getMessage());
		}
		
		return zcrudResponse;
	}
	
	public ZCrudRequest getRequest(String cmd, Reader jsonReader, Storage storage) throws JsonParseException, JsonMappingException, IOException {
		
		String json = CoreUtils.getInstance().getStringFromReader(jsonReader);
		
		switch (cmd){
		case COMMAND_LIST_URL_PARAMETER:
			return ObjectMapperProvider.getInstance().getListRequest(json, storage);
			
		case COMMAND_GET_URL_PARAMETER:
			return ObjectMapperProvider.getInstance().getGetRequest(json, storage);
			
		case COMMAND_UPDATE_URL_PARAMETER:
			return ObjectMapperProvider.getInstance().getUpdateRequest(json, storage);
			
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
