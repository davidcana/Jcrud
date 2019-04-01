package org.github.davidcana.jcrud.core.requests;

import java.util.Map;

import org.github.davidcana.jcrud.core.ZCrudEntity;
import org.github.davidcana.jcrud.core.commands.GetZCrudCommand;
import org.github.davidcana.jcrud.core.commands.ZCrudCommand;
import org.github.davidcana.jcrud.storages.Storage;

public class GetZCrudRequest<F extends ZCrudEntity> extends AbstractZCrudRequest<F> {
	
	private String key;
	private F filter;
	private Map<String,SearchFieldData<F>> searchFieldsData;
	
	public GetZCrudRequest(){}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public F getFilter() {
		return filter;
	}

	public void setFilter(F filter) {
		this.filter = filter;
	}

	public Map<String, SearchFieldData<F>> getSearchFieldsData() {
		return searchFieldsData;
	}

	public void setSearchFieldsData(Map<String, SearchFieldData<F>> searchFieldsData) {
		this.searchFieldsData = searchFieldsData;
	}

	@Override
	public ZCrudCommand buildCommand(Storage storage) {
		return new GetZCrudCommand(this, storage);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((filter == null) ? 0 : filter.hashCode());
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		result = prime * result + ((searchFieldsData == null) ? 0 : searchFieldsData.hashCode());
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
		GetZCrudRequest<F> other = (GetZCrudRequest<F>) obj;
		if (filter == null) {
			if (other.filter != null)
				return false;
		} else if (!filter.equals(other.filter))
			return false;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		if (searchFieldsData == null) {
			if (other.searchFieldsData != null)
				return false;
		} else if (!searchFieldsData.equals(other.searchFieldsData))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "GetZCrudRequest [key=" + key + ", filter=" + filter + ", searchFieldsData=" + searchFieldsData + "]";
	}
	
}
