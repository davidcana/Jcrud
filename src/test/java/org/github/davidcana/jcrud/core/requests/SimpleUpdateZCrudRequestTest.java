package org.github.davidcana.jcrud.core.requests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.github.davidcana.jcrud.core.DefaultCRUDManager;
import org.github.davidcana.jcrud.core.model.Simple;
import org.github.davidcana.jcrud.core.requests.UpdateZCrudRequest;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class SimpleUpdateZCrudRequestTest extends AbstractZCrudRequestTest {

	@Test
	public void testBuildResponse() throws JsonParseException, JsonMappingException, IOException {
		
        // Build zcrudRequest
		UpdateZCrudRequest<Simple> zcrudRequest = new UpdateZCrudRequest<Simple>();
		zcrudRequest.setNewRecords(new ArrayList<>());
		zcrudRequest.setRecordsToRemove(new ArrayList<>());
		zcrudRequest.setUrl("CRUDManager.do?cmd=BATCH_UPDATE&table=simple"); //TODO Remove URL
		zcrudRequest.setCommand("batchUpdate");
		
		Simple simple = new Simple();
		simple.setName("Simple 1b");
		Map<String, Simple> existingRecords = new HashMap<>();
		existingRecords.put("1", simple);
		zcrudRequest.setExistingRecords(existingRecords);
		
		/*
		{
		    "existingRecords": {
		        "1": {
		            "name": "Simple 1b"
		        }
		    },
		    "newRecords": [],
		    "recordsToRemove": [],
		    "url": "CRUDManager.do?cmd=BATCH_UPDATE&table=simple",
		    "command": "batchUpdate"
		}
		*/
		
        this.testRequest(
        		DefaultCRUDManager.COMMAND_UPDATE_URL_PARAMETER, 
        		"SimpleUpdateZCrudRequest", 
        		zcrudRequest);
	}

}
