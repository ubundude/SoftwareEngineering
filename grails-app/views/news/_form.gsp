<%@ page import="elearn.News" %>



<div class="fieldcontain ${hasErrors(bean: newsInstance, field: 'title', 'error')} ">
    <label for="title">
        <g:message code="news.title.label" default="Title" />

    </label>
    <g:textField name="title" value="${newsInstance?.title}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: newsInstance, field: 'newsBody', 'error')} ">
	<label for="newsBody">
		<g:message code="news.newsBody.label" default="News Body" />
		
	</label>
	<g:textArea name="newsBody" value="${newsInstance?.newsBody}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: newsInstance, field: 'expires', 'error')} required">
    <label for="expires">
        <g:message code="news.expires.label" default="Expires" />
        <span class="required-indicator">*</span>
    </label>
    <g:datePicker name="expires" precision="day"  value="${newsInstance?.expires}"  />
</div>