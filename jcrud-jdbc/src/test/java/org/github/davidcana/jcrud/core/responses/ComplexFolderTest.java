package org.github.davidcana.jcrud.core.responses;

import org.github.davidcana.jcrud.core.ClientServerTalking;
import org.github.davidcana.jcrud.core.StorageResolverForTests;
import org.github.davidcana.jcrud.core.model.Complex;
import org.github.davidcana.jcrud.core.model.storages.ComplexJDBCStorage;
import org.junit.Test;

import com.fasterxml.jackson.core.type.TypeReference;

public class ComplexFolderTest extends AbstractZCrudResponseTest {

	@Test
	public void test() throws Exception {
        
        this.testTalkingFolder(
        		"complex",
        		new StorageResolverForTests(
        				ComplexJDBCStorage.getInstance()
        		),
        		new TypeReference<ClientServerTalking<Complex, Complex, Complex>>() {}
        );
	}

}
