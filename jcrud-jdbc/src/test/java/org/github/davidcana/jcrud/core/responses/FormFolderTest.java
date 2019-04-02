package org.github.davidcana.jcrud.core.responses;

import java.util.HashMap;
import java.util.Map;

import org.github.davidcana.jcrud.core.StorageResolverForTests;
import org.github.davidcana.jcrud.core.model.Form;
import org.github.davidcana.jcrud.core.model.OriginalMember;
import org.github.davidcana.jcrud.core.model.filters.MemberFilter;
import org.github.davidcana.jcrud.core.model.storages.FormJDBCStorage;
import org.github.davidcana.jcrud.core.model.storages.OriginalMemberJDBCStorage;
import org.github.davidcana.jcrud.core.requests.GetZCrudRequest;
import org.github.davidcana.jcrud.core.requests.ListZCrudRequest;
import org.github.davidcana.jcrud.core.requests.UpdateZCrudRequest;
import org.github.davidcana.jcrud.storages.Storage;
import org.junit.Test;

public class FormFolderTest extends AbstractZCrudResponseTest<Form, Integer, MemberFilter, OriginalMember> {

	@Test
	public void test() throws Exception {
        
		Map<Class<?>, Storage<?, ?, ?>> storages = new HashMap<>();
		storages.put(GetZCrudRequest.class, FormJDBCStorage.getInstance());
		storages.put(ListZCrudRequest.class, OriginalMemberJDBCStorage.getInstance());
		storages.put(UpdateZCrudRequest.class, FormJDBCStorage.getInstance());
		
        this.testTalkingFolder(
        		"form",
        		new StorageResolverForTests(storages)
        );
	}

}
