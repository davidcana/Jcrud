package org.github.davidcana.jcrud.core.responses;

public class SimpleZCrudResponse extends AbstractZCrudResponse {
	
	public SimpleZCrudResponse(){
		super();
	}
	
	public SimpleZCrudResponse(String message){
		this();
		this.setMessage(message);
	}
	
	@Override
	public String toString() {
		return "SimpleCRUDResponse []";
	}
	
}
