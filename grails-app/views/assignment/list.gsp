<%@ page import="elearn.Assignment" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="bootstrap">
    <g:set var="entityName" value="${message(code: 'assignment.label', default: 'Assignment')}"/>
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
                
                <g:sortableColumn property="dateDue"
                                  title="${message(code: 'assignment.dateDue.label', default: 'Date Due')}"/>
                
                <g:sortableColumn property="name"
                                  title="${message(code: 'assignment.name.label', default: 'Name')}"/>
                
                <th class="header"><g:message code="assignment.section.label"
                                              default="Section"/></th>
                
                <th></th>
            </tr>
            </thead>
            <tbody>
            <g:each in="${assignmentInstanceList}" var="assignmentInstance">
                <tr>
                    
                    <td><g:formatDate date="${assignmentInstance.dateDue}"/></td>
                    
                    <td>${fieldValue(bean: assignmentInstance, field: "name")}</td>
                    
                    <td>${fieldValue(bean: assignmentInstance, field: "section")}</td>
                    
                    <td class="link">
                        <g:link action="show" id="${assignmentInstance.id}" class="btn btn-small">Show &raquo;</g:link>
                    </td>
                </tr>
            </g:each>
            </tbody>
        </table>

        <div class="pagination">
            <bootstrap:paginate total="${assignmentInstanceTotal}"/>
        </div>
    </div>

</div>
</body>
</html>
