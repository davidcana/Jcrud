package org.github.davidcana.jcrud.core.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalTime;
import java.util.List;

import org.github.davidcana.jcrud.core.ZCrudEntity;
import org.github.davidcana.jcrud.core.annotations.JCRUDEntity;
import org.github.davidcana.jcrud.core.model.storages.Complex2Detail2JDBCStorage;
import org.github.davidcana.jcrud.core.model.storages.Complex2DetailJDBCStorage;
import org.github.davidcana.jcrud.core.model.storages.Complex2JDBCStorage;
import org.github.davidcana.jcrud.core.requests.ZCrudRecords;
import org.github.davidcana.jcrud.storages.JDBC.annotations.JDBCEntity;
import org.github.davidcana.jcrud.storages.JDBC.annotations.JDBCId;
import org.github.davidcana.jcrud.storages.JDBC.annotations.JDBCOneToMany;
import org.github.davidcana.jcrud.storages.JDBC.annotations.JDBCOrderedByDefault;

@JCRUDEntity(
		storage = Complex2JDBCStorage.class,
		jsFilePath="target/jcrud-examples-0.1/javascript/options/complex2.js",
		jsFileVarName="constants.options.complex2"
)
@JDBCEntity(table = "complex2")
public class Complex2 implements ZCrudEntity {
	/*jcrud-class
    saveUserPreferences: true,
    
    pageConf: {
        defaultPageConf: {
            updateURL: 'CRUDManager.do?cmd=BATCH_UPDATE&table=complex2',
            getRecordURL: 'CRUDManager.do?cmd=GET&table=complex2'
        },
        pages: {
            list: {
                getGroupOfRecordsURL: 'CRUDManager.do?cmd=LIST&table=complex2',
                fields: [
                    {
                        type: 'fieldsGroup',
                        except: [ 'description', 'details', 'details2' ]
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
                    },
					filtering: {
		                isOn: true,
		                fields: [ 'id', 'name' ]
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
            en: [ 'en-common.json', 'en-complex2.json' ],
            es: [ 'es-common.json', 'es-complex2.json' ] 
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
	
	@JDBCId
	@JDBCOrderedByDefault(type = "ASC")
	private Integer id;
	/*jcrud-field
	 */
	private String name;
	/*jcrud-field
	 */
	private String description;
	/*jcrud-field
			type: 'textarea'
	 */
	private Integer secondaryId;
	/*jcrud-field
			type: 'select',
			options: '/jcrud/OptionsManager.do?table=secondary'
	 */
	private Boolean important;
	/*jcrud-field
			type: 'checkbox'
	 */
	private Float numberFloat;
	/*jcrud-field
	 */
	private Integer numberInteger;
	/*jcrud-field
	 */
	private Date birth;
	/*jcrud-field
			type: 'date'
	 */
	private LocalTime recordTime;
	/*jcrud-field
			type: 'time'
	 */
	private Timestamp recordDateTime;
	/*jcrud-field
			type: 'datetime'
	 */
	private List<Complex2Detail> details;
	/*jcrud-field
		type: 'subform',
		subformKey: 'id',
		fields: {
	        id: {
	
	        },
	        name: {
	
	        },
	        description: {
				type: 'textarea'
	
	        },
	        secondaryId: {
				type: 'select',
				options: '/jcrud/OptionsManager.do?table=secondary'
	
	        },
	        important: {
				type: 'checkbox'
	
	        },
	        numberFloat: {
	
	        },
	        numberInteger: {
	
	        },
	        birth: {
				type: 'date'
	
	        },
	        recordTime: {
				type: 'time'
	
	        },
	        recordDateTime: {
				type: 'datetime'
	
	        }
		}
	 */
	private List<Complex2Detail2> details2;
	/*jcrud-field
		type: 'subform',
		subformKey: 'id',
		fields: {
	        id: {
	
	        },
	        name: {
	
	        },
	        description: {
				type: 'textarea'
	
	        },
	        secondaryId: {
				type: 'select',
				options: '/jcrud/OptionsManager.do?table=secondary'
	
	        },
	        important: {
				type: 'checkbox'
	
	        },
	        numberFloat: {
	
	        },
	        numberInteger: {
	
	        },
	        birth: {
				type: 'date'
	
	        },
	        recordTime: {
				type: 'time'
	
	        },
	        recordDateTime: {
				type: 'datetime'
	
	        }
		}
	 */
	
	@JDBCOneToMany(storage = Complex2DetailJDBCStorage.class)
	private transient ZCrudRecords<Complex2Detail> detailsZCrudRecords;
	
	@JDBCOneToMany(storage = Complex2Detail2JDBCStorage.class)
	private transient ZCrudRecords<Complex2Detail2> details2ZCrudRecords;
	
	public Complex2(){}
	
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

	public Integer getSecondaryId() {
		return secondaryId;
	}

	public void setSecondaryId(Integer secondaryId) {
		this.secondaryId = secondaryId;
	}

	public Boolean getImportant() {
		return important;
	}

	public void setImportant(Boolean important) {
		this.important = important;
	}

	public Float getNumberFloat() {
		return numberFloat;
	}

	public void setNumberFloat(Float numberDouble) {
		this.numberFloat = numberDouble;
	}

	public Integer getNumberInteger() {
		return numberInteger;
	}

	public void setNumberInteger(Integer numberInteger) {
		this.numberInteger = numberInteger;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public LocalTime getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(LocalTime recordTime) {
		this.recordTime = recordTime;
	}

	public Timestamp getRecordDateTime() {
		return recordDateTime;
	}

	public void setRecordDateTime(Timestamp recordDateTime) {
		this.recordDateTime = recordDateTime;
	}

	@Override
	public String toString() {
		return "Complex2 [id=" + id + ", name=" + name + ", description=" + description + ", secondaryId=" + secondaryId
				+ ", important=" + important + ", numberFloat=" + numberFloat + ", numberInteger=" + numberInteger
				+ ", birth=" + birth + ", recordTime=" + recordTime + ", recordDateTime=" + recordDateTime
				+ ", details=" + details + ", details2=" + details2 + "]";
	}

	public List<Complex2Detail> getDetails() {
		return details;
	}

	public void setDetails(List<Complex2Detail> details) {
		this.details = details;
	}

	public List<Complex2Detail2> getDetails2() {
		return details2;
	}

	public void setDetails2(List<Complex2Detail2> details2) {
		this.details2 = details2;
	}

	public ZCrudRecords<Complex2Detail> getDetailsZCrudRecords() {
		return detailsZCrudRecords;
	}

	public void setDetailsZCrudRecords(ZCrudRecords<Complex2Detail> detailsZCrudRecords) {
		this.detailsZCrudRecords = detailsZCrudRecords;
	}

	public ZCrudRecords<Complex2Detail2> getDetails2ZCrudRecords() {
		return details2ZCrudRecords;
	}

	public void setDetails2ZCrudRecords(ZCrudRecords<Complex2Detail2> details2zCrudRecords) {
		details2ZCrudRecords = details2zCrudRecords;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((birth == null) ? 0 : birth.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((details == null) ? 0 : details.hashCode());
		result = prime * result + ((details2 == null) ? 0 : details2.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((important == null) ? 0 : important.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((numberFloat == null) ? 0 : numberFloat.hashCode());
		result = prime * result + ((numberInteger == null) ? 0 : numberInteger.hashCode());
		result = prime * result + ((recordDateTime == null) ? 0 : recordDateTime.hashCode());
		result = prime * result + ((recordTime == null) ? 0 : recordTime.hashCode());
		result = prime * result + ((secondaryId == null) ? 0 : secondaryId.hashCode());
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
		Complex2 other = (Complex2) obj;
		if (birth == null) {
			if (other.birth != null)
				return false;
		} else if (!birth.equals(other.birth))
			return false;
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
		if (important == null) {
			if (other.important != null)
				return false;
		} else if (!important.equals(other.important))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (numberFloat == null) {
			if (other.numberFloat != null)
				return false;
		} else if (!numberFloat.equals(other.numberFloat))
			return false;
		if (numberInteger == null) {
			if (other.numberInteger != null)
				return false;
		} else if (!numberInteger.equals(other.numberInteger))
			return false;
		if (recordDateTime == null) {
			if (other.recordDateTime != null)
				return false;
		} else if (!recordDateTime.equals(other.recordDateTime))
			return false;
		if (recordTime == null) {
			if (other.recordTime != null)
				return false;
		} else if (!recordTime.equals(other.recordTime))
			return false;
		if (secondaryId == null) {
			if (other.secondaryId != null)
				return false;
		} else if (!secondaryId.equals(other.secondaryId))
			return false;
		return true;
	}

}
