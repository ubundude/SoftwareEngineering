<%@ page import="school.Course" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="bootstrap">
    <g:set var="entityName" value="${message(code: 'course.label', default: 'Course')}"/>
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

            <g:if test="${courseInstance?.code}">
                <dt><g:message code="course.code.label" default="Code"/></dt>

                <dd><g:fieldValue bean="${courseInstance}" field="code"/></dd>

            </g:if>

            <g:if test="${courseInstance?.credits}">
                <dt><g:message code="course.credits.label" default="Credits"/></dt>

                <dd><g:fieldValue bean="${courseInstance}" field="credits"/></dd>

            </g:if>

            <g:if test="${courseInstance?.description}">
                <dt><g:message code="course.description.label" default="Description"/></dt>

                <dd><g:fieldValue bean="${courseInstance}" field="description"/></dd>

            </g:if>

            <g:if test="${courseInstance?.name}">
                <dt><g:message code="course.name.label" default="Name"/></dt>

                <dd><g:fieldValue bean="${courseInstance}" field="name"/></dd>

            </g:if>

            <g:if test="${courseInstance?.section}">
                <dt><g:message code="course.section.label" default="Section"/></dt>

                <g:each in="${courseInstance.section}" var="s">
                    <dd><g:link controller="section" action="show" id="${s.id}">${s?.encodeAsHTML()}</g:link></dd>
                </g:each>

            </g:if>

        </dl>

        <g:form>
            <g:hiddenField name="id" value="${courseInstance?.id}"/>
            <div class="form-actions">
                <g:link class="btn" action="edit" id="${courseInstance?.id}">
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
