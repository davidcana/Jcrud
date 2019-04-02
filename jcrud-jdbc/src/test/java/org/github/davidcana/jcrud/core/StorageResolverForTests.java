package org.github.davidcana.jcrud.core;

import java.util.Map;

import org.github.davidcana.jcrud.storages.Storage;

public class StorageResolverForTests {

	private Map<Class<?>, Storage<?, ?, ?>> storages;
	private Storage<?, ?, ?> defaultStorage;
	
	public StorageResolverForTests(Map<Class<?>, Storage<?, ?, ?>> storages){
		this.storages = storages;
	}
	
	public StorageResolverForTests(Storage<?, ?, ?> defaultStorage){
		this.defaultStorage = defaultStorage;
	}
	
	public StorageResolverForTests(Storage<?, ?, ?> defaultStorage, Map<Class<?>, Storage<?, ?, ?>> storages){
		this.defaultStorage = defaultStorage;
		this.storages = storages;
	}
	
	public Storage<?, ?, ?> resolve(Class<?> clazz){
		
		if (this.storages != null && this.storages.containsKey(clazz)){
			return this.storages.get(clazz);
		}
		
		if (this.defaultStorage != null){
			return this.defaultStorage;
		}
		
		throw new IllegalArgumentException("Unable to resolve class " + clazz + " in StorageResolverForTests " + this);
	}

	@Override
	public String toString() {
		return "StorageResolverForTests [storages=" + storages + ", defaultStorage=" + defaultStorage + "]";
	}
	
}
