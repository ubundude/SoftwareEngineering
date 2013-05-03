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
        <g:render template="../menu"/>
        <div class="row-fluid">
            <div span="4">
                <fieldset>
                    <g:form class="form-horizontal" action="addHandler" >

                            <input type="hidden" name="section" value="${section}" />
                            <label for="name">
                                <g:message code="name.label" default="Name" />

                            </label>
                            <g:textField name="name" />
                            <label for="maxPoints">
                                <g:message code="assignment.maxPoints" default="Maximum Points" />

                            </label>
                            <g:textField name="maxPoints" id="maxPoints" />
                        <label for="category">
                        <g:message code="categories.label" default="Categories" />

                        </label>
                        <g:select id="assignmentCategories" name="assignmentCategories" from="${elearn.AssignmentCategories.list()}" optionKey="id" required="" class="many-to-one"/>

                            <div class="form-actions">
                                <button type="submit" class="btn btn-primary">
                                    <i class="icon-ok icon-white"></i>
                                    <g:message code="default.button.create.label" default="Create"/>
                                </button>
                            </div>
                        </fieldset>
                    </g:form>

            </div>
        </div>
    </section>
</div>
</body>
</html>