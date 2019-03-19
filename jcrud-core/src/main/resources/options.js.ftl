"use strict";

<#if varName?contains(".")>
${varName} = {
<#else>
var ${varName} = {
</#if>
    entityId: '${entityId}',
${classContents},

<#if key??>
    key: '${key}',
<#else>
</#if>
    fields: {
	<#list fields as field>
        ${field.id}: {
${field.contents}
        },
	</#list>
	}
}
