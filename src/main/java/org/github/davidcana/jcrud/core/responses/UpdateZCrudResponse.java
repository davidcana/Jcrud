package org.github.davidcana.jcrud.core.responses;

import java.util.List;

import org.github.davidcana.jcrud.core.ZCrudEntity;

public class UpdateZCrudResponse<T extends ZCrudEntity> extends GenericZCrudResponse {

	private List<T> newRecords;
	
	public UpdateZCrudResponse(){}
	
	public List<T> getNewRecords() {
		return newRecords;
	}

	public void setNewRecords(List<T> records) {
		this.newRecords = records;
	}

	@Override
	public String toString() {
		return "UpdateZCrudResponse [newRecords=" + newRecords + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((newRecords == null) ? 0 : newRecords.hashCode());
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
		UpdateZCrudResponse other = (UpdateZCrudResponse) obj;
		if (newRecords == null) {
			if (other.newRecords != null)
				return false;
		} else if (!newRecords.equals(other.newRecords))
			return false;
		return true;
	}
	
}
