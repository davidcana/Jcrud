package org.github.davidcana.jcrud.core.model;

import org.github.davidcana.jcrud.core.ZCrudEntity;
import org.github.davidcana.jcrud.core.annotations.JCRUDEntity;
import org.github.davidcana.jcrud.core.model.storages.VerifiedMemberJDBCStorage;
import org.github.davidcana.jcrud.storages.JDBC.annotations.JDBCEntity;
import org.github.davidcana.jcrud.storages.JDBC.annotations.JDBCId;
import org.github.davidcana.jcrud.storages.JDBC.annotations.JDBCOrderedByDefault;

@JCRUDEntity(storage = VerifiedMemberJDBCStorage.class)
@JDBCEntity(table = "verified_members")
public class VerifiedMember extends Member implements ZCrudEntity {
	
	@JDBCId
	@JDBCOrderedByDefault(type = "ASC")
	private Integer id;
	
	public VerifiedMember(){}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "VerifiedMember [id=" + id + ", getName()=" + getName() + ", getCountry()=" + getCountry()
				+ ", getRecordDateTime()=" + getRecordDateTime() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		VerifiedMember other = (VerifiedMember) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
