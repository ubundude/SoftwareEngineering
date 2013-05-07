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
            <div span="4">
                <table>
                    <tr>
                        <th>Category</th>
                        <th>Action</th>
                    </tr>
                    <g:each status="i" in="${cats}" var="c">
                        <tr class="${(i % 2) == 0 ? 'a' : 'b'}">
                            <td>${c.name}</td>
                        </tr>
                    </g:each>
                </table>
                <g:form action="addCategory" params="${params}">
                    %{--<input type=--}%
                        <label for="name">
                            <g:message code="category.new" default="New Category" />
                        </label>
                    <input type="text" name="name" id="name" />
                    <input type="submit" value="Submit" />
                </g:form>
            </div>
        </div>
    </section>
    </div>
</body>
</html>