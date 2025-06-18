"use strict";

constants.options.simple2 = {
    entityId: 'simple2',
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
        details: {
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

        },
        details2: {
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

        },
	}
}
