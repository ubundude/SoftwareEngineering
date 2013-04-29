<div class="fieldcontain ${hasErrors(field: 'name', 'error')} ">
    <label for="name">
        <g:message code="name.label" default="Name" />

    </label>
    <g:textField name="name" />
</div>

<div class="fieldcontain ${hasErrors(field: 'section', 'error')} required">
    <label for="section">
        <g:message code="section.label" default="Section" />
        <span class="required-indicator">*</span>
    </label>
    <g:select id="section" name="section.id" from="${elearn.Section.list()}" optionKey="id" required="" class="many-to-one"/>
</div>