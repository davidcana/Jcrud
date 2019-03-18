package org.github.davidcana.jcrud.core.model.storages;

import org.github.davidcana.jcrud.core.model.Simple2;

public class Simple2VoidStorage extends VoidStorage<Simple2, Integer, Simple2> {
	
	static private Simple2VoidStorage instance;
	
	static public Simple2VoidStorage getInstance(){
		
		if (instance == null){
			instance = new Simple2VoidStorage();
		}
		
		return instance;
	}

}
