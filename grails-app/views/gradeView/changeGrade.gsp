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
            <div span="4">
                <g:form action="submitGrades">
                    <table>
                       <tr>
                           <th>Student Name</th>
                           <th>Grade</th>
                       </tr>
                       <g:each var="st" in="${students}" status="i">
                           <tr class="${(i % 2) == 0 ? 'a' : 'b'}">
                               <td><input type="hidden" name="uId" id="uId" value="${st.id}" /></td>
                               <td>${st.name}</td>
                               <td><input type="text" name="grade" id="grade" ></td>
                           </tr>
                       </g:each>
                   </table>
                </g:form>
            </div>
        </div>
    </section>
</div>
</body>
</html>