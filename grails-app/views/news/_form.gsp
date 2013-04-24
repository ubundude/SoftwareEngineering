<%@ page import="school.News" %>



<div class="fieldcontain ${hasErrors(bean: newsInstance, field: 'newsBody', 'error')} ">
	<label for="newsBody">
		<g:message code="news.newsBody.label" default="News Body" />
		
	</label>
	<g:textField name="newsBody" value="${newsInstance?.newsBody}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: newsInstance, field: 'title', 'error')} ">
	<label for="title">
		<g:message code="news.title.label" default="Title" />
		
	</label>
	<g:textField name="title" value="${newsInstance?.title}"/>
</div>

