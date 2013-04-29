<%@ page import="elearn.Assignment" %>

<div class="fieldcontain ${hasErrors(bean: assignmentInstance, field: 'dateDue', 'error')} required">
	<label for="dateDue">
		<g:message code="assignment.dateDue.label" default="Date Due" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="dateDue" precision="day" value="${assignmentInstance?.dateDue}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: assignmentInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${assignmentInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: assignmentInstance, field: 'section', 'error')} required">
	<label for="section">
		<g:message code="section.label" default="Section" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="section" name="section.id" from="${elearn.Section.list()}" optionKey="id" required="" value="${assignmentInstance?.section?.id}" class="many-to-one"/>
</div>

