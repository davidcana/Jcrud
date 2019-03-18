package org.github.davidcana.jcrud.core.model.storages;

import org.github.davidcana.jcrud.core.model.Simple2Detail;

public class Simple2DetailVoidStorage extends VoidStorage<Simple2Detail, Integer, Simple2Detail> {
	
	static private Simple2DetailVoidStorage instance;
	
	static public Simple2DetailVoidStorage getInstance(){
		
		if (instance == null){
			instance = new Simple2DetailVoidStorage();
		}
		
		return instance;
	}

}
