package org.github.davidcana.jcrud.core.model;

import java.util.List;

import org.github.davidcana.jcrud.core.ZCrudEntity;
import org.github.davidcana.jcrud.core.annotations.JCRUDEntity;
import org.github.davidcana.jcrud.core.model.storages.Simple2JDBCStorage;
import org.github.davidcana.jcrud.core.requests.ZCrudRecords;

@JCRUDEntity(
		storage = Simple2JDBCStorage.class,
		jsFilePath="target/jcrud-examples-0.1/javascript/options/form.js",
		jsFileVarName="constants.options.form"
)
public class Form implements ZCrudEntity {
	/*jcrud-class
    saveUserPreferences: true,
    
    pageConf: {
        defaultPageConf: {
            updateURL: 'CRUDManager.do?cmd=BATCH_UPDATE&table=form',
            getRecordURL: 'CRUDManager.do?cmd=GET&table=form'
        },
        pages: {
            list: {
                getGroupOfRecordsURL: 'CRUDManager.do?cmd=LIST&table=form',
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

    validation: {
        modules: 'security, date',
        rules: {}
    },

    i18n: {
        language: 'es',
        files: { 
            en: [ 'en-common.json', 'en-form.json' ],
            es: [ 'es-common.json', 'es-form.json' ] 
        }
    },

    logging: {
        isOn: false
    },
    
    ajax: {
        ajaxPreFilter: function( data ){
            return JSON.stringify( data );
        }
    },
    
    key: 'id'
	 */
	
	private List<Member> originalMember;
	/*jcrud-field
		type: 'subform',
		subformKey: 'id',
		fields: {
		    id: {},
			name: {},
			country: {
	            type: 'select',
	            translateOptions: false,
	            options: [
	                { value: 1, displayText: 'France' }, 
	                { value: 2, displayText: 'Italy' },
	                { value: 3, displayText: 'Portugal' }, 
	                { value: 4, displayText: 'Spain' }, 
	                { value: 5, displayText: 'UK' }
	            ],
	            defaultValue: '4'
			},
			dateTime: {
				type: 'datetime'
			}
		}
	 */
	private List<Member> verifiedMembers;
	/*jcrud-field
		type: 'subform',
		subformKey: 'id',
		fields: {
		    id: {},
			name: {},
			country: {
	            type: 'select',
	            translateOptions: false,
	            options: [
	                { value: 1, displayText: 'France' }, 
	                { value: 2, displayText: 'Italy' },
	                { value: 3, displayText: 'Portugal' }, 
	                { value: 4, displayText: 'Spain' }, 
	                { value: 5, displayText: 'UK' }
	            ],
	            defaultValue: '4'
			},
			dateTime: {
				type: 'datetime'
			}
		}
	 */
	
	private transient ZCrudRecords<Member> verifiedMembersZCrudRecords;
	
	public Form(){}

	public List<Member> getOriginalMember() {
		return originalMember;
	}

	public void setOriginalMember(List<Member> originalMember) {
		this.originalMember = originalMember;
	}

	public List<Member> getVerifiedMembers() {
		return verifiedMembers;
	}

	public void setVerifiedMembers(List<Member> verifiedMembers) {
		this.verifiedMembers = verifiedMembers;
	}

	public ZCrudRecords<Member> getVerifiedMembersZCrudRecords() {
		return verifiedMembersZCrudRecords;
	}

	public void setVerifiedMembersZCrudRecords(ZCrudRecords<Member> verifiedMembersZCrudRecords) {
		this.verifiedMembersZCrudRecords = verifiedMembersZCrudRecords;
	}

	@Override
	public String toString() {
		return "Form [originalMember=" + originalMember + ", verifiedMembers=" + verifiedMembers + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((originalMember == null) ? 0 : originalMember.hashCode());
		result = prime * result + ((verifiedMembers == null) ? 0 : verifiedMembers.hashCode());
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
		Form other = (Form) obj;
		if (originalMember == null) {
			if (other.originalMember != null)
				return false;
		} else if (!originalMember.equals(other.originalMember))
			return false;
		if (verifiedMembers == null) {
			if (other.verifiedMembers != null)
				return false;
		} else if (!verifiedMembers.equals(other.verifiedMembers))
			return false;
		return true;
	}

}
