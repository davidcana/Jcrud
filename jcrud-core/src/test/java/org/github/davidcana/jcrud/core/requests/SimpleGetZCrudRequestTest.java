package org.github.davidcana.jcrud.core.requests;

import java.io.IOException;

import org.github.davidcana.jcrud.core.DefaultCRUDManager;
import org.github.davidcana.jcrud.core.requests.GetZCrudRequest;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class SimpleGetZCrudRequestTest extends AbstractZCrudRequestTest {

	@Test
	public void testBuildResponse() throws JsonParseException, JsonMappingException, IOException {
		
        // Build zcrudRequest
        GetZCrudRequest zcrudRequest = new GetZCrudRequest();
        zcrudRequest.setKey("1");
        zcrudRequest.setCommand("getRecord");
        
        this.testRequest(
        		DefaultCRUDManager.COMMAND_GET_URL_PARAMETER, 
        		"SimpleGetZCrudRequest", 
        		zcrudRequest);
	}

}
