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

public class ClientServerTalkingItem<T extends ZCrudEntity, F extends ZCrudEntity, U extends ZCrudEntity> {

	@JsonInclude(Include.NON_NULL)
	private GetZCrudRequest<F> getRequest;
	
	@JsonInclude(Include.NON_NULL)
	private ListZCrudRequest<F> listRequest;
	
	@JsonInclude(Include.NON_NULL)
	private UpdateZCrudRequest<T, F> updateRequest;
	
	@JsonInclude(Include.NON_NULL)
	private GetZCrudResponse<T> getResponse;
	
	@JsonInclude(Include.NON_NULL)
	private ListZCrudResponse<U> listResponse;
	
	@JsonInclude(Include.NON_NULL)
	private UpdateZCrudResponse<T> updateResponse;
	
	public ClientServerTalkingItem(){}

	public GetZCrudRequest<F> getGetRequest() {
		return getRequest;
	}

	public void setGetRequest(GetZCrudRequest<F> getRequest) {
		this.getRequest = getRequest;
	}

	public ListZCrudRequest<F> getListRequest() {
		return listRequest;
	}

	public void setListRequest(ListZCrudRequest<F> listRequest) {
		this.listRequest = listRequest;
	}

	public UpdateZCrudRequest<T, F> getUpdateRequest() {
		return updateRequest;
	}

	public void setUpdateRequest(UpdateZCrudRequest<T, F> updateRequest) {
		this.updateRequest = updateRequest;
	}

	public GetZCrudResponse<T> getGetResponse() {
		return getResponse;
	}

	public void setGetResponse(GetZCrudResponse<T> getResponse) {
		this.getResponse = getResponse;
	}

	public ListZCrudResponse<U> getListResponse() {
		return listResponse;
	}

	public void setListResponse(ListZCrudResponse<U> listResponse) {
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
			listResponse = (ListZCrudResponse<U>) newResponse;
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
		ClientServerTalkingItem<T, F, U> other = (ClientServerTalkingItem<T, F, U>) obj;
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
	public ClientServerTalkingItem<T, F, U> clone() {
		
		ClientServerTalkingItem<T, F, U> result = new ClientServerTalkingItem<>();
		
		result.getRequest = getRequest;
		result.listRequest = listRequest;
		result.updateRequest = updateRequest;
		result.getResponse = getResponse;
		result.listResponse = listResponse;
		result.updateResponse = updateResponse;
		
		return result;
	}
}
