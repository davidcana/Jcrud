package org.github.davidcana.jcrud.core.model;

import java.util.List;

import org.github.davidcana.jcrud.core.ZCrudEntity;
import org.github.davidcana.jcrud.core.annotations.JCRUDEntity;
import org.github.davidcana.jcrud.core.model.storages.FormStorage;
import org.github.davidcana.jcrud.core.requests.ZCrudRecords;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JCRUDEntity(
		storage = FormStorage.class,
		jsFilePath="target/jcrud-examples-0.1/javascript/options/form.js",
		jsFileVarName="constants.options.form"
)
public class Form implements ZCrudEntity {
	/*jcrud-class
    saveUserPreferences: false,
    
    pageConf: {
        defaultPageConf: {
            updateURL: 'CRUDManager.do?cmd=BATCH_UPDATE&table=form',
            getRecordURL: 'CRUDManager.do?cmd=GET&table=form'
        },
        pages: {
            list: {
                template: "formDefaultTemplate@templates/forms.html",
                fields: [ 
                    'originalMembers', 
                    'verifiedMembers'
                ],
                components: {
                    paging: {
                        isOn: false
                    },
                    filtering: {
                        isOn: true,
                        forceToFilter: true,
                        fields: [ 
                            {
                                id: 'year',
                                type: 'select',
                                translateOptions: false,
                                options: function(){
                                    var result = [];
                                    for ( var i = 2010; i < 2020; ++i ){
                                        result.push( i );
                                    }
                                    return result;
                                }
                            }
                        ]
                    }   
                },
                buttons: {
                    toolbar: [ 
                        'undo', 
                        'redo', 
                        'form_submit',
                        {
                            type: 'form_copySubformRows',
                            source: 'originalMembers',
                            target: 'verifiedMembers',
                            onlySelected: true,
                            removeFromSource: false,
                            deselect: true,
                            title: 'Copy original members',
                            textsBundle: {
                                title: undefined,
                                content: {
                                    translate: false,
                                    text: 'Copy original members'
                                }  
                            }
                        }
                    ]
                }
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
	
	private List<Member> originalMembers;
	/*jcrud-field
			type: 'subform',
			getGroupOfRecordsURL: 'http://localhost/CRUDManager.do?cmd=LIST&table=members',
            readOnly: true,
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
				recordDateTime: {
					type: 'datetime'
				}
			},
            components: {
                paging: {
                    isOn: true
                },
                selecting: {
                    isOn: true
                }
            },
            buttons: {
                toolbar: [],
                byRow: []
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
				recordDateTime: {
					type: 'datetime'
				}
			},
            buttons: {
                toolbar: [ 'subform_addNewRow' ]
            }
	 */

	@JsonInclude(Include.NON_NULL)
	private transient ZCrudRecords<Member> verifiedMembersZCrudRecords;
	
	public Form(){}

	public List<Member> getOriginalMembers() {
		return originalMembers;
	}

	public void setOriginalMembers(List<Member> originalMember) {
		this.originalMembers = originalMember;
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
		return "Form [originalMembers=" + originalMembers + ", verifiedMembers=" + verifiedMembers + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((originalMembers == null) ? 0 : originalMembers.hashCode());
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
		if (originalMembers == null) {
			if (other.originalMembers != null)
				return false;
		} else if (!originalMembers.equals(other.originalMembers))
			return false;
		if (verifiedMembers == null) {
			if (other.verifiedMembers != null)
				return false;
		} else if (!verifiedMembers.equals(other.verifiedMembers))
			return false;
		return true;
	}

}
