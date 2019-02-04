package org.github.davidcana.jcrud.storages;

public class StorageException extends Exception {
	
	private static final long serialVersionUID = 3309851594873905811L;
	
	public StorageException(Exception e){
		super(e);
	}
	
	public StorageException(String message){
		super(message);
	}
}
