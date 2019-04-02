package org.github.davidcana.jcrud.core.requests;

import java.util.List;
import java.util.Map;

import org.github.davidcana.jcrud.core.ZCrudEntity;
import org.github.davidcana.jcrud.core.commands.UpdateZCrudCommand;
import org.github.davidcana.jcrud.core.commands.ZCrudCommand;
import org.github.davidcana.jcrud.storages.Storage;

public class UpdateZCrudRequest<T extends ZCrudEntity, K, F extends ZCrudEntity> extends AbstractZCrudRequest<T, K, F> implements IZCrudRecords<T> {
	
	private String url;
	private List<T> newRecords;
	private List<String> recordsToRemove;
	private Map<String, T> existingRecords;
	private F filter;
	
	public UpdateZCrudRequest(){}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

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

	public F getFilter() {
		return filter;
	}

	public void setFilter(F filter) {
		this.filter = filter;
	}

	@Override
	public ZCrudCommand buildCommand(Storage<T, K, F> storage) {
		return new UpdateZCrudCommand<T, K, F>(this, storage);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((existingRecords == null) ? 0 : existingRecords.hashCode());
		result = prime * result + ((filter == null) ? 0 : filter.hashCode());
		result = prime * result + ((newRecords == null) ? 0 : newRecords.hashCode());
		result = prime * result + ((recordsToRemove == null) ? 0 : recordsToRemove.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		@SuppressWarnings("unchecked")
		UpdateZCrudRequest<T, K, F> other = (UpdateZCrudRequest<T, K, F>) obj;
		if (existingRecords == null) {
			if (other.existingRecords != null)
				return false;
		} else if (!existingRecords.equals(other.existingRecords))
			return false;
		if (filter == null) {
			if (other.filter != null)
				return false;
		} else if (!filter.equals(other.filter))
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
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UpdateZCrudRequest [url=" + url + ", newRecords=" + newRecords + ", recordsToRemove=" + recordsToRemove
				+ ", existingRecords=" + existingRecords + ", filter=" + filter + "]";
	}
	
}
