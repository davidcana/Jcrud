package org.github.davidcana.jcrud.core.optionsFiles;

import static org.junit.Assert.fail;

import java.io.IOException;

import org.github.davidcana.jcrud.core.AbstractTest;
import org.github.davidcana.jcrud.core.utils.CoreUtils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class AbstractOptionsFilesTest extends AbstractTest {
	
	private static final String TESTS_PATH = "optionsFiles/";
	private static final String FILE_EXTENSION = ".js";
	/*
	private static final boolean STANDARD_OUT_MODE = false;
	private static final String ROOT_PATH = "src/test/java/";
	
	@BeforeClass
	static public void setUp() throws Exception {
		OptionsFilesBuilder.getInstance().run(ROOT_PATH, STANDARD_OUT_MODE);
	}
	*/
	
	protected void testJavascript(String test, String expectedPath) throws JsonParseException, JsonMappingException, IOException {
		
        long start = System.currentTimeMillis();
        
        // Get contents of files
        String expectedString = this.getStringFromFile(TESTS_PATH, test, FILE_EXTENSION);
        String testString = CoreUtils.getInstance().getStringFromProjectPath(expectedPath);
        
        // Check it!
		if (! expectedString.equals(testString)){
		    fail(
		    		"unexpected results: see " + this.saveNew(test, testString)
		    );
		}

        long elapsed = System.currentTimeMillis() - start;
        System.err.println(test + ": tested in " + elapsed + " ms");
	}

	protected String saveNew(String test, String string) throws IOException {
		return this.saveNew(test, string, FILE_EXTENSION, TESTS_PATH);
	}
	
}
