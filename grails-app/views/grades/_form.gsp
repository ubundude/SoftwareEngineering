<%@ page import="elearn.Assignment; elearn.Grades" %>



<div class="fieldcontain ${hasErrors(bean: gradesInstance, field: 'assignment', 'error')} required">
    <label for="assignment">
        <g:message code="assignment.label" default="Assignment"/>
        <span class="required-indicator">*</span>
    </label>
    <g:select id="assignment" name="assignment.id" from="${Assignment.list()}" optionKey="id" required=""
              value="${gradesInstance?.assignment?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: gradesInstance, field: 'grade', 'error')} ">
    <label for="grade">
        <g:message code="grades.label" default="Grade"/>

    </label>
    <g:textField name="grade" value="${gradesInstance?.grade}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: gradesInstance, field: 'students', 'error')} required">
    <label for="students">
        <g:message code="students.label" default="Students"/>
        <span class="required-indicator">*</span>
    </label>
    <g:select id="students" name="students.id" from="${students}" optionKey="id" required=""
              value="${gradesInstance?.students?.id}" class="many-to-one"/>
</div>

