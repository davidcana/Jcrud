"use strict";

constants.options.form = {
    entityId: 'form',
    saveUserPreferences: false,
    
    pageConf: {
        defaultPageConf: {
            updateURL: 'CRUDManager.do?cmd=BATCH_UPDATE&table=form',
            getRecordURL: 'CRUDManager.do?cmd=GET&table=form'
        },
        pages: {
            customForm: {
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
    
    key: 'id',

    fields: {
        originalMembers: {
			type: 'subform',
			getGroupOfRecordsURL: 'CRUDManager.do?cmd=LIST&table=originalMember',
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

        },
        verifiedMembers: {
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
			}
        }
	}
}
