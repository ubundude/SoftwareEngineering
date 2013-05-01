
<%@ page import="elearn.Content" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="bootstrap">
    <g:set var="entityName" value="${message(code: 'content.label', default: 'Content')}"/>
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
            
            <g:if test="${contentInstance?.summary}">
                <dt><g:message code="content.summary.label" default="Summary"/></dt>
                
                <dd><g:fieldValue bean="${contentInstance}" field="summary"/></dd>
                
            </g:if>
            
            <g:if test="${contentInstance?.contentURI}">
                <dt><g:message code="content.contentURI.label" default="Content URI"/></dt>
                
                <dd><g:fieldValue bean="${contentInstance}" field="contentURI"/></dd>
                
            </g:if>
            
            <g:if test="${contentInstance?.section}">
                <dt><g:message code="content.section.label" default="Section"/></dt>
                
                <dd><g:link controller="section" action="show"
                            id="${contentInstance?.section?.id}">${contentInstance?.section?.encodeAsHTML()}</g:link></dd>
                
            </g:if>
            
            <g:if test="${contentInstance?.title}">
                <dt><g:message code="content.title.label" default="Title"/></dt>
                
                <dd><g:fieldValue bean="${contentInstance}" field="title"/></dd>
                
            </g:if>
            
        </dl>

        <g:form>
            <g:hiddenField name="id" value="${contentInstance?.id}"/>
            <div class="form-actions">
                <g:link class="btn" action="edit" id="${contentInstance?.id}">
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
