package org.github.davidcana.jcrud.storages;

import java.util.ArrayList;
import java.util.List;

import org.github.davidcana.jcrud.core.ZCrudEntity;
import org.github.davidcana.jcrud.core.options.Option;
import org.github.davidcana.jcrud.core.options.OptionProvider;

abstract public class AbstractStorage<T extends ZCrudEntity, K> implements Storage<T, K> {

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
}
