package org.github.davidcana.jcrud.core.responses;

import org.github.davidcana.jcrud.core.ClientServerTalking;
import org.github.davidcana.jcrud.core.StorageResolverForTests;
import org.github.davidcana.jcrud.core.model.Simple;
import org.github.davidcana.jcrud.core.model.storages.SimpleJDBCStorage;
import org.junit.Test;

import com.fasterxml.jackson.core.type.TypeReference;

public class SimpleFolderTest extends AbstractZCrudResponseTest {

	@Test
	public void test() throws Exception {
        
        this.testTalkingFolder(
        		"simple",
        		new StorageResolverForTests(
        				SimpleJDBCStorage.getInstance()
        		),
        		new TypeReference<ClientServerTalking<Simple, Simple, Simple>>() {}
        );
	}

}
