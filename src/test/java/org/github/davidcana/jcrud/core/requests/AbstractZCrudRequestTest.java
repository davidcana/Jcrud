package org.github.davidcana.jcrud.core.requests;

import static org.junit.Assert.*;

import java.io.IOException;

import org.github.davidcana.jcrud.core.AbstractTest;
import org.github.davidcana.jcrud.core.DefaultCRUDManager;
import org.github.davidcana.jcrud.core.ObjectMapperProviderForTest;
import org.github.davidcana.jcrud.core.requests.ZCrudRequest;
import org.github.davidcana.jcrud.storages.SimpleVoidStorage;
import org.junit.After;
import org.junit.Before;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

abstract public class AbstractZCrudRequestTest extends AbstractTest {
	
	public static final String TESTS_PATH = "requests/";
	private static final String FILE_EXTENSION = ".json";
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	protected void testRequest(String cmd, String test, ZCrudRequest expected) throws JsonParseException, JsonMappingException, IOException {
		
        long start = System.currentTimeMillis();
        
        // Build expectedZcrudRequest
        ZCrudRequest zCrudRequest = DefaultCRUDManager.getInstance().getRequest(
        		cmd, 
        		this.getResourceReader(TESTS_PATH, test, FILE_EXTENSION), 
        		SimpleVoidStorage.getInstance()
        );
        
        // Check it!
		if (! expected.equals(zCrudRequest)){
		    fail(
		    		"unexpected results: see " + this.saveNew(test, zCrudRequest)
		    );
		}

        long elapsed = System.currentTimeMillis() - start;
        System.err.println(test + ": tested in " + elapsed + " ms");
	}

	protected String saveNew(String test, ZCrudRequest zCrudRequest) throws IOException {
		
        String buffer = this.buildBuffer(
				ObjectMapperProviderForTest.getInstance().get().writerWithDefaultPrettyPrinter().writeValueAsString(zCrudRequest)
		);
				
		return this.saveNew(test, buffer, FILE_EXTENSION, TESTS_PATH);
	}
	
}
