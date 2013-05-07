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
        <g:render template="../menu" />
        <div class="row-fluid">

            <div class="span4">
                    <table>
                        <tr>
                            <th>Assignment Name</th>
                            <th>Grade</th>
                        </tr>
                        <sec:ifAnyGranted roles="ROLE_STUDENT" >
                            <g:each status="i" in="${assignments}" var="gr">
                                <tr class="${(i % 2) == 0 ? 'a' : 'b'}">
                                    <td>${gr.name}</td>
                                    <td>${gr.grade}</td>
                                </tr>
                            </g:each>
                        </sec:ifAnyGranted>
                        <sec:ifAnyGranted roles="ROLE_TEACHER" >
                            <g:each status="i" in="${assignments}" var="gr">
                                <tr class="${(i % 2) == 0 ? 'a' : 'b'}">
                                    <td>${gr.name}</td>
                                    <td>
                                        <g:link action="changeGrade" params="[assignment: gr.id, section: section]" >
                                        Edit
                                        </g:link>
                                    </td>
                                </tr>
                            </g:each>
                        </sec:ifAnyGranted>
                    </table>
                    <sec:ifAnyGranted roles="ROLE_TEACHER,ROLE_TA" >
                        <g:link action="addAssignment" params="[section: section]">Add an assignment</g:link><br />
                        <g:link action="manageCategories" params="[section: section]">Manage Categories</g:link>
                    </sec:ifAnyGranted>
                </div>

            </div>
    </section>
</div>

</body>
</html>