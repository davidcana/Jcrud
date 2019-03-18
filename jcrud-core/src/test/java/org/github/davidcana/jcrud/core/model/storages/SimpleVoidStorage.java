package org.github.davidcana.jcrud.core.model.storages;

import org.github.davidcana.jcrud.core.model.Simple;

public class SimpleVoidStorage extends VoidStorage<Simple, Integer, Simple> {
	
	static private SimpleVoidStorage instance;
	
	static public SimpleVoidStorage getInstance(){
		
		if (instance == null){
			instance = new SimpleVoidStorage();
		}
		
		return instance;
	}

}
