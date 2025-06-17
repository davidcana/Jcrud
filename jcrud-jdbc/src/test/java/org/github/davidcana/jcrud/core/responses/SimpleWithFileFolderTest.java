package org.github.davidcana.jcrud.core.responses;

import org.github.davidcana.jcrud.core.StorageResolverForTests;
import org.github.davidcana.jcrud.core.model.Simple;
import org.github.davidcana.jcrud.core.model.storages.SimpleJDBCStorage;
import org.junit.Test;

public class SimpleWithFileFolderTest extends AbstractZCrudResponseTest<Simple, Integer, Simple, Simple> {

	@Test
	public void test() throws Exception {
        
        this.testTalkingFolder(
        		"simple",
        		new StorageResolverForTests(
        				SimpleJDBCStorage.getInstance()
        		)
        );
	}

}
