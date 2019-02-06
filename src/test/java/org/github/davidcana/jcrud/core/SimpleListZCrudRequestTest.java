package org.github.davidcana.jcrud.core;

import java.io.IOException;
import java.util.HashMap;

import org.github.davidcana.jcrud.core.requests.ListZCrudRequest;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class SimpleListZCrudRequestTest extends AbstractZCrudRequestTest {

	@Test
	public void testBuildResponse() throws JsonParseException, JsonMappingException, IOException {
		
        // Build zcrudRequest
        ListZCrudRequest zcrudRequest = new ListZCrudRequest();
        zcrudRequest.setFilter(new HashMap<>());
        zcrudRequest.setPageNumber(1);
        zcrudRequest.setPageSize(5);
        zcrudRequest.setSortFieldId("id");
        zcrudRequest.setSortType("asc");
        zcrudRequest.setCommand("listRecords");
        
        this.testRequest(
        		DefaultCRUDManager.COMMAND_LIST_URL_PARAMETER, 
        		"SimpleListZCrudRequest", 
        		zcrudRequest);
	}

}
