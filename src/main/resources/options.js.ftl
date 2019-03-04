"use strict";

var ${varName} = {
    entityId: '${entityId}',
${classContents},

    key: '${key}',
    fields: {
	<#list fields as field>
        ${field.id}: {
${field.contents}
        },
	</#list>
	}
}
