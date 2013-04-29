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
                <g:form action="save" method="post" >
                    <g:message code="name.label" default="Name" />
                        <input type="text" name="title" id="title" />
                    <g:message code="content.summary" default="Summary" />
                        <input type="text" name="summary" id="summary" />
                    <g:message code="content.file" default="File" />
                        <input type="file" name="file" />

                    <input type="submit">
                </g:form>
            </div>
        </div>
    </section>
</div>
</body>
</html>