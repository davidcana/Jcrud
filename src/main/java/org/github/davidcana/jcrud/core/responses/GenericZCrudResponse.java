package org.github.davidcana.jcrud.core.responses;

import org.github.davidcana.jcrud.core.ObjectMapperProvider;

import com.fasterxml.jackson.core.JsonProcessingException;

abstract public class GenericZCrudResponse implements ZCrudResponse {
	
	private static final String TRUE_VALUE = "OK";
	private static final String FALSE_VALUE = "ERROR";
	
	private String result;
	private String message;
	
	public GenericZCrudResponse(){}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
		if (message != null){
			this.isOK(false);
		}
	}

	public void isOK(boolean ok){
		this.result = ok? TRUE_VALUE: FALSE_VALUE;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "GenericCRUDResponse [result=" + result + ", message=" + message + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + ((this.result == null) ? 0 : this.result.hashCode());
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
		GenericZCrudResponse other = (GenericZCrudResponse) obj;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (result == null) {
			if (other.result != null)
				return false;
		} else if (!result.equals(other.result))
			return false;
		return true;
	}
	
	public String buildJSON() throws JsonProcessingException {
		return ObjectMapperProvider.getInstance().get().writeValueAsString( this );
	}
	
}
