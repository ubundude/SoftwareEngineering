<div class="fieldcontain">
	<label for="title">
        <g:message code="news.title" default="Title" />
    </label>
    <g:textField name="title" />
</div>

<div class="fieldcontain">
	<label for="news">
        <g:message code="news.body" default="Message" />
    </label>
    <g:textArea name="news" />
</div>
<div class="fieldcontain">
	<label for="expires">
        <g:message code="news.expires" default="Expires" />
    </label>
    <g:datePicker name="expires" precision="due"  />
</div>