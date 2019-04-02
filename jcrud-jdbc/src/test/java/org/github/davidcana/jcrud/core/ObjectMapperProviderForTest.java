package org.github.davidcana.jcrud.core;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
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

	public ClientServerTalking<?,?,?,?> getClientServerTalking(String json, TypeReference<?> typeReference) throws IOException, JsonParseException, JsonMappingException {
		return (ClientServerTalking<?,?,?,?>) this.get().readValue(json, typeReference);
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
