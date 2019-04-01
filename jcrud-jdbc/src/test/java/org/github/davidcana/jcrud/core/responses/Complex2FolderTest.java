package org.github.davidcana.jcrud.core.responses;

import org.github.davidcana.jcrud.core.ClientServerTalking;
import org.github.davidcana.jcrud.core.StorageResolverForTests;
import org.github.davidcana.jcrud.core.model.Complex2;
import org.github.davidcana.jcrud.core.model.storages.Complex2JDBCStorage;
import org.junit.Test;

import com.fasterxml.jackson.core.type.TypeReference;

public class Complex2FolderTest extends AbstractZCrudResponseTest {

	@Test
	public void test() throws Exception {
        
        this.testTalkingFolder(
        		"complex2",
        		new StorageResolverForTests(
        				Complex2JDBCStorage.getInstance()
        		),
        		new TypeReference<ClientServerTalking<Complex2, Complex2, Complex2>>() {}
        );
	}

}
