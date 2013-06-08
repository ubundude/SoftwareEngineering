<%@ page import="elearn.SectionNews" %>



<div class="fieldcontain ${hasErrors(bean: sectionNewsInstance, field: 'newsBody', 'error')} ">
    <label for="newsBody">
        <g:message code="sectionNews.newsBody.label" default="News Body"/>

    </label>
    <g:textField name="newsBody" value="${sectionNewsInstance?.newsBody}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: sectionNewsInstance, field: 'section', 'error')} required">
    <label for="section">
        <g:message code="sectionNews.section.label" default="Section"/>
        <span class="required-indicator">*</span>
    </label>
    <g:select id="section" name="section.id" from="${elearn.Section.list()}" optionKey="id" required=""
              value="${sectionNewsInstance?.section?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: sectionNewsInstance, field: 'title', 'error')} ">
    <label for="title">
        <g:message code="sectionNews.title.label" default="Title"/>

    </label>
    <g:textField name="title" value="${sectionNewsInstance?.title}"/>
</div>

