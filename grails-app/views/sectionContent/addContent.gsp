<!doctype html>
<html>
<head>
    <meta name="layout" content="bootstrap"/>
    <title>Homepage</title>
</head>

<body>
<div class="row-fluid">
    <aside id="application-status" class="span3">
        <div class="well sidebar-nav">
            <h5>User Info</h5>
            <p>This section to be determined soon.</p>
        </div>
    </aside>
    <section id="main" class="span9">
        <g:render template="../menu" />
        <div class="row-fluid">
            <div class="span3">
                <g:form action="upload" enctype="multipart/form-data" method="post" >
                    <input type="hidden" name="section" id="section" value="${section}" />
                    <label for="title">
                        <g:message code="name.label" default="Name" />
                        <span class="required-indicator">*</span>
                    </label>
                        <input type="text" name="title" id="title" />
                    <label for="summary">
                        <g:message code="content.summary" default="Summary" />

                    </label>
                        <input type="text" name="summary" id="summary" />
                    <label for="uploadedFile">
                        <g:message code="content.file" default="File" />
                        <span class="required-indicator">*</span>
                    </label>
                        <input type="file" name="uploadedFile" id="uploadedFile "/>

                    <input type="submit">
                </g:form>
            </div>
        </div>
    </section>
</div>
</body>
</html>