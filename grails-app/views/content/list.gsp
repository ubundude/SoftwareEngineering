
<%@ page import="elearn.Content" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="bootstrap">
    <g:set var="entityName" value="${message(code: 'content.label', default: 'Content')}"/>
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
                
                <g:sortableColumn property="summary"
                                  title="${message(code: 'content.summary.label', default: 'Summary')}"/>
                
                <g:sortableColumn property="contentURI"
                                  title="${message(code: 'content.contentURI.label', default: 'Content URI')}"/>
                
                <th class="header"><g:message code="content.section.label"
                                              default="Section"/></th>
                
                <g:sortableColumn property="title"
                                  title="${message(code: 'content.title.label', default: 'Title')}"/>
                
                <th></th>
            </tr>
            </thead>
            <tbody>
            <g:each in="${contentInstanceList}" var="contentInstance">
                <tr>
                    
                    <td>${fieldValue(bean: contentInstance, field: "summary")}</td>
                    
                    <td>${fieldValue(bean: contentInstance, field: "contentURI")}</td>
                    
                    <td>${fieldValue(bean: contentInstance, field: "section")}</td>
                    
                    <td>${fieldValue(bean: contentInstance, field: "title")}</td>
                    
                    <td class="link">
                        <g:link action="show" id="${contentInstance.id}" class="btn btn-small">Show &raquo;</g:link>
                    </td>
                </tr>
            </g:each>
            </tbody>
        </table>

        <div class="pagination">
            <bootstrap:paginate total="${contentInstanceTotal}"/>
        </div>
    </div>

</div>
</body>
</html>
