package org.github.davidcana.jcrud.core.responses;

import org.github.davidcana.jcrud.storages.JDBC.SimpleJDBCStorage;
import org.junit.Test;

public class SimpleFolderTest extends AbstractZCrudResponseTest {

	@Test
	public void test() throws Exception {
        
        this.testTalkingFolder(
        		"simple",
        		SimpleJDBCStorage.getInstance()
        );
	}

}