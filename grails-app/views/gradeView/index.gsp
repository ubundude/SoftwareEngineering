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
            <h3>Grades</h3>
        </div>
        <div class="row-fluid">

            <div class="span4">
                    <table>
                        <tr>
                            <th>Assignment Name</th>
                            <th>Grade</th>
                        </tr>

                        <g:each status="i" in="${grades}" var="gr">
                            <tr class="${(i % 2) == 0 ? 'a' : 'b'}">
                                <td>${gr.name}</td>
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