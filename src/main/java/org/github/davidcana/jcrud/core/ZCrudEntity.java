package org.github.davidcana.jcrud.core;

public interface ZCrudEntity {
	
	public void initNewInstance();
	
	public String getKey();
	public void setParentKey(String key);
}
