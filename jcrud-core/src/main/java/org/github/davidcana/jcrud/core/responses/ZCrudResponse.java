package org.github.davidcana.jcrud.core.responses;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface ZCrudResponse {

	public String buildJSON() throws JsonProcessingException;
}
