package org.github.davidcana.jcrud.core;

import java.io.IOException;

import org.github.davidcana.jcrud.core.requests.ZCrudRequest;
import org.github.davidcana.jcrud.storages.Storage;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapperProviderForTest {
	
	static private ObjectMapperProviderForTest instance;
	
	private ObjectMapper mapper;
	
	public ObjectMapperProviderForTest(){}
	
	public ObjectMapper get(){
		
		if (this.mapper == null){
			this.mapper = instanceObjectMapper();
		}
		
		return this.mapper;
	}

	public ZCrudRequest<?, ?, ?> getZCrudRequest(String json, Storage<?, ?, ?> storage) throws IOException, JsonParseException, JsonMappingException {
		
		JavaType type = this.get().getTypeFactory().constructParametricType(ZCrudRequest.class, storage.getDeserializeClass());
		return (ZCrudRequest<?, ?, ?>) this.get().readValue(json, type);
	}
	
	static private ObjectMapper instanceObjectMapper(){
		
		ObjectMapper mapper = ObjectMapperProvider.instanceObjectMapper();
		
		//mapper.enableDefaultTyping();
		//mapper.enable(SerializationFeature.INDENT_OUTPUT);
		
		return mapper;
	}
	
	static public ObjectMapperProviderForTest getInstance(){
		
		if ( instance == null ){
			instance = new ObjectMapperProviderForTest();
		}
		
		return instance;
	}

}
