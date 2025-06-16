package org.github.davidcana.jcrud.core.model;

import java.util.Objects;

import org.github.davidcana.jcrud.core.File;
import org.github.davidcana.jcrud.core.ZCrudEntity;
import org.github.davidcana.jcrud.core.annotations.JCRUDEntity;
import org.github.davidcana.jcrud.core.model.storages.SimpleWithFileJDBCStorage;
import org.github.davidcana.jcrud.storages.JDBC.annotations.JDBCEntity;
import org.github.davidcana.jcrud.storages.JDBC.annotations.JDBCId;
import org.github.davidcana.jcrud.storages.JDBC.annotations.JDBCOrderedByDefault;

@JCRUDEntity(storage = SimpleWithFileJDBCStorage.class)
@JDBCEntity(table = "simple_with_file")
public class SimpleWithFile implements ZCrudEntity {
	
	@JDBCId
	@JDBCOrderedByDefault(type = "ASC")
	private Integer id;
	private String name;
	private String description;
	private File file;
	
	public SimpleWithFile(){}
	
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

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	@Override
	public int hashCode() {
		return Objects.hash(description, file, id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SimpleWithFile other = (SimpleWithFile) obj;
		return Objects.equals(description, other.description) && Objects.equals(file, other.file)
				&& Objects.equals(id, other.id) && Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "SimpleWithFile [id=" + id + ", name=" + name + ", description=" + description + ", file=" + file + "]";
	}

}
