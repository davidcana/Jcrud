package org.github.davidcana.jcrud.core.requests;

import java.util.List;
import java.util.Map;

import org.github.davidcana.jcrud.core.ZCrudEntity;

public class ZCrudRecords<T extends ZCrudEntity> implements IZCrudRecords<T> {
	
	private List<T> newRecords;
	private List<String> recordsToRemove;
	private Map<String, T> existingRecords;
	
	public ZCrudRecords(){}

	public List<T> getNewRecords() {
		return newRecords;
	}

	public void setNewRecords(List<T> newRecords) {
		this.newRecords = newRecords;
	}

	public List<String> getRecordsToRemove() {
		return recordsToRemove;
	}

	public void setRecordsToRemove(List<String> recordsToRemove) {
		this.recordsToRemove = recordsToRemove;
	}

	public Map<String, T> getExistingRecords() {
		return existingRecords;
	}

	public void setExistingRecords(Map<String, T> existingRecords) {
		this.existingRecords = existingRecords;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((existingRecords == null) ? 0 : existingRecords.hashCode());
		result = prime * result + ((newRecords == null) ? 0 : newRecords.hashCode());
		result = prime * result + ((recordsToRemove == null) ? 0 : recordsToRemove.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ZCrudRecords other = (ZCrudRecords) obj;
		if (existingRecords == null) {
			if (other.existingRecords != null)
				return false;
		} else if (!existingRecords.equals(other.existingRecords))
			return false;
		if (newRecords == null) {
			if (other.newRecords != null)
				return false;
		} else if (!newRecords.equals(other.newRecords))
			return false;
		if (recordsToRemove == null) {
			if (other.recordsToRemove != null)
				return false;
		} else if (!recordsToRemove.equals(other.recordsToRemove))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ZCrudRecords [newRecords=" + newRecords + ", recordsToRemove=" + recordsToRemove + ", existingRecords="
				+ existingRecords + "]";
	}

	public void setParentKey(String key) {
		/*
		for (Map.Entry<String, T> entry : this.existingRecords.entrySet()){
			T existingRecord = entry.getValue();
			existingRecord.setParentKey(key);
		}
		*/
		for (T newRecord : this.newRecords){
			newRecord.setParentKey(key);
		}
	}
}
