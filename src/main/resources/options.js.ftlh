"use strict";

var ${options.varName} = {
    "entityId": "${options.entityId}",
    ${options.classContents},

    "key" : "${options.key}",
    "fields": {
	<#list options.fields as field>
        "${field.id}": {
            ${field.contents}
        }
	</#list>
	}
}
