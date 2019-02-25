package org.github.davidcana.jcrud.core.utils;

import java.io.IOException;
import java.io.Reader;

import org.github.davidcana.jcrud.core.Constants;

public class CoreUtils {
	
	static private CoreUtils instance;
	
	public CoreUtils(){}
	
	public String getStringFromReader(Reader reader) throws IOException {
		
		StringBuilder sb = new StringBuilder();
	    int intValueOfChar;
	    while ((intValueOfChar = reader.read()) != -1) {
	        sb.append( (char) intValueOfChar);
	    }
	    reader.close();
	    
	    return sb.toString();
	}
	
	public String getPropertyIdFromZCrudRecordsFieldName(String zcrudRecordsFieldName){
		return zcrudRecordsFieldName.substring(0, zcrudRecordsFieldName.length() - Constants.ZCRUD_RECORDS_SUFFIX.length());
	}
	
	static public CoreUtils getInstance(){
		
		if ( instance == null ){
			instance = new CoreUtils();
		}
		
		return instance;
	}

}
