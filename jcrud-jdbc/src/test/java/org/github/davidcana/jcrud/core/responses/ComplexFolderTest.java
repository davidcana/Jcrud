package org.github.davidcana.jcrud.core.responses;

import org.github.davidcana.jcrud.core.model.storages.ComplexJDBCStorage;
import org.junit.Test;

public class ComplexFolderTest extends AbstractZCrudResponseTest {

	@Test
	public void test() throws Exception {
        
        this.testTalkingFolder(
        		"complex",
        		ComplexJDBCStorage.getInstance()
        );
	}

}
