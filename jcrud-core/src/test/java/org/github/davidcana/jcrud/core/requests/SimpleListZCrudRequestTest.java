package org.github.davidcana.jcrud.core.requests;

import java.io.IOException;

import org.github.davidcana.jcrud.core.DefaultCRUDManager;
import org.github.davidcana.jcrud.core.model.Simple;
import org.github.davidcana.jcrud.core.requests.ListZCrudRequest;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class SimpleListZCrudRequestTest extends AbstractZCrudRequestTest {

	@Test
	public void testBuildResponse() throws JsonParseException, JsonMappingException, IOException {
		
        // Build zcrudRequest
        ListZCrudRequest<Simple> zcrudRequest = new ListZCrudRequest<>();
        zcrudRequest.setFilter(new Simple());
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
