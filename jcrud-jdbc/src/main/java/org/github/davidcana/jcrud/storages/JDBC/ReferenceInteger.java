package org.github.davidcana.jcrud.storages.JDBC;

import java.util.Objects;

public class ReferenceInteger {
	
	private int integer;
	
	public ReferenceInteger(int integer) {
		this.integer = integer;
	}
	
	public ReferenceInteger() {
		this(0);
	}

	public int getInteger() {
		return integer;
	}

	public void setInteger(int integer) {
		this.integer = integer;
	}
	
	public void inc() {
		++this.integer;
	}

	@Override
	public String toString() {
		return "ReferenceInteger [integer=" + integer + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(integer);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReferenceInteger other = (ReferenceInteger) obj;
		return integer == other.integer;
	}
}
