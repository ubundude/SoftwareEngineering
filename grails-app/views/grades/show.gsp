
<%@ page import="school.Grades" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="bootstrap">
    <g:set var="entityName" value="${message(code: 'grades.label', default: 'Grades')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<div class="row-fluid">

    <div class="span3">
        <div class="well">
            <ul class="nav nav-list">
                <li class="nav-header">${entityName}</li>
                <li>
                    <g:link class="list" action="list">
                        <i class="icon-list"></i>
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
            <h1><g:message code="default.show.label" args="[entityName]"/></h1>
        </div>

        <g:if test="${flash.message}">
            <bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
        </g:if>

        <dl>
            
            <g:if test="${gradesInstance?.assignment}">
                <dt><g:message code="grades.assignment.label" default="Assignment"/></dt>
                
                <dd><g:link controller="assignment" action="show"
                            id="${gradesInstance?.assignment?.id}">${gradesInstance?.assignment?.encodeAsHTML()}</g:link></dd>
                
            </g:if>
            
            <g:if test="${gradesInstance?.grade}">
                <dt><g:message code="grades.grade.label" default="Grade"/></dt>
                
                <dd><g:fieldValue bean="${gradesInstance}" field="grade"/></dd>
                
            </g:if>
            
            <g:if test="${gradesInstance?.students}">
                <dt><g:message code="grades.students.label" default="Students"/></dt>
                
                <dd><g:link controller="user" action="show"
                            id="${gradesInstance?.students?.id}">${gradesInstance?.students?.encodeAsHTML()}</g:link></dd>
                
            </g:if>
            
        </dl>

        <g:form>
            <g:hiddenField name="id" value="${gradesInstance?.id}"/>
            <div class="form-actions">
                <g:link class="btn" action="edit" id="${gradesInstance?.id}">
                    <i class="icon-pencil"></i>
                    <g:message code="default.button.edit.label" default="Edit"/>
                </g:link>
                <button class="btn btn-danger" type="submit" name="_action_delete">
                    <i class="icon-trash icon-white"></i>
                    <g:message code="default.button.delete.label" default="Delete"/>
                </button>
            </div>
        </g:form>

    </div>

</div>
</body>
</html>
