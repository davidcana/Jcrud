package org.github.davidcana.jcrud.storages;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.atteo.classindex.ClassIndex;
import org.github.davidcana.jcrud.core.annotations.JCRUDEntity;

public class StorageResolver {
	
	@SuppressWarnings("rawtypes")
	private Map<String, Storage> storages = new HashMap<>();
	static private StorageResolver instance;
	
	private StorageResolver() throws StorageException {
		this.init();
	}
	
	public void init() throws StorageException {
		
		try {
			this.storages = new HashMap<>();
			
			Iterator<Class<?>> i = ClassIndex.getAnnotated(JCRUDEntity.class).iterator();
			while (i.hasNext()){
				Class<?> clazz = i.next();
				JCRUDEntity jcrudEntity = clazz.getAnnotation(JCRUDEntity.class);
				
				String key = resolveKey(jcrudEntity, clazz);
				@SuppressWarnings("rawtypes")
				Storage storage = this.resolveStorage(jcrudEntity);
				
				this.storages.put(key, storage);
			}
			
			for (Map.Entry<String, Storage> entry : this.storages.entrySet()){
				Storage storage = entry.getValue();
				storage.configure();
			}
			
		} catch (Exception e) {
			throw new StorageException(e);
		}
	}
	
	@SuppressWarnings("rawtypes")
	private Storage resolveStorage(JCRUDEntity jcrudEntity) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		Class<? extends Storage> storageClass = jcrudEntity.storage();
		return this.resolve(storageClass);
	}

	public Storage resolve(Class<? extends Storage> storageClass) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		
		Method method = storageClass.getMethod("getInstance");
		return (Storage) method.invoke(method);
	}

	static private String resolveKey(JCRUDEntity jcrudEntity, Class<?> clazz) {
		
		return !jcrudEntity.urlParameter().equals("")?
				jcrudEntity.urlParameter(): 
				buildDefaultUrlParameter(clazz);
	}
	
	static private String buildDefaultUrlParameter(Class<?> clazz){
		
		String simpleClassName = clazz.getSimpleName();
		return (simpleClassName.substring(0, 1)).toLowerCase() + simpleClassName.substring(1);
	}
	
	@SuppressWarnings("rawtypes")
	public Storage get(String key){
		
		Storage storage = this.storages.get(key);
		
		if (storage == null){
			throw new IllegalArgumentException("Storage with key '" + key + "' not found by StorageResolver!");
		}
		
		return storage;
	}
	
	static public StorageResolver getInstance() throws StorageException {
		
		if ( instance == null ){
			instance = new StorageResolver();
		}
		
		return instance;
	}

}
