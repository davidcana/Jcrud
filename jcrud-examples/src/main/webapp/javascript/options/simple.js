"use strict";

constants.options.simple = {
    entityId: 'simple',
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
                        type: 'fieldsGroup'
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
,

    key: 'id',
    fields: {
        id: {

        },
        name: {
		    attributes:{
		        rowHeader: {
		            style: 'width:40%'
		        }
		    }

        },
        description: {
			type: 'textarea'

        },
	}
}
