package org.github.davidcana.jcrud.core.model;

import java.util.ArrayList;
import java.util.List;

import org.github.davidcana.jcrud.core.ZCrudEntity;
import org.github.davidcana.jcrud.core.annotations.JCRUDEntity;
import org.github.davidcana.jcrud.core.requests.ZCrudRecords;
import org.github.davidcana.jcrud.storages.JDBC.Simple2Detail2JDBCStorage;
import org.github.davidcana.jcrud.storages.JDBC.Simple2DetailJDBCStorage;
import org.github.davidcana.jcrud.storages.JDBC.Simple2JDBCStorage;
import org.github.davidcana.jcrud.storages.JDBC.annotations.JDBCEntity;
import org.github.davidcana.jcrud.storages.JDBC.annotations.JDBCId;
import org.github.davidcana.jcrud.storages.JDBC.annotations.JDBCOneToMany;
import org.github.davidcana.jcrud.storages.JDBC.annotations.JDBCOrderedByDefault;

@JCRUDEntity(storage = Simple2JDBCStorage.class)
@JDBCEntity(table = "simple2")
public class Simple2 implements ZCrudEntity {
	
	@JDBCId
	@JDBCOrderedByDefault(type = "ASC")
	private Integer id;
	
	private String name;
	private String description;
	private List<Simple2Detail> details = new ArrayList<>();
	private List<Simple2Detail2> details2 = new ArrayList<>();
	
	@JDBCOneToMany(storage = Simple2DetailJDBCStorage.class)
	private transient ZCrudRecords<Simple2Detail> detailsZCrudRecords;
	
	@JDBCOneToMany(storage = Simple2Detail2JDBCStorage.class)
	private transient ZCrudRecords<Simple2Detail2> details2ZCrudRecords;
	
	public Simple2(){}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Simple2Detail> getDetails() {
		return details;
	}

	public void setDetails(List<Simple2Detail> details) {
		this.details = details;
	}

	public List<Simple2Detail2> getDetails2() {
		return details2;
	}

	public void setDetails2(List<Simple2Detail2> details2) {
		this.details2 = details2;
	}

	public ZCrudRecords<Simple2Detail> getDetailsZCrudRecords() {
		return detailsZCrudRecords;
	}

	public void setDetailsZCrudRecords(ZCrudRecords<Simple2Detail> detailsZCrudRecords) {
		this.detailsZCrudRecords = detailsZCrudRecords;
	}

	public ZCrudRecords<Simple2Detail2> getDetails2ZCrudRecords() {
		return details2ZCrudRecords;
	}

	public void setDetails2ZCrudRecords(ZCrudRecords<Simple2Detail2> details2zCrudRecords) {
		details2ZCrudRecords = details2zCrudRecords;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((details == null) ? 0 : details.hashCode());
		result = prime * result + ((details2 == null) ? 0 : details2.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Simple2 other = (Simple2) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (details == null) {
			if (other.details != null)
				return false;
		} else if (!details.equals(other.details))
			return false;
		if (details2 == null) {
			if (other.details2 != null)
				return false;
		} else if (!details2.equals(other.details2))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Simple2 [id=" + id + ", name=" + name + ", description=" + description + ", details=" + details
				+ ", details2=" + details2 + "]";
	}

}
