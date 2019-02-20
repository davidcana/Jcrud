"use strict";

var ${varName} = {
    "entityId": "${entityId}",
    "saveUserPreferences": true,
    
    "pageConf": {
        "defaultPageConf": {
            "updateURL": "${pageConf-defaultPageConf-updateURL}",
            "getRecordURL": "${pageConf-defaultPageConf-getRecordURL}",
            "getGroupOfRecordsURL": "${pageConf-defaultPageConf-getGroupOfRecordsURL}"
        },
        "pages": {
            "list": {
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
                            "fieldId": "${pageConf-pages-list-components-sorting-default-fieldId}",
                            "type": "${pageConf-pages-list-components-sorting-default-type}"
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

    "key" : "${key}",
    "fields": {
	<#list fields as field>
        "${field.name}": {
			"type": "${field.type}"
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
            "en": [ "en-common.json", "en-simple.json" ],
            "es": [ "es-common.json", "es-simple.json" ] 
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
