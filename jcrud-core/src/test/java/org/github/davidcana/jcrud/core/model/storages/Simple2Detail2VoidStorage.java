package org.github.davidcana.jcrud.core.model.storages;

import org.github.davidcana.jcrud.core.model.Simple2Detail2;

public class Simple2Detail2VoidStorage extends VoidStorage<Simple2Detail2, Integer, Simple2Detail2> {
	
	static private Simple2Detail2VoidStorage instance;
	
	static public Simple2Detail2VoidStorage getInstance(){
		
		if (instance == null){
			instance = new Simple2Detail2VoidStorage();
		}
		
		return instance;
	}

}
