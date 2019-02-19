package org.github.davidcana.jcrud.storages;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import org.github.davidcana.jcrud.core.ZCrudEntity;
import org.github.davidcana.jcrud.core.options.Option;
import org.github.davidcana.jcrud.core.options.OptionProvider;

abstract public class AbstractStorage<T extends ZCrudEntity, K> implements Storage<T, K> {
	
	protected Class<T> clazz;
	
	protected AbstractStorage(){
		this.clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	@Override
	public List<Option<K>> getAllAsOptions() throws StorageException {
		
		try {
			List<Option<K>> result = new ArrayList<>();
			
			for (T object : this.getAll()){
				OptionProvider<K> optionProvider = (OptionProvider<K>) object;
				result.add(
						optionProvider.buildOption());
			}
			
			return result;
			
		} catch (ClassCastException e) {
			throw new StorageException(e);
		}
	}

	@Override
	public Class<?> getDeserializeClass() {
		return this.clazz;
	}
}
