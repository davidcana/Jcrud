package org.github.davidcana.jcrud.core.optionsFiles;

import org.junit.Test;

public class OptionsFilesTest extends AbstractOptionsFilesTest {

	@Test
	public void test() throws Exception {
		
        long start = System.currentTimeMillis();

        this.testJavascript("simple", "target/classes/simple.js");
        
        long elapsed = System.currentTimeMillis() - start;
        System.err.println("tested in " + elapsed + " ms");
	}

	
}
