"use strict";

constants.options.complex2 = {
    entityId: 'complex2',
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
    validation: {
        modules: 'security, date',
        rules: {}
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
,

    key: 'id',
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

        },
        details: {
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

        },
        details2: {
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

        },
	}
}
