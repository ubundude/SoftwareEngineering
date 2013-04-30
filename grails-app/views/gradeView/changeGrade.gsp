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
                <g:form action="submitGrades">
                    <table>
                       <tr>
                           <th>Student Name</th>
                           <th>Grade</th>
                       </tr>
                       <g:each var="st" in="${students}" status="i">
                           <input type="hidden" name="uId" id="uId" value="${st.user}" />
                           <input type="hidden" name="gId" id="gId" value="${st.gId}" />
                           <tr class="${(i % 2) == 0 ? 'a' : 'b'}">
                               <td>${st.name}</td>
                               <td><input type="text" name="grade" id="grade" value=${st.grade} ></td>
                           </tr>
                       </g:each>
                    </table>
                    <div class="form-actions">
                                <button type="submit" class="btn btn-primary">
                                <i class="icon-ok icon-white"></i>
                    <g:message code="default.button.create.label" default="Create"/>
                    </button>
                </g:form>
            </div>
                </div>
        </div>
    </section>
</div>
</body>
</html>