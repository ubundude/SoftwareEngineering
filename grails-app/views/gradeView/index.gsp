<!doctype html>
<html>
<head>
    <meta name="layout" content="bootstrap"/>
    <title>Grades</title>
</head>
<body>
<table>
    <tr>
        <th>Assignment Name</th>
        <th>Grade</th>
    </tr>

    <g:each in="${grades}">
            <tr>
                <td>${it.name}</td>
                <td>${it.grade}</td>
            </tr>
        </table>

    </g:each>

</body>
</html>