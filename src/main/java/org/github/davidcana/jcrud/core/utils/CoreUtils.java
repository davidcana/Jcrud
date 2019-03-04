package org.github.davidcana.jcrud.core.utils;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.net.URL;

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
	
	public String getProjectFullPath() throws IOException {

		URL resource = getClass().getResource("/");
    	File resourceFile = new File(resource.getFile());
		File projectFile = resourceFile.getParentFile().getParentFile();
        
        return projectFile.getAbsolutePath();
	}
	
	static public CoreUtils getInstance(){
		
		if ( instance == null ){
			instance = new CoreUtils();
		}
		
		return instance;
	}

}
