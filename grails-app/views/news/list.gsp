
<%@ page import="elearn.News" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="bootstrap">
    <g:set var="entityName" value="${message(code: 'news.label', default: 'News')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<div class="row-fluid">

    <div class="span3">
        <div class="well">
            <ul class="nav nav-list">
                <li class="nav-header">${entityName}</li>
                <li class="active">
                    <g:link class="list" action="list">
                        <i class="icon-list icon-white"></i>
                        <g:message code="default.list.label" args="[entityName]"/>
                    </g:link>
                </li>
                <li>
                    <g:link class="create" action="create">
                        <i class="icon-plus"></i>
                        <g:message code="default.create.label" args="[entityName]"/>
                    </g:link>
                </li>
            </ul>
        </div>
    </div>

    <div class="span9">

        <div class="page-header">
            <h1><g:message code="default.list.label" args="[entityName]"/></h1>
        </div>

        <g:if test="${flash.message}">
            <bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
        </g:if>

        <table class="table table-striped">
            <thead>
            <tr>

                <g:sortableColumn property="title"
                                  title="${message(code: 'news.title.label', default: 'Title')}"/>
                
                <g:sortableColumn property="newsBody"
                                  title="${message(code: 'news.newsBody.label', default: 'News Body')}"/>

                <g:sortableColumn property="expires"
                                  title="${message(code: 'news.expires.label', default: 'Expires')}"/>
                
                <th></th>
            </tr>
            </thead>
            <tbody>
            <g:each in="${newsInstanceList}" var="newsInstance">
                <tr>
                    
                    <td><g:formatDate date="${newsInstance.expires}"/></td>
                    
                    <td>${fieldValue(bean: newsInstance, field: "newsBody")}</td>
                    
                    <td>${fieldValue(bean: newsInstance, field: "title")}</td>
                    
                    <td class="link">
                        <g:link action="show" id="${newsInstance.id}" class="btn btn-small">Show &raquo;</g:link>
                    </td>
                </tr>
            </g:each>
            </tbody>
        </table>

        <div class="pagination">
            <bootstrap:paginate total="${newsInstanceTotal}"/>
        </div>
    </div>

</div>
</body>
</html>
