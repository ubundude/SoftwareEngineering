<%@ page import="school.UserHomeController; school.Term" %>
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
            <h1>Welcome to eLearn+</h1>

            <p>Captain, why are we out here chasing comets? A surprise party? Mr. Worf, I hate surprise parties.
            I would *never* do that to you. Mr. Crusher, ready a collision course with the Borg ship. Some days
            you get the bear, and some days the bear gets you. Our neural pathways have become accustomed to
            your sensory input patterns.</p>
            <sec:ifAllGranted roles="ROLE_ADMIN">
                <g:link controller="news" >Edit News</g:link>
            </sec:ifAllGranted>
        </div>

        <div class="span4">
            <h3>Classes</h3>
                <g:form id="changeTerm" method="POST" action="changeTerm" name="changeTerm">
                    <g:select id="termId" name="term" from="${Term.list()}" noSelection="['':'Choose a term']" optionKey="id" optionValue="name" />
                </g:form>

            <table>
                <ul class="nav nav-list">
                <g:each status="i" in="${sections}" var="se" >
                    <tr class="${(i % 2) == 0 ? 'a' : 'b'}">
                        <li><a href="${createLink(controller: 'sectionHome', params: se.id)}" ><td>${se.code} ${se.name}</td></a></li>
                    </tr>
                </g:each>
                </ul>
            </table>


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