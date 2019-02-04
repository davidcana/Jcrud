package org.github.davidcana.jcrud.core.requests;

import org.github.davidcana.jcrud.core.CRUD.CRUDHelper;
import org.github.davidcana.jcrud.core.commands.GetZCrudCommand;
import org.github.davidcana.jcrud.core.commands.ZCrudCommand;

public class GetZCrudRequest extends AbstractZCrudRequest {
	
	private String key;
	
	public GetZCrudRequest(){}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@Override
	public ZCrudCommand buildCommand(CRUDHelper crudHelper) {
		return new GetZCrudCommand(this, crudHelper);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((key == null) ? 0 : key.hashCode());
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
		GetZCrudRequest other = (GetZCrudRequest) obj;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "GetZCrudRequest [key=" + key + ", getCommand()=" + getCommand() + "]";
	}
	
}
