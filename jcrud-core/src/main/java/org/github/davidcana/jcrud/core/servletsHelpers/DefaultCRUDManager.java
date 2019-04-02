package org.github.davidcana.jcrud.core.servletsHelpers;

import java.io.IOException;
import java.io.Reader;

import javax.servlet.http.HttpServletRequest;

import org.github.davidcana.jcrud.core.Constants;
import org.github.davidcana.jcrud.core.ObjectMapperProvider;
import org.github.davidcana.jcrud.core.commands.ZCrudCommand;
import org.github.davidcana.jcrud.core.requests.ZCrudRequest;
import org.github.davidcana.jcrud.core.responses.SimpleZCrudResponse;
import org.github.davidcana.jcrud.core.responses.ZCrudResponse;
import org.github.davidcana.jcrud.core.utils.CoreUtils;
import org.github.davidcana.jcrud.storages.Storage;
import org.github.davidcana.jcrud.storages.StorageResolver;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class DefaultCRUDManager implements Manager {
	
	static private DefaultCRUDManager instance;
	
	private DefaultCRUDManager(){}
	
	@Override
	public ZCrudResponse buildZCrudResponse(HttpServletRequest request){
		
		ZCrudRequest<?,?,?> zcrudRequest = null;
		ZCrudResponse zcrudResponse = null;
		
		try {
			// Build zcrudRequest
			@SuppressWarnings("rawtypes")
			Storage storage = StorageResolver.getInstance().get(
					request.getParameter(Constants.TABLE_URL_PARAMETER)
			);
			zcrudRequest = this.getRequest(
					request.getParameter(Constants.COMMAND_URL_PARAMETER), 
					request.getReader(), 
					storage
			);
			
			// Build zcrudCommand
			ZCrudCommand zcrudCommand = zcrudRequest.buildCommand(storage);
			
			// Build zcrudResponse
			zcrudResponse = zcrudCommand.buildResponse();
			
		} catch (Exception e) {
			zcrudResponse = new SimpleZCrudResponse(
					e.getMessage()
			);
		}
		
		return zcrudResponse;
	}
	
	public ZCrudRequest<?,?,?> getRequest(String cmd, Reader jsonReader, Storage<?,?,?> storage) throws JsonParseException, JsonMappingException, IOException {
		
		String json = CoreUtils.getInstance().getStringFromReader(jsonReader);
		
		switch (cmd){
		case Constants.COMMAND_LIST_URL_PARAMETER:
			return ObjectMapperProvider.getInstance().getListRequest(json, storage);
			
		case Constants.COMMAND_GET_URL_PARAMETER:
			return ObjectMapperProvider.getInstance().getGetRequest(json, storage);
			
		case Constants.COMMAND_UPDATE_URL_PARAMETER:
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
