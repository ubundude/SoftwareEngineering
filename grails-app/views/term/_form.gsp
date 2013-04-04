<%@ page import="school.Term" %>



<div class="fieldcontain ${hasErrors(bean: termInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="term.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${termInstance?.name}"/>
</div>

