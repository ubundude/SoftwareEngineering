<%@ page import="elearn.Content" %>



<div class="fieldcontain ${hasErrors(bean: contentInstance, field: 'summary', 'error')} ">
	<label for="summary">
		<g:message code="content.summary.label" default="Summary" />
		
	</label>
	<g:textField name="summary" value="${contentInstance?.summary}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: contentInstance, field: 'contentURI', 'error')} ">
	<label for="contentURI">
		<g:message code="content.contentURI.label" default="Content URI" />
		
	</label>
	<g:textField name="contentURI" value="${contentInstance?.contentURI}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: contentInstance, field: 'section', 'error')} required">
	<label for="section">
		<g:message code="content.section.label" default="Section" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="section" name="section.id" from="${elearn.Section.list()}" optionKey="id" required="" value="${contentInstance?.section?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: contentInstance, field: 'title', 'error')} ">
	<label for="title">
		<g:message code="content.title.label" default="Title" />
		
	</label>
	<g:textField name="title" value="${contentInstance?.title}"/>
</div>

