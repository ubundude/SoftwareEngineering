<%@ page import="org.codehaus.groovy.grails.web.servlet.GrailsApplicationAttributes" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title><g:layoutTitle default="${meta(name: 'app.name')}"/></title>
    <meta name="description" content="">
    <meta name="author" content="">

    <meta name="viewport" content="initial-scale = 1.0">

    <!-- Le HTML5 shim, for IE6-8 support of HTML elements -->
    <!--[if lt IE 9]>
			<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
		<![endif]-->

    <r:require modules="scaffolding"/>

    <!-- Le fav and touch icons -->
    <link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}" type="image/x-icon">
    <link rel="apple-touch-icon" href="${resource(dir: 'images', file: 'apple-touch-icon.png')}">
    %{--<link rel="apple-touch-icon" sizes="72x72" href="${resource(dir: 'images', file: 'apple-touch-icon-72x72.png')}">--}%
    %{--<link rel="apple-touch-icon" sizes="114x114"--}%
          %{--href="${resource(dir: 'images', file: 'apple-touch-icon-114x114.png')}">--}%

    <g:layoutHead/>
    <r:layoutResources/>
</head>

<body>

<nav class="navbar navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container-fluid">

            <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </a>

            <a class="brand" href="${createLink(uri: '/userHome')}">eLearn+</a>

            <div class="nav-collapse">
                <ul class="nav">
                    <sec:ifAllGranted roles="ROLE_ADMIN">
                        <li <g:if test="${request.getRequestURI().startsWith("/user")}">class="active"</g:if>><a href="${createLink(controller:'user')}">Users</a></li>
                        <li <g:if test="${request.getRequestURI().startsWith("/course")}">class="active"</g:if>><a href="${createLink(controller:'course')}">Course Manager</a></li>
                    </sec:ifAllGranted>
                    <sec:ifAnyGranted roles="ROLE_TEACHER, ROLE_ADMIN">
                        <li <g:if test="${request.getRequestURI().startsWith("/section")}">class="active"</g:if>><a href="${createLink(controller:'section')}">Section Manager</a></li>
                    </sec:ifAnyGranted>
                    <sec:ifAnyGranted roles="ROLE_TEACHER, ROLE_TA">
                        <li <g:if test="${request.getRequestURI().startsWith("/grades")}">class="active"</g:if>><a href="${createLink(controller:'gradeView')}">Grade Manager</a></li>
                    </sec:ifAnyGranted>

                    <sec:ifNotLoggedIn>
                       <li><g:link controller="login" action="auth">Login</g:link></li>
                    </sec:ifNotLoggedIn>
                    <sec:ifLoggedIn>
                        <li><g:link controller="logout">Logout</g:link></li>
                    </sec:ifLoggedIn>


                <!--    <li<%=request.forwardURI == "${createLink(uri: '/')}" ? ' class="active"' : ''%>><a
                            href="${createLink(uri: '/')}">Home</a></li>
                    <g:each var="c" in="${grailsApplication.controllerClasses.sort { it.fullName }}">
                        <li<%=c.logicalPropertyName == controllerName ? ' class="active"' : ''%>><g:link
                                controller="${c.logicalPropertyName}">${c.naturalName}</g:link></li>
                    </g:each>   -->
                </ul>
            </div>
        </div>
    </div>
</nav>

<div class="container-fluid">
    <g:layoutBody/>

    <hr>

    <footer>
        <p>&copy; SWAU 2013</p>
    </footer>
</div>

<r:layoutResources/>

</body>
</html>