package org.github.davidcana.jcrud.core;

import java.io.IOException;
import java.time.LocalTime;

import org.github.davidcana.jcrud.core.CRUD.CRUDHelper;
import org.github.davidcana.jcrud.core.JSON.deserializers.LocalTimeDeserializer;
import org.github.davidcana.jcrud.core.JSON.serializers.LocalTimeSerializer;
import org.github.davidcana.jcrud.core.requests.GetZCrudRequest;
import org.github.davidcana.jcrud.core.requests.ListZCrudRequest;
import org.github.davidcana.jcrud.core.requests.UpdateZCrudRequest;
import org.github.davidcana.jcrud.core.responses.GetZCrudResponse;
import org.github.davidcana.jcrud.core.responses.ListZCrudResponse;
import org.github.davidcana.jcrud.core.responses.UpdateZCrudResponse;

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
		
		return mapper;
	}
	
	public ListZCrudRequest getListRequest(String json, CRUDHelper crudHelper) throws IOException, JsonParseException, JsonMappingException {
		return (ListZCrudRequest) this.get().readValue(json, ListZCrudRequest.class);
	}
	public GetZCrudRequest getGetRequest(String json, CRUDHelper crudHelper) throws IOException, JsonParseException, JsonMappingException {
		return (GetZCrudRequest) this.get().readValue(json, GetZCrudRequest.class);
	}
	@SuppressWarnings("rawtypes")
	public UpdateZCrudRequest getUpdateRequest(String json, CRUDHelper crudHelper) throws IOException, JsonParseException, JsonMappingException {
		
		JavaType type = this.get().getTypeFactory().constructParametricType(UpdateZCrudRequest.class, crudHelper.getDeserializeClass());
		return (UpdateZCrudRequest) this.get().readValue(json, type);
	}
	
	@SuppressWarnings("rawtypes")
	public ListZCrudResponse getListResponse(String json, CRUDHelper crudHelper) throws IOException, JsonParseException, JsonMappingException {
		
		JavaType type = this.get().getTypeFactory().constructParametricType(ListZCrudResponse.class, crudHelper.getDeserializeClass());
		return (ListZCrudResponse) this.get().readValue(json, type);
	}
	@SuppressWarnings("rawtypes")
	public GetZCrudResponse getGetResponse(String json, CRUDHelper crudHelper) throws IOException, JsonParseException, JsonMappingException {
		
		JavaType type = this.get().getTypeFactory().constructParametricType(GetZCrudResponse.class, crudHelper.getDeserializeClass());
		return (GetZCrudResponse) this.get().readValue(json, type);
	}
	@SuppressWarnings("rawtypes")
	public UpdateZCrudResponse getUpdateResponse(String json, CRUDHelper crudHelper) throws IOException, JsonParseException, JsonMappingException {
		
		JavaType type = this.get().getTypeFactory().constructParametricType(UpdateZCrudResponse.class, crudHelper.getDeserializeClass());
		return (UpdateZCrudResponse) this.get().readValue(json, type);
	}
	
	static public ObjectMapperProvider getInstance(){
		
		if ( instance == null ){
			instance = new ObjectMapperProvider();
		}
		
		return instance;
	}

}
