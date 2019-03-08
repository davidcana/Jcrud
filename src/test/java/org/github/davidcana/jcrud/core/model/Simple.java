package org.github.davidcana.jcrud.core.model;

import org.github.davidcana.jcrud.core.ZCrudEntity;
import org.github.davidcana.jcrud.core.annotations.JCRUDEntity;
import org.github.davidcana.jcrud.core.annotations.JCRUDId;
import org.github.davidcana.jcrud.storages.SimpleVoidStorage;

@JCRUDEntity(storage = SimpleVoidStorage.class, jsFilePath="target/classes/simple.js")
public class Simple implements ZCrudEntity {
	/*jcrud
    saveUserPreferences: true,
    
    pageConf: {
        defaultPageConf: {
            updateURL: 'CRUDManager.do?cmd=BATCH_UPDATE&table=simple',
            getRecordURL: 'CRUDManager.do?cmd=GET&table=simple'
        },
        pages: {
            list: {
                getGroupOfRecordsURL: 'CRUDManager.do?cmd=LIST&table=simple',
                fields: [
                    {
                        "type": "fieldsGroup"
                    }
                ],
                components: {
                    paging: {
                        defaultPageSize: 5,
                        pageSizes: [5, 10, 15, 50, 100]
                    },
                    sorting: {
                        isOn: true,
                        default: {
                            fieldId: 'id',
                            type: 'asc'
                        },
                        allowUser: true
                    }
                }
            }, 
            create: {
                fields: [
                    {
                        "type": "fieldsGroup"
                    }
                ]
            }, 
            update: {
                fields: [
                    {
                        "type": "fieldsGroup"
                    }
                ]
            }, 
            delete: {
                fields: [
                    {
                        "type": "fieldsGroup"
                    }
                ]
            }
        }
    },
    
    validation: {
        modules: 'security, date',
        rules: {}
    },

    i18n: {
        language: 'es',
        files: { 
            en: [ 'en-common.json', 'en-simple.json' ],
            es: [ 'es-common.json', 'es-simple.json' ] 
        }
    },

    logging: {
        isOn: false
    },
    
    ajax: {
        ajaxPreFilter: function( data ){
            return JSON.stringify( data );
        }
    }
	 */
	
	@JCRUDId
	private Integer id;
	/*jcrud
	 */
	private String name;
	/*jcrud
		    attributes:{
		        rowHeader: {
		            style: 'width:40%'
		        }
		    }
    */
	private String description;
	/*jcrud
			type: 'textarea'
	 */
	
	public Simple(){}
	
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
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
		Simple other = (Simple) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
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
		return "Simple [id=" + id + ", name=" + name + ", description=" + description + "]";
	}

}
