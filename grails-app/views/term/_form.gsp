<%@ page import="elearn.Term" %>



<div class="fieldcontain ${hasErrors(bean: termInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${termInstance?.name}"/>
</div>

