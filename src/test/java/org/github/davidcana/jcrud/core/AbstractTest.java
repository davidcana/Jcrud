package org.github.davidcana.jcrud.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;
import java.net.URL;

import org.github.davidcana.jcrud.core.utils.CoreUtils;

abstract public class AbstractTest {
	
	private static final String NEW_FILE_SUFFIX = ".new";
	
	/*
	protected String getStringFromPath(String testsPath) throws IOException {
		
		return CoreUtils.getInstance().getStringFromReader(
				this.getResourceReader(testsPath)
		);
	}
	*/
	protected String getStringFromFile(String testsPath, String test, String extension) throws IOException {
		
		return CoreUtils.getInstance().getStringFromReader(
				this.getResourceReader(testsPath, test, extension)
		);
	}
	
	protected Reader getResourceReader(String jsonPath) {
		
        return new BufferedReader(
        		new InputStreamReader(
        				this.getClass().getResourceAsStream(jsonPath)
        		)
        );
	}
	
	protected Reader getResourceReader(String testsPath, String test, String extension) {
		return this.getResourceReader(testsPath + test + extension);
	}
	
	protected String saveNew(String test, String buffer, String extension) throws IOException {
		
		URL resource = getClass().getResource(
				this.buildResourceString(test, extension)
		);
    	File parent = (new File( resource.getFile() ) ).getParentFile().getParentFile();
    	String newFileName = parent.getPath() + this.buildNewFileName(test, extension);
		File newFile = new File(newFileName);
    	newFile.createNewFile();
    	 
        this.writeBufferToFile(buffer, newFile);
        
        return newFileName;
	}
	
	private void writeBufferToFile(String buffer, File file) throws IOException {
		
		Writer fileWriter = new FileWriter(file);
        fileWriter.write(buffer.toString());
        fileWriter.close();
	}
	
	private String buildNewFileName(String test, String extension) {
		return this.buildResourceString(test, extension) + NEW_FILE_SUFFIX;
	}
	
	private String buildResourceString(String test, String extension) {
		//return "/" + test;
		return "/requests/" + test + extension;
	}
	
	protected String buildBuffer(String text) throws IOException {
		
		StringReader reader = new StringReader(text);
		
		int intValueOfChar;
	    StringBuilder buffer = new StringBuilder();
	    while ((intValueOfChar = reader.read()) != -1) {
	        buffer.append((char) intValueOfChar);
	    }
	    reader.close();
	    
		return buffer.toString();
	}
}