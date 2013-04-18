<!doctype html>
<html>
<head>
    <meta name="layout" content="bootstrap"/>
    <title>Homepage</title>
</head>

<body>
    <fieldset>
    <g:form class="form-horizontal" action="edit">
        <fieldset>
            <g:render template="form" />
            <div class="form-actions">
                <button type="submit" class="btn btn-primary">
                    <i class="icon-ok icon-white"></i>
                    <g:message code="default.button.create.label" default="Create"/>
                </button>
            </div>
        </fieldset>
    </g:form>
    </fieldset>

</body>
</html>