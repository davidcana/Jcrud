package org.github.davidcana.jcrud.core.requests;

import java.util.Map;

import org.github.davidcana.jcrud.core.CRUD.CRUDHelper;
import org.github.davidcana.jcrud.core.commands.ListZCrudCommand;
import org.github.davidcana.jcrud.core.commands.ZCrudCommand;

public class ListZCrudRequest extends AbstractZCrudRequest {

	//pageNumber=1&pageSize=15&sortFieldId=name&sortType=asc&command=listRecords
	
	private int pageNumber;
	private int pageSize;
	private String sortFieldId;
	private String sortType;
	private Map<String, Map<String, Object>> filter;
	
	public ListZCrudRequest(){}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getSortFieldId() {
		return sortFieldId;
	}

	public void setSortFieldId(String sortFieldId) {
		this.sortFieldId = sortFieldId;
	}

	public String getSortType() {
		return sortType;
	}

	public void setSortType(String sortType) {
		this.sortType = sortType;
	}

	public Map<String, Map<String, Object>> getFilter() {
		return filter;
	}

	public void setFilter(Map<String, Map<String, Object>> filter) {
		this.filter = filter;
	}

	@Override
	public ZCrudCommand buildCommand(CRUDHelper crudHelper) {
		return new ListZCrudCommand(this, crudHelper);
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
		ListZCrudRequest other = (ListZCrudRequest) obj;
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
