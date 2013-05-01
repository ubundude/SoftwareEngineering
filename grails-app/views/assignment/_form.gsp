<%@ page import="elearn.Assignment" %>



<div class="fieldcontain ${hasErrors(bean: assignmentInstance, field: 'assignmentCategories', 'error')} required">
	<label for="assignmentCategories">
		<g:message code="assignment.assignmentCategories.label" default="Assignment Categories" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="assignmentCategories" name="assignmentCategories.id" from="${elearn.AssignmentCategories.list()}" optionKey="id" required="" value="${assignmentInstance?.assignmentCategories?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: assignmentInstance, field: 'dateDue', 'error')} required">
	<label for="dateDue">
		<g:message code="assignment.dateDue.label" default="Date Due" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="dateDue" precision="day"  value="${assignmentInstance?.dateDue}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: assignmentInstance, field: 'maxPoints', 'error')} required">
	<label for="maxPoints">
		<g:message code="assignment.maxPoints.label" default="Max Points" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="maxPoints" type="number" value="${assignmentInstance.maxPoints}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: assignmentInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="assignment.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${assignmentInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: assignmentInstance, field: 'section', 'error')} required">
	<label for="section">
		<g:message code="assignment.section.label" default="Section" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="section" name="section.id" from="${elearn.Section.list()}" optionKey="id" required="" value="${assignmentInstance?.section?.id}" class="many-to-one"/>
</div>

