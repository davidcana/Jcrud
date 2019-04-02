package org.github.davidcana.jcrud.core.requests;

import org.github.davidcana.jcrud.core.ZCrudEntity;

abstract public class AbstractZCrudRequest<T extends ZCrudEntity, K, F extends ZCrudEntity> implements ZCrudRequest<T, K, F> {

	private String command;
	
	public AbstractZCrudRequest() {
		super();
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((command == null) ? 0 : command.hashCode());
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
		@SuppressWarnings("unchecked")
		AbstractZCrudRequest<T, K, F> other = (AbstractZCrudRequest<T, K, F>) obj;
		if (command == null) {
			if (other.command != null)
				return false;
		} else if (!command.equals(other.command))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AbstractZCrudRequest [command=" + command + "]";
	}

}
