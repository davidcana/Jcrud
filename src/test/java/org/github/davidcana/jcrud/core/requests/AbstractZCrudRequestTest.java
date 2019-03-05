package org.github.davidcana.jcrud.core.requests;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;
import java.net.URL;

import org.github.davidcana.jcrud.core.DefaultCRUDManager;
import org.github.davidcana.jcrud.core.ObjectMapperProviderForTest;
import org.github.davidcana.jcrud.core.requests.ZCrudRequest;
import org.github.davidcana.jcrud.storages.SimpleVoidStorage;
import org.junit.After;
import org.junit.Before;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

abstract public class AbstractZCrudRequestTest {
	
	public static final String TESTS_PATH = "/requests/";
	private static final String NEW_FILE_SUFFIX = ".new";
	
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
        
        //assertEquals("failure - strings are not equal", expected, zCrudRequest);
		if (! expected.equals(zCrudRequest)){
		    fail(
		    		"unexpected results: see " + this.saveNew(test, zCrudRequest)
		    );
		}

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
	
	protected String saveNew(String test, ZCrudRequest zCrudRequest) throws IOException {
		
        return this.saveNew(
        		test, 
        		this.buildBuffer(zCrudRequest)
        );
	}
	
	private StringBuilder buildBuffer(ZCrudRequest zCrudRequest) throws IOException {
		
		String json = ObjectMapperProviderForTest.getInstance().get().writerWithDefaultPrettyPrinter().writeValueAsString(zCrudRequest);
		
		StringReader reader = new StringReader(json);
		
		int intValueOfChar;
	    StringBuilder buffer = new StringBuilder();
	    while ((intValueOfChar = reader.read()) != -1) {
	        buffer.append((char) intValueOfChar);
	    }
	    reader.close();
	    
		return buffer;
	}
	
	/**/
	protected String saveNew(String test, StringBuilder buffer) throws IOException {
		
		URL resource = getClass().getResource(
				this.buildResourceString(test)
		);
    	File parent = (new File( resource.getFile() ) ).getParentFile().getParentFile();
    	String newFileName = parent.getPath() + this.buildNewFileName(test);
		File newFile = new File(newFileName);
    	newFile.createNewFile();
    	 
        this.writeBufferToFile(buffer, newFile);
        
        return newFileName;
	}
	
	private void writeBufferToFile(StringBuilder buffer, File file) throws IOException {
		
		Writer fileWriter = new FileWriter(file);
        fileWriter.write(buffer.toString());
        fileWriter.close();
	}
	
	private String buildNewFileName(String test) {
		return this.buildResourceString(test) + NEW_FILE_SUFFIX;
	}
	
	private String buildResourceString(String test) {
		//return "/" + test;
		return "/requests/" + test + ".json";
	}
}
