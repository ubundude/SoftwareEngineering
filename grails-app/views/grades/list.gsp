
<%@ page import="elearn.Grades" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="bootstrap">
    <g:set var="entityName" value="${message(code: 'grades.label', default: 'Grades')}"/>
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
                
                <th class="header"><g:message code="grades.assignment.label"
                                              default="Assignment"/></th>
                
                <g:sortableColumn property="grade"
                                  title="${message(code: 'grades.grade.label', default: 'Grade')}"/>
                
                <th class="header"><g:message code="grades.students.label"
                                              default="Students"/></th>
                
                <th></th>
            </tr>
            </thead>
            <tbody>
            <g:each in="${gradesInstanceList}" var="gradesInstance">
                <tr>
                    
                    <td>${fieldValue(bean: gradesInstance, field: "assignment")}</td>
                    
                    <td>${fieldValue(bean: gradesInstance, field: "grade")}</td>
                    
                    <td>${fieldValue(bean: gradesInstance, field: "students")}</td>
                    
                    <td class="link">
                        <g:link action="show" id="${gradesInstance.id}" class="btn btn-small">Show &raquo;</g:link>
                    </td>
                </tr>
            </g:each>
            </tbody>
        </table>

        <div class="pagination">
            <bootstrap:paginate total="${gradesInstanceTotal}"/>
        </div>
    </div>

</div>
</body>
</html>
