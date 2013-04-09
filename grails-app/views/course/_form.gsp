<%@ page import="school.Course" %>

<div class="fieldcontain ${hasErrors(bean: courseInstance, field: 'code', 'error')} ">
    <label for="code">
        <g:message code="course.code.label" default="Code"/>

    </label>
    <g:textField name="code" value="${courseInstance?.code}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: courseInstance, field: 'credits', 'error')} required">
    <label for="credits">
        <g:message code="course.credits.label" default="Credits"/>
        <span class="required-indicator">*</span>
    </label>
    <g:field name="credits" type="number" value="${courseInstance.credits}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: courseInstance, field: 'description', 'error')} ">
    <label for="description">
        <g:message code="course.description.label" default="Description"/>

    </label>
    <g:textField name="description" value="${courseInstance?.description}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: courseInstance, field: 'name', 'error')} ">
    <label for="name">
        <g:message code="name.label" default="Name"/>

    </label>
    <g:textField name="name" value="${courseInstance?.name}"/>
</div>


