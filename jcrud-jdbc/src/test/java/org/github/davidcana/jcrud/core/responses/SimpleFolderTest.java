package org.github.davidcana.jcrud.core.responses;

import org.github.davidcana.jcrud.core.StorageResolverForTests;
import org.github.davidcana.jcrud.core.model.SimpleWithFile;
import org.github.davidcana.jcrud.core.model.storages.SimpleWithFileJDBCStorage;
import org.junit.Test;

public class SimpleFolderTest extends AbstractZCrudResponseTest<SimpleWithFile, Integer, SimpleWithFile, SimpleWithFile> {

	@Test
	public void test() throws Exception {
        
        this.testTalkingFolder(
        		"simpleWithFile",
        		new StorageResolverForTests(
        				SimpleWithFileJDBCStorage.getInstance()
        		)
        );
	}

}
