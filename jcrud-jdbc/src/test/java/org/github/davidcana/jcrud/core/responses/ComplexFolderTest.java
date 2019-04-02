package org.github.davidcana.jcrud.core.responses;

import org.github.davidcana.jcrud.core.StorageResolverForTests;
import org.github.davidcana.jcrud.core.model.Complex;
import org.github.davidcana.jcrud.core.model.storages.ComplexJDBCStorage;
import org.junit.Test;

public class ComplexFolderTest extends AbstractZCrudResponseTest<Complex, Integer, Complex, Complex> {

	@Test
	public void test() throws Exception {
        
        this.testTalkingFolder(
        		"complex",
        		new StorageResolverForTests(
        				ComplexJDBCStorage.getInstance()
        		)
        );
	}

}
