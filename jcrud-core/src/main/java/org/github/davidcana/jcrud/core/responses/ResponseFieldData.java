package org.github.davidcana.jcrud.core.responses;

public class ResponseFieldData {

	private long totalNumberOfRecords;
	
	public ResponseFieldData(){}

	public ResponseFieldData(long totalNumberOfRecords){
		this.totalNumberOfRecords = totalNumberOfRecords;
	}

	public long getTotalNumberOfRecords() {
		return totalNumberOfRecords;
	}

	public void setTotalNumberOfRecords(long totalNumberOfRecords) {
		this.totalNumberOfRecords = totalNumberOfRecords;
	}

	@Override
	public String toString() {
		return "ResponseFieldData [totalNumberOfRecords=" + totalNumberOfRecords + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (totalNumberOfRecords ^ (totalNumberOfRecords >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ResponseFieldData other = (ResponseFieldData) obj;
		if (totalNumberOfRecords != other.totalNumberOfRecords)
			return false;
		return true;
	}
	
}
