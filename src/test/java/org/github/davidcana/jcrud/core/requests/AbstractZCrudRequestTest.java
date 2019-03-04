package org.github.davidcana.jcrud.core.requests;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import org.github.davidcana.jcrud.core.DefaultCRUDManager;
import org.github.davidcana.jcrud.core.requests.ZCrudRequest;
import org.github.davidcana.jcrud.storages.SimpleVoidStorage;
import org.junit.After;
import org.junit.Before;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

abstract public class AbstractZCrudRequestTest {
	
	public static final String TESTS_PATH = "/requests/";
	
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
        		this.getResourceReader(TESTS_PATH, test), 
        		SimpleVoidStorage.getInstance()
        );
        
        assertEquals("failure - strings are not equal", expected, zCrudRequest);

        long elapsed = System.currentTimeMillis() - start;
        System.err.println(test + ": tested in " + elapsed + " ms");
	}
	
	private Reader getResourceReader(String testsPath, String test) {
		
		String jsonPath = TESTS_PATH + test + ".json";
        return new BufferedReader(
        		new InputStreamReader(
        				this.getClass().getResourceAsStream(jsonPath)
        		)
        );
	}

}
