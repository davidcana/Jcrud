package org.github.davidcana.jcrud.core.options;

public class Option<K> {

	private K value;
	private String displayText;
	
	public Option(K value, String displayText) {
		this();
		this.value = value;
		this.displayText = displayText;
	}
	
	public Option(K value) {
		this();
		this.value = value;
		this.displayText = value.toString();
	}
	
	public Option() {
		super();
	}

	public K getValue() {
		return value;
	}

	public void setValue(K value) {
		this.value = value;
	}

	public String getDisplayText() {
		return displayText;
	}

	public void setDisplayText(String displayText) {
		this.displayText = displayText;
	}

	@Override
	public String toString() {
		return "Option [value=" + value + ", displayText=" + displayText + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((displayText == null) ? 0 : displayText.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		Option other = (Option) obj;
		if (displayText == null) {
			if (other.displayText != null)
				return false;
		} else if (!displayText.equals(other.displayText))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
	
}
