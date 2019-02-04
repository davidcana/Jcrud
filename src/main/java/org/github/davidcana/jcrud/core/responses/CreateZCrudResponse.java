package org.github.davidcana.jcrud.core.responses;

public class CreateZCrudResponse extends GenericZCrudResponse {

	private Object record;
	
	public CreateZCrudResponse(){}

	public Object getRecord() {
		return record;
	}

	public void setRecord(Object record) {
		this.record = record;
	}

	@Override
	public String toString() {
		return "CreateCRUDResponse [record=" + record + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((record == null) ? 0 : record.hashCode());
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
		CreateZCrudResponse other = (CreateZCrudResponse) obj;
		if (record == null) {
			if (other.record != null)
				return false;
		} else if (!record.equals(other.record))
			return false;
		return true;
	}
	
}
