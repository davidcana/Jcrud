package org.github.davidcana.jcrud.core;

import java.time.LocalTime;

import org.github.davidcana.jcrud.core.JSON.deserializers.LocalTimeDeserializer;
import org.github.davidcana.jcrud.core.JSON.serializers.LocalTimeSerializer;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class ObjectMapperProvider {
	
	static private ObjectMapperProvider instance;
	
	private ObjectMapper mapper;
	
	public ObjectMapperProvider(){}
	
	public ObjectMapper get(){
		
		if (this.mapper == null){
			this.instanceObjectMapper();
		}
		
		return this.mapper;
	}
	
	private void instanceObjectMapper(){
		
		this.mapper = new ObjectMapper();

		SimpleModule module1 = new SimpleModule("LocalTimeSerializer", new Version(1, 0, 0, null, null, null));
		module1.addSerializer(LocalTime.class, new LocalTimeSerializer());
		this.mapper.registerModule(module1);
		
		SimpleModule module2 = new SimpleModule("LocalTimeDeserializer", new Version(1, 0, 0, null, null, null));
		module2.addDeserializer(LocalTime.class, new LocalTimeDeserializer());
		this.mapper.registerModule(module2);
	}
	
	static public ObjectMapperProvider getInstance(){
		
		if ( instance == null ){
			instance = new ObjectMapperProvider();
		}
		
		return instance;
	}

}
