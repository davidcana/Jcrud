package org.github.davidcana.jcrud.core.optionsFiles;

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
import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import freemarker.template.TemplateException;

public class OptionsFilesTest {
	
	public static final String TESTS_PATH = "/optionsFiles/";
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRequest() throws ClassNotFoundException, IOException, TemplateException {
		
        long start = System.currentTimeMillis();
        
        OptionsFileGeneratorBuilder.getInstance().run();

        long elapsed = System.currentTimeMillis() - start;
        System.err.println("tested in " + elapsed + " ms");
	}

}
