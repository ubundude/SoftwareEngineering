<%@ page import="elearn.UserHomeController; elearn.Term" %>
<%--
  Created by IntelliJ IDEA.
  User: kolby
  Date: 2/21/13
  Time: 12:24 AM
  To change this template use File | Settings | File Templates.
--%>

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
            <h5>User</h5>
            <p>Hello ${user.name}</p>


        </div>
    </aside>
    <section id="main" class="span9">
        <div class="hero-unit">
            <g:each in="news" status="i" var="ne" >
                %{--<h2>${ne.title}</h2>--}%
                %{--<p>${ne.body}</p>--}%
            </g:each>
            <sec:ifAllGranted roles="ROLE_ADMIN">
                <g:link controller="news" >Edit News</g:link>
            </sec:ifAllGranted>
        </div>

        <div class="span4">
            <h3>Classes</h3>
                <g:form id="changeTerm" method="POST" action="index" name="changeTerm">
                    <g:select id="termId" name="term" from="${Term.list()}" optionKey="id" optionValue="name" noSelection="['':'Please select something']" />
                </g:form>
            <div class="nav nav-list">
            <table>

                <g:each status="i" in="${sections}" var="se" >
                    <tr class="${(i % 2) == 0 ? 'a' : 'b'}">
                        <td><g:link controller="sectionHome" action="index" params="[sectionId: se.section_id]" >${se.code} ${se.name}</g:link></td>
                    </tr>
                </g:each>

            </table>
        </div>

        </div>

        <sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_TEACHER">
            <div class="span3">
                <h3>Managers</h3>
                <ul class="nav nav-list">
                    <sec:ifAnyGranted roles="ROLE_ADMIN">
                        <li><a href="${createLink(controller:'user')}">User Manager</a></li>
                        <li><a href="${createLink(controller:'course')}">Course Manager</a></li>
                    </sec:ifAnyGranted>
                </ul>
            </div>
        </sec:ifAnyGranted>

    </section>

</div>

<script type="text/javascript">
    $(function() {
        $ ('#termId').change(function() {
            $(this).parents('form').submit();
        });
    });
</script>

</body>
</html>