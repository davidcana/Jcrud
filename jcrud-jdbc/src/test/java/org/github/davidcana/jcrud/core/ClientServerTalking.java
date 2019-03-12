package org.github.davidcana.jcrud.core;

import java.util.ArrayList;
import java.util.List;

public class ClientServerTalking<T extends ZCrudEntity> {

	private List<ClientServerTalkingItem<T>> items = new ArrayList<>();
	
	public ClientServerTalking(){}

	public List<ClientServerTalkingItem<T>> getItems() {
		return items;
	}

	public void setItems(List<ClientServerTalkingItem<T>> items) {
		this.items = items;
	}

	public void add(ClientServerTalkingItem<T> item){
		this.items.add(item);
	}
	
	@Override
	public String toString() {
		return "ClientServerTalking [items=" + items + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((items == null) ? 0 : items.hashCode());
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
		ClientServerTalking other = (ClientServerTalking) obj;
		if (items == null) {
			if (other.items != null)
				return false;
		} else if (!items.equals(other.items))
			return false;
		return true;
	}
	
}
