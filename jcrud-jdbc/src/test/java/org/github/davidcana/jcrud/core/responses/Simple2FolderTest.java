package org.github.davidcana.jcrud.core.responses;

import org.github.davidcana.jcrud.core.ClientServerTalking;
import org.github.davidcana.jcrud.core.StorageResolverForTests;
import org.github.davidcana.jcrud.core.model.Simple2;
import org.github.davidcana.jcrud.core.model.storages.Simple2JDBCStorage;
import org.junit.Test;

import com.fasterxml.jackson.core.type.TypeReference;

public class Simple2FolderTest extends AbstractZCrudResponseTest {

	@Test
	public void test() throws Exception {
        
        this.testTalkingFolder(
        		"simple2",
        		new StorageResolverForTests(
        				Simple2JDBCStorage.getInstance()
        		),
        		new TypeReference<ClientServerTalking<Simple2, Simple2, Simple2>>() {}
        );
	}

}
