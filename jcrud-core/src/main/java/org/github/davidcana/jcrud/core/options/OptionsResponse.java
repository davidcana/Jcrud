package org.github.davidcana.jcrud.core.options;

import java.util.List;

import org.github.davidcana.jcrud.core.ObjectMapperProvider;
import org.github.davidcana.jcrud.core.responses.ZCrudResponse;

import com.fasterxml.jackson.core.JsonProcessingException;

public class OptionsResponse<K> implements ZCrudResponse {
	
	private List<Option<K>> options;
	private String result;
	private String message;
	
	public OptionsResponse(){}

	public List<Option<K>> getOptions() {
		return options;
	}

	public void setOptions(List<Option<K>> list) {
		this.options = list;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public void done(boolean isOK){
		this.result = isOK? "OK": "Error";
	}

	@Override
	public String buildJSON() throws JsonProcessingException {
		return ObjectMapperProvider.getInstance().get().writeValueAsString(this);
	}
}
