package org.github.davidcana.jcrud.core.responses;

import org.github.davidcana.jcrud.core.model.storages.Simple2JDBCStorage;
import org.junit.Test;

public class Simple2FolderTest extends AbstractZCrudResponseTest {

	@Test
	public void test() throws Exception {
        
        this.testTalkingFolder(
        		"simple2",
        		Simple2JDBCStorage.getInstance()
        );
	}

}
