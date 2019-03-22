package org.github.davidcana.jcrud.core.requests;

import org.github.davidcana.jcrud.core.ZCrudEntity;

public interface ISearchFieldData<F extends ZCrudEntity> {

	public int getPageNumber();

	public int getPageSize();

	public String getSortFieldId();

	public String getSortType();
	
	public F getFilter();
	
}
