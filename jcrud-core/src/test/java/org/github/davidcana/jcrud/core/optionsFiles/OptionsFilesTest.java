package org.github.davidcana.jcrud.core.optionsFiles;

import org.junit.Test;

public class OptionsFilesTest extends AbstractOptionsFilesTest {

	@Test
	public void testSimple() throws Exception {
        
		this.testJavascript(
        		"simple", 
        		"target/simple.js"
        );
	}
	
	@Test
	public void testSimple2() throws Exception {
        
		this.testJavascript(
        		"simple2", 
        		"target/simple2.js"
        );
	}
	
}
