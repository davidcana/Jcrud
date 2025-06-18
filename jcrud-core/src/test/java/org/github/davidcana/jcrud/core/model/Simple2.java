package org.github.davidcana.jcrud.core.model;

import java.util.ArrayList;
import java.util.List;

import org.github.davidcana.jcrud.core.ZCrudEntity;
import org.github.davidcana.jcrud.core.annotations.JCRUDEntity;
import org.github.davidcana.jcrud.core.annotations.JCRUDId;
import org.github.davidcana.jcrud.core.model.storages.Simple2VoidStorage;
import org.github.davidcana.jcrud.core.requests.ZCrudRecords;

@JCRUDEntity(
		storage = Simple2VoidStorage.class,
		jsFilePath="target/simple2.js",
		jsFileVarName="constants.options.simple2"
)
public class Simple2 implements ZCrudEntity {
	/*jcrud-class
    saveUserPreferences: true,
    
    pageConf: {
        defaultPageConf: {
            updateURL: 'CRUDManager.do?cmd=BATCH_UPDATE&table=simple2',
            getRecordURL: 'CRUDManager.do?cmd=GET&table=simple2'
        },
        pages: {
            list: {
                getGroupOfRecordsURL: 'CRUDManager.do?cmd=LIST&table=simple2',
                fields: [
                    {
                        type: 'fieldsGroup',
                        except: [ 'details', 'details2' ]
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
                        type: 'fieldsGroup'
                    }
                ]
            }, 
            update: {
                fields: [
                    {
                        type: 'fieldsGroup'
                    }
                ]
            }, 
            delete: {
                fields: [
                    {
                        type: 'fieldsGroup'
                    }
                ]
            }
        }
    },

    i18n: {
        language: 'es',
        files: { 
            en: [ 'en-common.json', 'en-simple2.json' ],
            es: [ 'es-common.json', 'es-simple2.json' ] 
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
	/*jcrud-field
	 */
	private String name;
	/*jcrud-field
		attributes:{
		    rowHeader: {
		        style: 'width:40%'
		    }
		}
	 */
	private String description;
	/*jcrud-field
		type: 'textarea'
	 */
	private List<Simple2Detail> details = new ArrayList<>();
	/*jcrud-field
		type: 'subform',
		subformKey: 'id',
		fields: {
		    id: {},
			name: {},
			description: {
				type: 'textarea',
				attributes: {
					field: {
						rows: 2,
						cols: 90
					}
				}
			}
		}
	 */
	private List<Simple2Detail2> details2 = new ArrayList<>();
	/*jcrud-field
		type: 'subform',
		subformKey: 'id',
		fields: {
		    id: {},
			name: {},
			description: {
				type: 'textarea',
				attributes: {
					field: {
						rows: 2,
						cols: 90
					}
				}
			}
		}
	 */
	
	private transient ZCrudRecords<Simple2Detail> detailsZCrudRecords;
	
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
