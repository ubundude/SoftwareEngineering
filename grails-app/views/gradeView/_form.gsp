
<div class="fieldcontain ${hasErrors(field: 'name', 'error')} ">
    <label for="name">
        <g:message code="name.label" default="Name" />

    </label>
    <g:textField name="name" />
</div>

<div class="fieldcontain ${hasErrors(field: 'maxPoints', 'error')} ">
    <label for="maxPoints">
        <g:message code="assignment.maxPoints" default="Maximum Points" />

    </label>
    <g:textField name="maxPoints" id="maxPoints" />
</div>

<div class="fieldcontain ${hasErrors(field: 'section', 'error')} required">
    <label for="category">
        <g:message code="categories.label" default="Categories" />

    </label>
    <g:select id="assignmentCategories" name="assignmentCategories" from="${elearn.AssignmentCategories.list()}" optionKey="id" required="" class="many-to-one"/>
</div>