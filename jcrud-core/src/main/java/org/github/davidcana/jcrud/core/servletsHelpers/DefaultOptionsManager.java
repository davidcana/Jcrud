package org.github.davidcana.jcrud.core.servletsHelpers;

import javax.servlet.http.HttpServletRequest;

import org.github.davidcana.jcrud.core.Constants;
import org.github.davidcana.jcrud.core.options.OptionsResponse;
import org.github.davidcana.jcrud.core.responses.SimpleZCrudResponse;
import org.github.davidcana.jcrud.core.responses.ZCrudResponse;
import org.github.davidcana.jcrud.storages.Storage;
import org.github.davidcana.jcrud.storages.StorageResolver;

public class DefaultOptionsManager implements Manager{
	
	static private DefaultOptionsManager instance;
	
	private DefaultOptionsManager(){}
	
	@Override
	public ZCrudResponse buildZCrudResponse(HttpServletRequest request){
		
		ZCrudResponse zcrudResponse = null;
		
		try {
			// Build zcrudRequest
			@SuppressWarnings("rawtypes")
			Storage storage = StorageResolver.getInstance().get(
					request.getParameter(Constants.TABLE_URL_PARAMETER)
			);
			
			// Build zcrudResponse
			zcrudResponse = this.buildResponse(storage);
			
		} catch (Exception e) {
			zcrudResponse = new SimpleZCrudResponse(e.getMessage());
		}
		
		return zcrudResponse;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private ZCrudResponse buildResponse(Storage storage) {
		
		OptionsResponse optionsResponse = new OptionsResponse<>();
		try {
			optionsResponse.setOptions(
					storage.getAllAsOptions()
			);
			optionsResponse.done(true);
			
		} catch (Exception e) {
			optionsResponse.setMessage(
					e.getMessage()
			);
			optionsResponse.done(false);
		}
		
		return optionsResponse;
	}
	
	static public DefaultOptionsManager getInstance(){
		
		if ( instance == null ){
			instance = new DefaultOptionsManager();
		}
		
		return instance;
	}

}
