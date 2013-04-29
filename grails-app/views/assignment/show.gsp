<%@ page import="elearn.Assignment" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="bootstrap">
    <g:set var="entityName" value="${message(code: 'assignment.label', default: 'Assignment')}"/>
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
            
            <g:if test="${assignmentInstance?.dateDue}">
                <dt><g:message code="assignment.dateDue.label" default="Date Due"/></dt>
                
                <dd><g:formatDate date="${assignmentInstance?.dateDue}"/></dd>
                
            </g:if>
            
            <g:if test="${assignmentInstance?.name}">
                <dt><g:message code="assignment.name.label" default="Name"/></dt>
                
                <dd><g:fieldValue bean="${assignmentInstance}" field="name"/></dd>
                
            </g:if>
            
            <g:if test="${assignmentInstance?.section}">
                <dt><g:message code="assignment.section.label" default="Section"/></dt>
                
                <dd><g:link controller="section" action="show"
                            id="${assignmentInstance?.section?.id}">${assignmentInstance?.section?.encodeAsHTML()}</g:link></dd>
                
            </g:if>
            
        </dl>

        <g:form>
            <g:hiddenField name="id" value="${assignmentInstance?.id}"/>
            <div class="form-actions">
                <g:link class="btn" action="edit" id="${assignmentInstance?.id}">
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
