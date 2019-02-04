package org.github.davidcana.jcrud.core.responses;

import java.util.List;

public class ListZCrudResponse extends GenericZCrudResponse {

	private List<?> records;
	private long totalNumberOfRecords;
	
	public ListZCrudResponse(){}
	
	public List<?> getRecords() {
		return records;
	}

	public void setRecords(List<?> records) {
		this.records = records;
	}

	public long getTotalNumberOfRecords() {
		return totalNumberOfRecords;
	}

	public void setTotalNumberOfRecords(long totalNumberOfRecords) {
		this.totalNumberOfRecords = totalNumberOfRecords;
	}

	@Override
	public String toString() {
		return "ListCRUDResponse [records=" + records + ", totalNumberOfRecords=" + totalNumberOfRecords + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((records == null) ? 0 : records.hashCode());
		result = prime * result + (int) (totalNumberOfRecords ^ (totalNumberOfRecords >>> 32));
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
		ListZCrudResponse other = (ListZCrudResponse) obj;
		if (records == null) {
			if (other.records != null)
				return false;
		} else if (!records.equals(other.records))
			return false;
		if (totalNumberOfRecords != other.totalNumberOfRecords)
			return false;
		return true;
	}
	
}
