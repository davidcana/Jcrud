package org.github.davidcana.jcrud.core.responses;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import org.github.davidcana.jcrud.core.ClientServerTalking;
import org.github.davidcana.jcrud.core.ClientServerTalkingItem;
import org.github.davidcana.jcrud.core.ObjectMapperProviderForTest;
import org.github.davidcana.jcrud.core.commands.ZCrudCommand;
import org.github.davidcana.jcrud.core.requests.ZCrudRequest;
import org.github.davidcana.jcrud.core.utils.CoreUtils;
import org.github.davidcana.jcrud.storages.Storage;
import org.github.davidcana.jcrud.storages.StorageException;
import org.github.davidcana.jcrud.storages.JDBC.Constants;
import org.github.davidcana.jcrud.storages.JDBC.JDBCUtils;
import org.junit.After;
import org.junit.Before;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

abstract public class AbstractZCrudResponseTest {

	private static final String NEW_FILE_SUFFIX = ".new";
	private static final String EXTENSION = ".json";

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	protected void testTalkingFolder(String folderString, Storage storage) throws JsonParseException, JsonMappingException, IOException, InterruptedException, StorageException {
		
		File folder = new File(
				getClass().getResource(
						buildResourceFolderString(folderString)).getFile()
		);
		
		this.testTalkingFolder(
				folder.getName(), 
				folder, 
				storage);
	}
	
	private void testTalkingFolder(String path, File folder, Storage storage) throws JsonParseException, JsonMappingException, IOException, InterruptedException, StorageException {

		int c = 0;
		
	    File[] files = folder.listFiles();
	    Arrays.sort(files);
		for (File file : files) {
            String newPath = path + File.separator + file.getName();
            
	        if (file.isDirectory()) {
				this.testTalkingFolder(newPath,	file, storage);
				
	        } else if (newPath.endsWith(EXTENSION)){
	        	this.testTalking(newPath, storage);
	        	++c;
	        }
	    }
	    
	    System.err.println("Folder: " + path + " -> " + c + " files tested.");
	}
	
	protected void testTalking(String test, Storage storage) throws JsonParseException, JsonMappingException, IOException, InterruptedException, StorageException {
		
        long start = System.currentTimeMillis();
        
        //this.build();
        this.resetData();
        
        String json = CoreUtils.getInstance().getStringFromReader(
        		this.getResourceReader(test));
        ClientServerTalking expectedTalking = ObjectMapperProviderForTest.getInstance().getClientServerTalking(json, storage);
        ClientServerTalking talking = this.buildTalking(storage, expectedTalking);
	
		if (! expectedTalking.equals(talking)){
		    fail( "unexpected results: see " + this.saveNew(test, talking) );
		}
		
        long elapsed = System.currentTimeMillis() - start;
        System.err.println(test + ": tested in " + elapsed + " ms");
	}
	/*
	private String build() throws JsonProcessingException{
		
		ClientServerTalking<Simple> talking = new ClientServerTalking<>();
		ClientServerTalkingItem<Simple> item = new ClientServerTalkingItem<>();
		
		GetZCrudRequest request = new GetZCrudRequest();
		request.setKey("1");
		request.setCommand("getRecord");
		item.setGetRequest(request);
		
		GetZCrudResponse<Simple> response = new GetZCrudResponse<>();
		response.setResult("OK");
		Simple record = new Simple();
		record.setId(1);
		record.setName("Igor Cooley");
		record.setDescription("Lorem ipsum dolor");
		response.setRecord(record);
		item.setGetResponse(response);
		
		List<ClientServerTalkingItem<Simple>> items = new ArrayList<>();
		items.add(item);
		talking.setItems(items);
		
		String json = ObjectMapperProviderForTest.getInstance().get().writeValueAsString(talking);
		
		return json;
	}*/
	
	@SuppressWarnings("rawtypes")
	private ClientServerTalking buildTalking(Storage storage, ClientServerTalking expectedTalking) throws JsonParseException, JsonMappingException, IOException{
		
		ClientServerTalking talking = new ClientServerTalking();
		
        List<ClientServerTalkingItem> expectedItems = expectedTalking.getItems();
		for (ClientServerTalkingItem expectedItem : expectedItems){
			ClientServerTalkingItem item = this.buildItem(storage, expectedItem);
			talking.add(item);
        }
		
		return talking;
	}
	
	private ClientServerTalkingItem buildItem(Storage storage, ClientServerTalkingItem expectedItem)
			throws JsonParseException, JsonMappingException, IOException {
		
		// Build zcrudRequest
		ZCrudRequest zcrudRequest = expectedItem.getRequest();
		
		// Build zcrudCommand
		ZCrudCommand zcrudCommand = zcrudRequest.buildCommand(storage);
		
		// Build zcrudResponse
		ZCrudResponse zcrudResponse = zcrudCommand.buildResponse();
		
		// Build expected ClientServerTalkingItem
		ClientServerTalkingItem item = (ClientServerTalkingItem) expectedItem.clone();
		item.replaceResponse(zcrudResponse);;
		
		return item;
	}

	protected String saveNew(String test, ClientServerTalking talking) throws IOException{
		
		StringBuilder buffer = this.buildBuffer(talking);
		
		URL resource = getClass().getResource(
				this.buildResourceString(test)
		);
    	File parent = (new File( resource.getFile() ) ).getParentFile().getParentFile();
    	String newFileName = parent.getPath() + this.buildNewFileName(test);
		File newFile = new File(newFileName);
    	newFile.createNewFile();
    	 
        this.writeBufferToFile(buffer, newFile);
        
        return newFileName;
	}

	private void writeBufferToFile(StringBuilder buffer, File file) throws IOException {
		
		Writer fileWriter = new FileWriter(file);
        fileWriter.write(buffer.toString());
        fileWriter.close();
	}
	
	private StringBuilder buildBuffer(ClientServerTalking talking) throws IOException {
		
		String json = ObjectMapperProviderForTest.getInstance().get().writerWithDefaultPrettyPrinter().writeValueAsString(talking);
		
		StringReader reader = new StringReader(json);
		
		int intValueOfChar;
	    StringBuilder buffer = new StringBuilder();
	    while ((intValueOfChar = reader.read()) != -1) {
	        buffer.append((char) intValueOfChar);
	    }
	    reader.close();
	    
		return buffer;
	}

	private Reader getResourceReader(String test) {
		
        return new BufferedReader(
        		new InputStreamReader(
        				this.getClass().getResourceAsStream( 
        						this.buildResourceString(test) 
        				)
        		)
        );
	}

	private String buildNewFileName(String test) {
		return this.buildResourceString(test) + NEW_FILE_SUFFIX;
	}
	
	private String buildResourceString(String test) {
		return "/" + test;
		//return "/" + test + ".json";
	}
	
	private String buildResourceFolderString(String folder) {
		return "/" + folder;
	}

	private void resetData() throws IOException, InterruptedException, StorageException {

		// Build path of scripts
		String projectFullPath = CoreUtils.getInstance().getProjectFullPath();
		String deleteScriptFullPath = projectFullPath + Constants.SCRIPTS_PATH + Constants.SCRIPTS_DELETE_DATA_FILE;
		String inserScriptFullPath = projectFullPath + Constants.SCRIPTS_PATH + Constants.SCRIPTS_INSERT_DATA_FILE;

		// Run scripts
		JDBCUtils.getInstance().executeSqlFile(deleteScriptFullPath);
		JDBCUtils.getInstance().executeSqlFile(inserScriptFullPath);
	}

}
