package org.github.davidcana.jcrud.core.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
	
	public String getStringFromFullPath(String filePath) throws IOException {
		
		StringBuilder fileData = new StringBuilder(1000);
		BufferedReader reader = new BufferedReader(
				new FileReader(filePath)
		);
 
		char[] buf = new char[10];
		int numRead = 0;
		while ((numRead = reader.read(buf)) != -1) {
			String readData = String.valueOf(buf, 0, numRead);
			fileData.append(readData);
			buf = new char[1024];
		}
 
		reader.close();
		return fileData.toString();
	}
	
	public String getStringFromProjectPath(String filePath) throws IOException {
		
		String fullPath = this.getProjectFullPath() 
				+ File.separator
				+ filePath;
		
		return this.getStringFromFullPath(fullPath);
	}
	
	public String getPropertyIdFromZCrudRecordsFieldName(String zcrudRecordsFieldName){
		
		return zcrudRecordsFieldName.substring(
				0, 
				zcrudRecordsFieldName.length() - Constants.ZCRUD_RECORDS_SUFFIX.length()
		);
	}
	
	public String getProjectFullPath() throws IOException {

		URL resource = getClass().getResource("/");
    	File resourceFile = new File(resource.getFile());
		File projectFile = resourceFile.getParentFile().getParentFile();
        
        return projectFile.getAbsolutePath();
	}
	
	public String getJavaFileFullPath(Class<?> clazz, String rootFolder) throws IOException {
		
		return this.getProjectFullPath() 
				+ File.separatorChar
				+ rootFolder 
				+ clazz.getCanonicalName().replace('.', File.separatorChar)
				+ ".java";
	}

	public boolean isNewerThan(String fullPath1, String fullPath2) {
		
		File file1 = new File(fullPath1);
		File file2 = new File(fullPath2);
		
		if (!file1.exists()){
			throw new IllegalArgumentException("File " + fullPath1 + " does not exist!");
		}
		
		if (!file2.exists()){
			return true;
		}
		
		return file1.lastModified() > file2.lastModified();
	}
	
	static public CoreUtils getInstance(){
		
		if ( instance == null ){
			instance = new CoreUtils();
		}
		
		return instance;
	}

}
