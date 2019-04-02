package org.github.davidcana.jcrud.core.responses;

import java.util.HashMap;
import java.util.Map;

import org.github.davidcana.jcrud.core.ZCrudEntity;

public class GetZCrudResponse<T extends ZCrudEntity> extends GenericZCrudResponse<T> {

	private T record;
	private Map<String, ResponseFieldData> fieldsData;
	
	public GetZCrudResponse(){}

	public T getRecord() {
		return record;
	}

	public void setRecord(T record) {
		this.record = record;
	}


	public Map<String, ResponseFieldData> getFieldsData() {
		return fieldsData;
	}

	public void setFieldsData(Map<String, ResponseFieldData> fieldsData) {
		this.fieldsData = fieldsData;
	}

	public void putResponseFieldData(String key, ResponseFieldData responseFieldData){
		
		if (this.fieldsData == null){
			this.fieldsData = new HashMap<>();
		}
		
		this.fieldsData.put(key, responseFieldData);
	}
	
	@Override
	public String toString() {
		return "GetZCrudResponse [record=" + record + ", fieldsData=" + fieldsData + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((fieldsData == null) ? 0 : fieldsData.hashCode());
		result = prime * result + ((record == null) ? 0 : record.hashCode());
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
		@SuppressWarnings("rawtypes")
		GetZCrudResponse other = (GetZCrudResponse) obj;
		if (fieldsData == null) {
			if (other.fieldsData != null)
				return false;
		} else if (!fieldsData.equals(other.fieldsData))
			return false;
		if (record == null) {
			if (other.record != null)
				return false;
		} else if (!record.equals(other.record))
			return false;
		return true;
	}
	
}
