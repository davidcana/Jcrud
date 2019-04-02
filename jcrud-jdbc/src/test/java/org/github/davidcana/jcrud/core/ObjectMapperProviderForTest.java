package org.github.davidcana.jcrud.core;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.github.davidcana.jcrud.core.responses.AbstractZCrudResponseTest;

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
	
	public ClientServerTalking<?, ? ,? ,?> getClientServerTalking(String json, AbstractZCrudResponseTest<?, ?, ?, ?> zcrudResponseTest) throws IOException, JsonParseException, JsonMappingException {
		
		Type[] actualTypeArguments = ((ParameterizedType) zcrudResponseTest.getClass().getGenericSuperclass()).getActualTypeArguments();
		JavaType javaType = this.get().getTypeFactory().constructParametricType(
				ClientServerTalking.class, 
				(Class<?>) actualTypeArguments[0],
				(Class<?>) actualTypeArguments[1],
				(Class<?>) actualTypeArguments[2],
				(Class<?>) actualTypeArguments[3]
		);
				
		return (ClientServerTalking<?, ? ,? ,?>) this.get().readValue(json, javaType);
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
