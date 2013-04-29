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
        <ul class="nav nav-tabs">
            <li><g:link controller="sectionHome" params="[section: section]" >Class Home</g:link></li>
            <li class="active"><g:link controller="gradeView" action="index" params="[section: section]" >Grades</g:link></li>
        </ul>
        <div class="row-fluid">

            <div class="span4">
                    <table>
                        <tr>
                            <th>Assignment Name</th>
                            <th>Grade</th>
                        </tr>

                        <g:each status="i" in="${grades}" var="gr">
                            <tr class="${(i % 2) == 0 ? 'a' : 'b'}">
                                <td><g:link action="changeGrade" params="[assignment: gr.id, section: section]" >${gr.name}</g:link></td>
                                <td>${gr.grade}</td>
                            </tr>

                        </g:each>
                    </table>
                </div>

            </div>
    </section>
</div>

</body>
</html>