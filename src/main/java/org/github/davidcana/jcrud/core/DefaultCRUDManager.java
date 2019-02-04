package org.github.davidcana.jcrud.core;

import java.io.IOException;
import java.io.Reader;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.github.davidcana.jcrud.core.CRUD.CRUDHelper;
import org.github.davidcana.jcrud.core.commands.ZCrudCommand;
import org.github.davidcana.jcrud.core.requests.GetZCrudRequest;
import org.github.davidcana.jcrud.core.requests.ListZCrudRequest;
import org.github.davidcana.jcrud.core.requests.UpdateZCrudRequest;
import org.github.davidcana.jcrud.core.requests.ZCrudRequest;
import org.github.davidcana.jcrud.core.responses.SimpleZCrudResponse;
import org.github.davidcana.jcrud.core.responses.ZCrudResponse;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DefaultCRUDManager {
	
	private static final String COMMAND_LIST_URL_PARAMETER = "LIST";
	private static final String COMMAND_GET_URL_PARAMETER = "GET";
	private static final String COMMAND_UPDATE_URL_PARAMETER = "BATCH_UPDATE";
	
	private static final String COMMAND_URL_PARAMETER = "cmd";
	private static final String TABLE_URL_PARAMETER = "table";
	
	private ObjectMapper mapper = ObjectMapperProvider.getInstance().get();
	static private DefaultCRUDManager instance;
	
	private DefaultCRUDManager(){}
	
	public ZCrudResponse buildCRUDResponse(HttpServletRequest request, Map<String, CRUDHelper> crudHelpers){
		
		ZCrudRequest zcrudRequest = null;
		ZCrudResponse zcrudResponse = null;
		
		try {
			// Build zcrudRequest
			String json = this.getStringFromReader(request.getReader());
			String cmd = request.getParameter(COMMAND_URL_PARAMETER);
			CRUDHelper crudHelper = this.buildCRUDHelper(request, crudHelpers);
			zcrudRequest = this.getRequest(cmd, json, crudHelper);
			
			// Build zcrudCommand
			ZCrudCommand zcrudCommand = zcrudRequest.buildCommand(crudHelper);
			
			// Build zcrudResponse
			zcrudResponse = zcrudCommand.buildResponse();
			
		} catch (Exception e) {
			zcrudResponse = new SimpleZCrudResponse(e.getMessage());
		}
		
		return zcrudResponse;
	}
	
	private String getStringFromReader(Reader reader) throws IOException {
		
		StringBuilder sb = new StringBuilder();
	    int intValueOfChar;
	    while ((intValueOfChar = reader.read()) != -1) {
	        sb.append( (char) intValueOfChar);
	    }
	    reader.close();
	    
	    return sb.toString();
	}
	
	private CRUDHelper buildCRUDHelper(HttpServletRequest request, Map<String, CRUDHelper> crudHelpers){
		
		String table = request.getParameter(TABLE_URL_PARAMETER);
		CRUDHelper result = crudHelpers.get(table);

		if (result != null){
			return result;
		}
		
		throw new IllegalArgumentException("Unknown table in CRUD: " + table);
	}
	
	private ZCrudRequest getRequest(String cmd, String json, CRUDHelper crudHelper) throws JsonParseException, JsonMappingException, IOException {
		
		switch (cmd){
		case COMMAND_LIST_URL_PARAMETER:
			return this.getListRequest(json, crudHelper);
			
		case COMMAND_GET_URL_PARAMETER:
			return this.getGetRequest(json, crudHelper);
			
		case COMMAND_UPDATE_URL_PARAMETER:
			return this.getUpdateRequest(json, crudHelper);
			
		case "":
			throw new IllegalArgumentException("CMD is null!");
			
		default:
			throw new IllegalArgumentException("Unknown CMD: '" + cmd + "'!");
		}
	}
	
	private ListZCrudRequest getListRequest(String json, CRUDHelper crudHelper) throws IOException, JsonParseException, JsonMappingException {
		return (ListZCrudRequest) this.mapper.readValue(json, ListZCrudRequest.class);
	}
	private GetZCrudRequest getGetRequest(String json, CRUDHelper crudHelper) throws IOException, JsonParseException, JsonMappingException {
		return (GetZCrudRequest) this.mapper.readValue(json, GetZCrudRequest.class);
	}
	@SuppressWarnings("rawtypes")
	private UpdateZCrudRequest getUpdateRequest(String json, CRUDHelper crudHelper) throws IOException, JsonParseException, JsonMappingException {
		
		JavaType type = this.mapper.getTypeFactory().constructParametricType(UpdateZCrudRequest.class, crudHelper.getDeserializeClass());
		return (UpdateZCrudRequest) this.mapper.readValue(json, type);
	}
	
	static public DefaultCRUDManager getInstance(){
		
		if ( instance == null ){
			instance = new DefaultCRUDManager();
		}
		
		return instance;
	}

}
