<%@ page import="school.Section; school.Assignment" %>

<div class="fieldcontain ${hasErrors(bean: assignmentInstance, field: 'dateDue', 'error')} required">
	<label for="dateDue">
		<g:message code="assignment.dateDue.label" default="Date Due" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="dateDue" precision="day"  value="${assignmentInstance?.dateDue}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: assignmentInstance, field: 'grades', 'error')} ">
	%{--@declare id="grades"--}%<label for="grades">
		<g:message code="grades.label" default="Grades" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${assignmentInstance?.grades?}" var="g">
    <li><g:link controller="grades" action="show" id="${g.id}">${g?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="grades" action="create" params="['assignment.id': assignmentInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'grades.label', default: 'Grades')])}</g:link>
</li>
</ul>

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
	<g:select id="section" name="section.id" from="${Section.list()}" optionKey="id" required="" value="${assignmentInstance?.section?.id}" class="many-to-one"/>
</div>

