<%@ page import="elearn.AssignmentCategories" %>



<div class="fieldcontain ${hasErrors(bean: assignmentCategoriesInstance, field: 'name', 'error')} ">
    <label for="name">
        <g:message code="assignmentCategories.name.label" default="Name"/>

    </label>
    <g:textField name="name" value="${assignmentCategoriesInstance?.name}"/>
</div>

