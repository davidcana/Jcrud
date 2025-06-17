package org.github.davidcana.jcrud.core;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class File implements IncludesNotUpdatableFieldsSet {
	
	public static final String CONTENTS_FIELD = "contents";
	
    private String name;
    private Long lastModified;
    private Long size;
    private String type;
    private String contents;
    private String url;
    
    public File() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getLastModified() {
		return lastModified;
	}

	public void setLastModified(Long lastModified) {
		this.lastModified = lastModified;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public boolean validate() {
		return this.name != null
				&& this.lastModified != null
				&& this.lastModified > 0
				&& this.size != null
				&& this.size > 0
				&& this.type != null;
	}
	
	@Override
	public Set<String> generateNotUpdatableFieldsSet(){
		Set<String> result = new HashSet<String>();
		result.add("url");
		return result;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(contents, lastModified, name, size, type, url);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		File other = (File) obj;
		return Objects.equals(contents, other.contents) && Objects.equals(lastModified, other.lastModified)
				&& Objects.equals(name, other.name) && Objects.equals(size, other.size)
				&& Objects.equals(type, other.type) && Objects.equals(url, other.url);
	}

	@Override
	public String toString() {
		return "File [name=" + name + ", lastModified=" + lastModified + ", size=" + size + ", type=" + type
				+ ", contents=" + contents + ", url=" + url + "]";
	}
}
