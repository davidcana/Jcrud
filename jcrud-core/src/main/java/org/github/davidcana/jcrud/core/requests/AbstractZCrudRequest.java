package org.github.davidcana.jcrud.core.requests;

abstract public class AbstractZCrudRequest implements ZCrudRequest {

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
		AbstractZCrudRequest other = (AbstractZCrudRequest) obj;
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
