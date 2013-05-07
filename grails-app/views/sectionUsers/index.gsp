<!doctype html>
<html>
<head>
    <meta name="layout" content="bootstrap"/>
    <title>Class List</title>
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
                <table>
                    <tr>
                        <td><b>Teacher:</b></td>
                    </tr>
                    <tr>
                        <td>${teacher}</td>
                    </tr>
                    <tr>
                        <td><b>Teaching Assistant</b></td>
                    </tr>
                    <tr>
                        <td>${ta}</td>
                    </tr>
                    <tr>
                        <td><b>Students</b></td>
                    </tr>
                    <g:each status="i" in="${students}" var="st" >
                        <tr class="${(i % 2) == 0 ? 'a' : 'b'}">
                            <td>${st.name}</td>
                        </tr>
                    </g:each>
                </table>
            </div>
        </div>
    </section>
    </div>
</body>
</html>