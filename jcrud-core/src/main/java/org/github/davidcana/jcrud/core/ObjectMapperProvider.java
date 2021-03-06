package org.github.davidcana.jcrud.core;

import java.io.IOException;
import java.time.LocalTime;

import org.github.davidcana.jcrud.core.JSON.deserializers.LocalTimeDeserializer;
import org.github.davidcana.jcrud.core.JSON.serializers.LocalTimeSerializer;
import org.github.davidcana.jcrud.core.requests.GetZCrudRequest;
import org.github.davidcana.jcrud.core.requests.ListZCrudRequest;
import org.github.davidcana.jcrud.core.requests.UpdateZCrudRequest;
import org.github.davidcana.jcrud.storages.Storage;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class ObjectMapperProvider {
	
	static private ObjectMapperProvider instance;
	
	private ObjectMapper mapper;
	
	public ObjectMapperProvider(){}
	
	public ObjectMapper get(){
		
		if (this.mapper == null){
			this.mapper = instanceObjectMapper();
		}
		
		return this.mapper;
	}
	
	static ObjectMapper instanceObjectMapper(){
		
		ObjectMapper mapper = new ObjectMapper();

		SimpleModule module1 = new SimpleModule("LocalTimeSerializer", new Version(1, 0, 0, null, null, null));
		module1.addSerializer(LocalTime.class, new LocalTimeSerializer());
		mapper.registerModule(module1);
		
		SimpleModule module2 = new SimpleModule("LocalTimeDeserializer", new Version(1, 0, 0, null, null, null));
		module2.addDeserializer(LocalTime.class, new LocalTimeDeserializer());
		mapper.registerModule(module2);
		
		mapper.setSerializationInclusion(Include.NON_NULL);
		
		return mapper;
	}
	
	@SuppressWarnings("rawtypes")
	public ListZCrudRequest getListRequest(String json, Storage storage) throws IOException, JsonParseException, JsonMappingException {
		
		return (ListZCrudRequest) this.get().readValue(
				json, 
				this.constructParametricType(ListZCrudRequest.class, storage)
		);
	}
	/*
	public ListZCrudRequest getListRequest(String json, Storage storage) throws IOException, JsonParseException, JsonMappingException {
		
		TypeReference<?> typeReference = storage.getListRequestTypeReference();
		if (typeReference != null) {
			return (ListZCrudRequest) this.get().readValue(json, typeReference);
		}
		
		return (ListZCrudRequest) this.get().readValue(
				json, 
				this.constructParametricType(
						ListZCrudRequest.class, 
						storage.getFilterDeserializeClass(), 
						null
				)
		);
	}
	*/
	
	@SuppressWarnings("rawtypes")
	public GetZCrudRequest getGetRequest(String json, Storage storage) throws IOException, JsonParseException, JsonMappingException {
		
		return (GetZCrudRequest) this.get().readValue(
				json,
				this.constructParametricType(GetZCrudRequest.class, storage)
		);
	}
	/*
	public GetZCrudRequest getGetRequest(String json, Storage storage) throws IOException, JsonParseException, JsonMappingException {
		
		TypeReference<?> typeReference = storage.getGetRequestTypeReference();
		if (typeReference != null) {
			return (GetZCrudRequest) this.get().readValue(json, typeReference);
		}
		
		return (GetZCrudRequest) this.get().readValue(
				json,
				this.constructParametricType(
						GetZCrudRequest.class, 
						storage.getFilterDeserializeClass(), 
						null
				)
		);
	}
	*/
	
	@SuppressWarnings("rawtypes")
	public UpdateZCrudRequest getUpdateRequest(String json, Storage storage) throws IOException, JsonParseException, JsonMappingException {

		return (UpdateZCrudRequest) this.get().readValue(
				json, 
				this.constructParametricType(UpdateZCrudRequest.class, storage)
		);
	}
	/*
	public UpdateZCrudRequest getUpdateRequest(String json, Storage storage) throws IOException, JsonParseException, JsonMappingException {
		
		TypeReference<?> typeReference = storage.getUpdateRequestTypeReference();
		if (typeReference != null) {
			return (UpdateZCrudRequest) this.get().readValue(json, typeReference);
		}
		
		return (UpdateZCrudRequest) this.get().readValue(
				json, 
				this.constructParametricType(
						UpdateZCrudRequest.class, 
						storage.getDeserializeClass(), 
						storage.getFilterDeserializeClass()
				)
		);
	}
	*/
	/*
	private JavaType constructParametricType(Class<?> clazz, Class<?> deserializeClazz, Class<?> filterDeserializeClazz){
		
		return filterDeserializeClazz != null?
				this.get().getTypeFactory().constructParametricType(
						clazz, 
						deserializeClazz,
						filterDeserializeClazz
				):
				this.get().getTypeFactory().constructParametricType(
						clazz, 
						deserializeClazz
		);
	}
	*/
	/*
	private JavaType constructParametricType(Class<?> clazz, Storage storage){
		
		return this.get().getTypeFactory().constructParametricType(
						clazz, 
						storage.getDeserializeClass(),
						storage.getFilterDeserializeClass()
		);
	}
	*/
	private JavaType constructParametricType(Class<?> clazz, Storage<?, ?, ?> storage){
		
		return this.get().getTypeFactory().constructParametricType(
						clazz, 
						storage.getActualTypeArguments(0),
						storage.getActualTypeArguments(1),
						storage.getActualTypeArguments(2)
		);
	}
	
	/*
	@SuppressWarnings("rawtypes")
	public ListZCrudResponse getListResponse(String json, Storage storage) throws IOException, JsonParseException, JsonMappingException {
		
		JavaType type = this.get().getTypeFactory().constructParametricType(ListZCrudResponse.class, storage.getDeserializeClass());
		return (ListZCrudResponse) this.get().readValue(json, type);
	}
	@SuppressWarnings("rawtypes")
	public GetZCrudResponse getGetResponse(String json, Storage storage) throws IOException, JsonParseException, JsonMappingException {
		
		JavaType type = this.get().getTypeFactory().constructParametricType(GetZCrudResponse.class, storage.getDeserializeClass());
		return (GetZCrudResponse) this.get().readValue(json, type);
	}
	@SuppressWarnings("rawtypes")
	public UpdateZCrudResponse getUpdateResponse(String json, Storage storage) throws IOException, JsonParseException, JsonMappingException {
		
		JavaType type = this.get().getTypeFactory().constructParametricType(UpdateZCrudResponse.class, storage.getDeserializeClass());
		return (UpdateZCrudResponse) this.get().readValue(json, type);
	}
	*/
	
	static public ObjectMapperProvider getInstance(){
		
		if ( instance == null ){
			instance = new ObjectMapperProvider();
		}
		
		return instance;
	}

}
