package org.github.davidcana.jcrud.core.optionsFiles;

import org.junit.Test;

public class OptionsFilesTest extends AbstractOptionsFilesTest {

	@Test
	public void test() throws Exception {
        
		this.testJavascript(
        		"simple", 
        		"target/classes/simple.js"
        );
	}

	
}
