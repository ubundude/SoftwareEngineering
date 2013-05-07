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
                <sec:ifAnyGranted roles="ROLE_TEACHER,ROLE_TA" >
                    <g:link action="addContent" params="[section: section]">Add content</g:link>
                </sec:ifAnyGranted>
                <table>
                    <th>Item Name</th>
                    <th>Description</th>
                    <th>Link</th>
                    <g:each status="i" in="${contents}" var="c">
                        <tr class="${(i % 2) == 0 ? 'a' : 'b'}">
                            <td>${c.title}</td>
                            <td>${c.summary}</td>
                            <td><g:link action="download" params="[link: c.link]" >Download</g:link></td>
                        </tr>
                    </g:each>
                </table>
            </div>
        </div>
    </section>
</div>
</body>
</html>