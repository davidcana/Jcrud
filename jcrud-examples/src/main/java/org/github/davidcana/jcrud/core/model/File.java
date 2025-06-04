package org.github.davidcana.jcrud.core.model;

import java.util.Objects;

public class File {
	
    private String name;
    private long lastModified;
    private long size;
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

	public long getLastModified() {
		return lastModified;
	}

	public void setLastModified(long lastModified) {
		this.lastModified = lastModified;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
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
		return Objects.equals(contents, other.contents) && lastModified == other.lastModified
				&& Objects.equals(name, other.name) && size == other.size && Objects.equals(type, other.type)
				&& Objects.equals(url, other.url);
	}

	@Override
	public String toString() {
		return "File [name=" + name + ", lastModified=" + lastModified + ", size=" + size + ", type=" + type
				+ ", contents=" + contents + ", url=" + url + "]";
	}
}
