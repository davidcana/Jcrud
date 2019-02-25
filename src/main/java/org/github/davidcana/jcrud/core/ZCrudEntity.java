package org.github.davidcana.jcrud.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public interface ZCrudEntity {
	
	@JsonIgnore
	public String getKey();
	
	@JsonIgnore
	public void setKey(String key);
	
	@JsonProperty
	public void setParentKey(String key);
}
