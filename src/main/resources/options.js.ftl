"use strict";

<#if varName?contains(".")>
${varName} = {
<#else>
var ${varName} = {
</#if>
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
