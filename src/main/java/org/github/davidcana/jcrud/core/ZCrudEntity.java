package org.github.davidcana.jcrud.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public interface ZCrudEntity {
	
	public void initNewInstance();
	
	@JsonIgnore
	public String getKey();
	
	@JsonProperty
	public void setParentKey(String key);
}
