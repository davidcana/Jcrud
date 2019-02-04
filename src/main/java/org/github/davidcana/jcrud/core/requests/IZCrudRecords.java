package org.github.davidcana.jcrud.core.requests;

import java.util.List;
import java.util.Map;

import org.github.davidcana.jcrud.core.ZCrudEntity;

public interface IZCrudRecords<T extends ZCrudEntity> {

	public List<T> getNewRecords();
	
	public Map<String, T> getExistingRecords();
	
	public List<String> getRecordsToRemove();
	
}
