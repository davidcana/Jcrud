package org.github.davidcana.jcrud.core.optionsFiles;

import java.util.ArrayList;
import java.util.List;

/*
"use strict";

var ${options.varName} = {
    "entityId": "${options.entityId}",
    ${options.classContents},

    "key" : "${options.key}",
    "fields": {
	<#list options.fields as field>
        "${field.id}": {
            ${field.contents}
        }
	</#list>
	}
}
*/
public class OptionsFile {
	
	private String varName;
	private String entityId;
	private String classContents;
	private String key;
	private List<OptionsFileField> fields = new ArrayList<>();
	
	public OptionsFile() {}

	public String getVarName() {
		
		if (varName == null) {
			varName = this.buildVarName();
		}
		
		return varName;
	}

	public void setVarName(String varName) {
		this.varName = varName;
	}

	public String getEntityId() {
		
		if (entityId == null) {
			throw new IllegalArgumentException("entityId not set in optionsFile!");
		}
		
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	public String getClassContents() {
		
		if (classContents == null) {
			throw new IllegalArgumentException("classContents not set in optionsFile!");
		}
		
		return classContents;
	}

	public void setClassContents(String classContents) {
		this.classContents = classContents;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public List<OptionsFileField> getFields() {
		return fields;
	}

	public void setFields(List<OptionsFileField> fields) {
		this.fields = fields;
	}

	public void add(OptionsFileField optionsFileField) {
		this.fields.add(optionsFileField);
	}
	
	@Override
	public String toString() {
		return "OptionsFile [varName=" + this.getVarName() + ", entityId=" + entityId + ", classContents=" + classContents
				+ ", key=" + key + ", fields=" + fields + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((classContents == null) ? 0 : classContents.hashCode());
		result = prime * result + ((entityId == null) ? 0 : entityId.hashCode());
		result = prime * result + ((fields == null) ? 0 : fields.hashCode());
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		result = prime * result + ((varName == null) ? 0 : varName.hashCode());
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
		OptionsFile other = (OptionsFile) obj;
		if (classContents == null) {
			if (other.classContents != null)
				return false;
		} else if (!classContents.equals(other.classContents))
			return false;
		if (entityId == null) {
			if (other.entityId != null)
				return false;
		} else if (!entityId.equals(other.entityId))
			return false;
		if (fields == null) {
			if (other.fields != null)
				return false;
		} else if (!fields.equals(other.fields))
			return false;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		if (varName == null) {
			if (other.varName != null)
				return false;
		} else if (!varName.equals(other.varName))
			return false;
		return true;
	}
	
	private String buildVarName() {
		
		String part = this.getEntityId();
		
		return part.substring(0, 1).toLowerCase() + part.substring(1) +"Options";
	}	
}
