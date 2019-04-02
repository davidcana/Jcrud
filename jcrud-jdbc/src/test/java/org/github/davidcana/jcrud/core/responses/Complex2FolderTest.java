package org.github.davidcana.jcrud.core.responses;

import org.github.davidcana.jcrud.core.StorageResolverForTests;
import org.github.davidcana.jcrud.core.model.Complex2;
import org.github.davidcana.jcrud.core.model.storages.Complex2JDBCStorage;
import org.junit.Test;

public class Complex2FolderTest extends AbstractZCrudResponseTest<Complex2, Integer, Complex2, Complex2> {

	@Test
	public void test() throws Exception {
        
        this.testTalkingFolder(
        		"complex2",
        		new StorageResolverForTests(
        				Complex2JDBCStorage.getInstance()
        		)
        );
	}

}
