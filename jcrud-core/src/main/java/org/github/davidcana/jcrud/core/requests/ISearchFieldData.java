package org.github.davidcana.jcrud.core.requests;

import org.github.davidcana.jcrud.core.ZCrudEntity;

public interface ISearchFieldData<F extends ZCrudEntity> {

	public Integer getPageNumber();

	public Integer getPageSize();

	public String getSortFieldId();

	public String getSortType();
	
	public F getFilter();
	
}
