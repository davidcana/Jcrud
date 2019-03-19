package org.github.davidcana.jcrud.core.responses;

import org.github.davidcana.jcrud.core.model.storages.Complex2JDBCStorage;
import org.junit.Test;

public class Complex2FolderTest extends AbstractZCrudResponseTest {

	@Test
	public void test() throws Exception {
        
        this.testTalkingFolder(
        		"complex2",
        		Complex2JDBCStorage.getInstance()
        );
	}

}
