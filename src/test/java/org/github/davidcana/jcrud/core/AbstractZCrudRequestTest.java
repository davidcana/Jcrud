package org.github.davidcana.jcrud.core;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import org.github.davidcana.jcrud.core.DefaultCRUDManager;
import org.github.davidcana.jcrud.core.model.CRUD.SimpleCRUDHelper;
import org.github.davidcana.jcrud.core.requests.ZCrudRequest;
import org.junit.After;
import org.junit.Before;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

abstract public class AbstractZCrudRequestTest {

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
        		this.getResourceReader(test), 
        		SimpleCRUDHelper.getInstance()
        );
        
        assertEquals("failure - strings are not equal", expected, zCrudRequest);

        long elapsed = System.currentTimeMillis() - start;
        System.err.println(test + ": tested in " + elapsed + " ms");
	}
	/*
	private URL getResource(String test) {
		
		String json = test + "-client" + ".json";
        return this.getClass().getResource( "/" + json );
	}*/
	
	private Reader getResourceReader(String test) {
		
		String json = test + "-client" + ".json";
        return new BufferedReader(
        		new InputStreamReader(
        				this.getClass().getResourceAsStream( "/" + json )));
	}
	/*
    static final String filterText(String source){
    	
    	String text = source.replaceAll("\\r|\\n|\\t", "");
    	
    	StringBuilder sb = new StringBuilder();

    	Scanner scanner = new Scanner(text);
    	while (scanner.hasNextLine()) {
    	  String line = scanner.nextLine();
    	  sb.append( 
    			  line.trim().replaceAll( 
    					  "\\s+", " " ) );
    	}
    	scanner.close();
    	
    	return sb.toString();
    }
    
    static final String fixCRLF(String source){
    	
        StringBuffer buf = new StringBuffer(source.length());
        StringTokenizer chunks = new StringTokenizer(source, "\r\n");
        
        while (chunks.hasMoreTokens()) {
            buf.append(chunks.nextToken());
            if (buf.charAt(buf.length() - 1) != '\n') {
                buf.append('\n');
            }
        }
        
        return buf.toString();
    }*/
}
