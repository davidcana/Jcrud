"use strict";

var ${options.varName} = {
    "entityId": "${options.entityId}",
    "saveUserPreferences": true,
    
    "pageConf": {
        "defaultPageConf": {
            "updateURL": "${options.updateURL}",
            "getRecordURL": "${options.getRecordURL}"
        },
        "pages": {
            "list": {
            "getGroupOfRecordsURL": "${options.getGroupOfRecordsURL}",
                "fields": [
                    {
                        "type": "fieldsGroup"
                    }
                ],
                "components": {
                    "paging": {
                        "defaultPageSize": 5,
                        "pageSizes": [5, 10, 15, 50, 100]
                    },
                    "sorting": {
                        "isOn": true,
                        "default": {
                            "fieldId": "${options.listSortingDefaultFieldId}",
                            "type": "${options.listSortingDefaultType}"
                        },
                        "allowUser": true
                    }
                }
            }, 
            "create": {
                "fields": [
                    {
                        "type": "fieldsGroup"
                    }
                ]
            }, 
            "update": {
                "fields": [
                    {
                        "type": "fieldsGroup"
                    }
                ]
            }, 
            "delete": {
                "fields": [
                    {
                        "type": "fieldsGroup"
                    }
                ]
            }
        }
    },

    "key" : "${options.key}",
    "fields": {
	<#list options.fields as field>
        "${field.id}": {
			<#list field.properties as property>
				"${property.key}": "${property.value}"
			</#list>
        }
	</#list>
	},
    "fieldsCustomization": {
        "name": {
            "attributes":{
                "rowHeader": {
                    "style": "width:40%"
                }
            }
        }
    },

    "validation": {
        "modules": "security, date",
        "rules": {}
    },

    "i18n": {
        "language": "es",
        "files": { 
            "en": [ "en-common.json", "en-${options.entityId}.json" ],
            "es": [ "es-common.json", "es-${options.entityId}.json" ] 
        }
    },

    "logging": {
        "isOn": false
    },
    
    "ajax": {
        "ajaxPreFilter": function( data ){ 
			return JSON.stringify( data )
		};
    }
}
