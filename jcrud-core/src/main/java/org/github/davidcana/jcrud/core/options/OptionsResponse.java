package org.github.davidcana.jcrud.core.options;

import java.util.List;

public class OptionsResponse<K> {
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
}
