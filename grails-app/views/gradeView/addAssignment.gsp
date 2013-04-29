<!doctype html>
<html>
<head>
    <meta name="layout" content="bootstrap"/>
    <title>Grades</title>
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
        <div class="well-small">
            <h3><g:link action="index" >Grades</g:link></h3>
        </div>
        <div class="row-fluid">
            <div span="4">
                <fieldset>
                    <g:form class="form-horizontal" action="addHandler" >
                        <fieldset>
                            <g:render template="form"/>
                            <div class="form-actions">
                                <button type="submit" class="btn btn-primary">
                                    <i class="icon-ok icon-white"></i>
                                    <g:message code="default.button.create.label" default="Create"/>
                                </button>
                            </div>
                        </fieldset>
                    </g:form>
                </fieldset>
            </div>
        </div>
    </section>
</div>
</body>
</html>