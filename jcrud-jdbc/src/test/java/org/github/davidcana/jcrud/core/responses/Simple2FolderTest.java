package org.github.davidcana.jcrud.core.responses;

import org.github.davidcana.jcrud.core.StorageResolverForTests;
import org.github.davidcana.jcrud.core.model.Simple2;
import org.github.davidcana.jcrud.core.model.storages.Simple2JDBCStorage;
import org.junit.Test;

public class Simple2FolderTest extends AbstractZCrudResponseTest<Simple2, Integer, Simple2, Simple2> {

	@Test
	public void test() throws Exception {
        
        this.testTalkingFolder(
        		"simple2",
        		new StorageResolverForTests(
        				Simple2JDBCStorage.getInstance()
        		)
        );
	}

}
