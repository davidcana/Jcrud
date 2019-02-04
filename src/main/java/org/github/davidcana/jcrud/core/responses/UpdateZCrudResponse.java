package org.github.davidcana.jcrud.core.responses;

import java.util.List;

public class UpdateZCrudResponse extends GenericZCrudResponse {

	private List<?> newRecords;
	
	public UpdateZCrudResponse(){}
	
	public List<?> getNewRecords() {
		return newRecords;
	}

	public void setNewRecords(List<?> records) {
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
