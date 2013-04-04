
<%@ page import="school.Section" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="bootstrap">
    <g:set var="entityName" value="${message(code: 'section.label', default: 'Section')}"/>
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

            <g:if test="${sectionInstance?.building}">
                <dt><g:message code="section.building.label" default="Building"/></dt>
                
                <dd><g:fieldValue bean="${sectionInstance}" field="building"/></dd>
                
            </g:if>
            
            <g:if test="${sectionInstance?.course}">
                <dt><g:message code="section.course.label" default="Course"/></dt>
                
                <dd><g:link controller="course" action="show"
                            id="${sectionInstance?.course?.id}">${sectionInstance?.course?.encodeAsHTML()}</g:link></dd>
                
            </g:if>

            <g:if test="${sectionInstance?.sectionNumber}">
                <dt><g:message code="section.sectionNumber.label" default="Section Number"/></dt>

                <dd><g:fieldValue bean="${sectionInstance}" field="sectionNumber"/></dd>

            </g:if>

            <g:if test="${sectionInstance?.teacher}">
                <dt><g:message code="section.teacher.label" default="Teacher"/></dt>

                <dd><g:link controller="user" action="show"
                            id="${sectionInstance?.teacher?.id}">${sectionInstance?.teacher?.encodeAsHTML()}</g:link></dd>

            </g:if>

            <g:if test="${sectionInstance?.assistant}">
                <dt><g:message code="section.assistant.label" default="Assistant"/></dt>

                <dd><g:link controller="user" action="show"
                            id="${sectionInstance?.assistant?.id}">${sectionInstance?.assistant?.encodeAsHTML()}</g:link></dd>

            </g:if>
            
            <g:if test="${sectionInstance?.room}">
                <dt><g:message code="section.room.label" default="Room"/></dt>
                
                <dd><g:fieldValue bean="${sectionInstance}" field="room"/></dd>
                
            </g:if>
            
            <g:if test="${sectionInstance?.schedule}">
                <dt><g:message code="section.schedule.label" default="Schedule"/></dt>
                
                <dd><g:fieldValue bean="${sectionInstance}" field="schedule"/></dd>
                
            </g:if>
            
            <g:if test="${sectionInstance?.students}">
                <dt><g:message code="section.students.label" default="Students"/></dt>
                
                <g:each in="${sectionInstance.students}" var="s">
                    <dd><g:link controller="user" action="show"
                                id="${s.id}">${s?.encodeAsHTML()}</g:link></dd>
                </g:each>
                
            </g:if>

        </dl>

        <g:form>
            <g:hiddenField name="id" value="${sectionInstance?.id}"/>
            <div class="form-actions">
                <g:link class="btn" action="edit" id="${sectionInstance?.id}">
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
