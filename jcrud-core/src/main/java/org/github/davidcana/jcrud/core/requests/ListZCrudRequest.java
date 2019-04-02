package org.github.davidcana.jcrud.core.requests;

import org.github.davidcana.jcrud.core.ZCrudEntity;
import org.github.davidcana.jcrud.core.commands.ListZCrudCommand;
import org.github.davidcana.jcrud.core.commands.ZCrudCommand;
import org.github.davidcana.jcrud.storages.Storage;

public class ListZCrudRequest<T extends ZCrudEntity, K, F extends ZCrudEntity> extends AbstractZCrudRequest<T, K, F>  implements ISearchFieldData<F>  {

	//pageNumber=1&pageSize=15&sortFieldId=name&sortType=asc&command=listRecords
	
	private int pageNumber;
	private int pageSize;
	private String sortFieldId;
	private String sortType;
	private F filter;
	
	public ListZCrudRequest(){}

	@Override
	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}
	
	@Override
	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
	@Override
	public String getSortFieldId() {
		return sortFieldId;
	}

	public void setSortFieldId(String sortFieldId) {
		this.sortFieldId = sortFieldId;
	}
	
	@Override
	public String getSortType() {
		return sortType;
	}

	public void setSortType(String sortType) {
		this.sortType = sortType;
	}
	
	@Override
	public F getFilter() {
		return filter;
	}

	public void setFilter(F filter) {
		this.filter = filter;
	}

	@Override
	public ZCrudCommand buildCommand(Storage<T, K, F> storage) {
		return new ListZCrudCommand<T, K, F>(this, storage);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((filter == null) ? 0 : filter.hashCode());
		result = prime * result + pageNumber;
		result = prime * result + pageSize;
		result = prime * result + ((sortFieldId == null) ? 0 : sortFieldId.hashCode());
		result = prime * result + ((sortType == null) ? 0 : sortType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		@SuppressWarnings("unchecked")
		ListZCrudRequest<T, K, F> other = (ListZCrudRequest<T, K, F>) obj;
		if (filter == null) {
			if (other.filter != null)
				return false;
		} else if (!filter.equals(other.filter))
			return false;
		if (pageNumber != other.pageNumber)
			return false;
		if (pageSize != other.pageSize)
			return false;
		if (sortFieldId == null) {
			if (other.sortFieldId != null)
				return false;
		} else if (!sortFieldId.equals(other.sortFieldId))
			return false;
		if (sortType == null) {
			if (other.sortType != null)
				return false;
		} else if (!sortType.equals(other.sortType))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "ListZCrudRequest [pageNumber=" + pageNumber + ", pageSize=" + pageSize + ", sortFieldId=" + sortFieldId
				+ ", sortType=" + sortType + ", filter=" + filter + ", getCommand()=" + getCommand() + "]";
	}
	
}
