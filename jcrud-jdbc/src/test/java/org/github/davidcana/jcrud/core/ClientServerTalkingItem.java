package org.github.davidcana.jcrud.core;

import org.github.davidcana.jcrud.core.requests.GetZCrudRequest;
import org.github.davidcana.jcrud.core.requests.ListZCrudRequest;
import org.github.davidcana.jcrud.core.requests.UpdateZCrudRequest;
import org.github.davidcana.jcrud.core.requests.ZCrudRequest;
import org.github.davidcana.jcrud.core.responses.GetZCrudResponse;
import org.github.davidcana.jcrud.core.responses.ListZCrudResponse;
import org.github.davidcana.jcrud.core.responses.UpdateZCrudResponse;
import org.github.davidcana.jcrud.core.responses.ZCrudResponse;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class ClientServerTalkingItem<T extends ZCrudEntity> {

	@JsonInclude(Include.NON_NULL)
	private GetZCrudRequest getRequest;
	
	@JsonInclude(Include.NON_NULL)
	private ListZCrudRequest<T> listRequest;
	
	@JsonInclude(Include.NON_NULL)
	private UpdateZCrudRequest<T> updateRequest;
	
	@JsonInclude(Include.NON_NULL)
	private GetZCrudResponse<T> getResponse;
	
	@JsonInclude(Include.NON_NULL)
	private ListZCrudResponse<T> listResponse;
	
	@JsonInclude(Include.NON_NULL)
	private UpdateZCrudResponse<T> updateResponse;
	
	public ClientServerTalkingItem(){}

	public GetZCrudRequest getGetRequest() {
		return getRequest;
	}

	public void setGetRequest(GetZCrudRequest getRequest) {
		this.getRequest = getRequest;
	}

	public ListZCrudRequest<T> getListRequest() {
		return listRequest;
	}

	public void setListRequest(ListZCrudRequest<T> listRequest) {
		this.listRequest = listRequest;
	}

	public UpdateZCrudRequest<T> getUpdateRequest() {
		return updateRequest;
	}

	public void setUpdateRequest(UpdateZCrudRequest<T> updateRequest) {
		this.updateRequest = updateRequest;
	}

	public GetZCrudResponse<T> getGetResponse() {
		return getResponse;
	}

	public void setGetResponse(GetZCrudResponse<T> getResponse) {
		this.getResponse = getResponse;
	}

	public ListZCrudResponse<T> getListResponse() {
		return listResponse;
	}

	public void setListResponse(ListZCrudResponse<T> listResponse) {
		this.listResponse = listResponse;
	}

	public UpdateZCrudResponse<T> getUpdateResponse() {
		return updateResponse;
	}

	public void setUpdateResponse(UpdateZCrudResponse<T> updateResponse) {
		this.updateResponse = updateResponse;
	}

	@JsonIgnore
	public ZCrudRequest getRequest(){
		
		if (getRequest != null){
			return getRequest;
		}
		
		if (listRequest != null){
			return listRequest;
		}
		
		return updateRequest;
	}
	
	@JsonIgnore
	public ZCrudResponse getResponse(){
		
		if (getResponse != null){
			return getResponse;
		}
		
		if (listResponse != null){
			return listResponse;
		}
		
		return updateResponse;
	}
	
	@JsonIgnore
	public void replaceResponse(ZCrudResponse newResponse){
		
		if (getResponse != null){
			getResponse = (GetZCrudResponse<T>) newResponse;
			return;
		}
		
		if (listResponse != null){
			listResponse = (ListZCrudResponse<T>) newResponse;
			return;
		}
		
		updateResponse = (UpdateZCrudResponse<T>) newResponse;
	}
	
	@Override
	public String toString() {
		return "ClientServerTalkingItem [getRequest=" + getRequest + ", listRequest=" + listRequest + ", updateRequest="
				+ updateRequest + ", getResponse=" + getResponse + ", listResponse=" + listResponse
				+ ", updateResponse=" + updateResponse + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getRequest == null) ? 0 : getRequest.hashCode());
		result = prime * result + ((getResponse == null) ? 0 : getResponse.hashCode());
		result = prime * result + ((listRequest == null) ? 0 : listRequest.hashCode());
		result = prime * result + ((listResponse == null) ? 0 : listResponse.hashCode());
		result = prime * result + ((updateRequest == null) ? 0 : updateRequest.hashCode());
		result = prime * result + ((updateResponse == null) ? 0 : updateResponse.hashCode());
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
		ClientServerTalkingItem other = (ClientServerTalkingItem) obj;
		if (getRequest == null) {
			if (other.getRequest != null)
				return false;
		} else if (!getRequest.equals(other.getRequest))
			return false;
		if (getResponse == null) {
			if (other.getResponse != null)
				return false;
		} else if (!getResponse.equals(other.getResponse))
			return false;
		if (listRequest == null) {
			if (other.listRequest != null)
				return false;
		} else if (!listRequest.equals(other.listRequest))
			return false;
		if (listResponse == null) {
			if (other.listResponse != null)
				return false;
		} else if (!listResponse.equals(other.listResponse))
			return false;
		if (updateRequest == null) {
			if (other.updateRequest != null)
				return false;
		} else if (!updateRequest.equals(other.updateRequest))
			return false;
		if (updateResponse == null) {
			if (other.updateResponse != null)
				return false;
		} else if (!updateResponse.equals(other.updateResponse))
			return false;
		return true;
	}
	
	@Override
	public ClientServerTalkingItem<T> clone() {
		
		ClientServerTalkingItem<T> result = new ClientServerTalkingItem();
		
		result.getRequest = getRequest;
		result.listRequest = listRequest;
		result.updateRequest = updateRequest;
		result.getResponse = getResponse;
		result.listResponse = listResponse;
		result.updateResponse = updateResponse;
		
		return result;
	}
}
