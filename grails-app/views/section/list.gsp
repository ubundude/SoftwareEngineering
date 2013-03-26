
<%@ page import="school.Section" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="bootstrap">
    <g:set var="entityName" value="${message(code: 'section.label', default: 'Section')}"/>
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

                <th class="header"><g:message code="section.course.label"
                                              default="Course"/></th>
                
                <th class="header"><g:message code="section.assistant.label"
                                              default="Assistant"/></th>
                
                <g:sortableColumn property="building"
                                  title="${message(code: 'section.building.label', default: 'Building')}"/>
                
                <g:sortableColumn property="room"
                                  title="${message(code: 'section.room.label', default: 'Room')}"/>
                
                <g:sortableColumn property="schedule"
                                  title="${message(code: 'section.schedule.label', default: 'Schedule')}"/>
                
                <g:sortableColumn property="sectionNumber"
                                  title="${message(code: 'section.sectionNumber.label', default: 'Section Number')}"/>
                
                <th></th>
            </tr>
            </thead>
            <tbody>
            <g:each in="${sectionInstanceList}" var="sectionInstance">
                <tr>

                    <td>${fieldValue(bean: sectionInstance, field: "course")}</td>
                    
                    <td>${fieldValue(bean: sectionInstance, field: "assistant")}</td>
                    
                    <td>${fieldValue(bean: sectionInstance, field: "building")}</td>
                    
                    <td>${fieldValue(bean: sectionInstance, field: "room")}</td>
                    
                    <td>${fieldValue(bean: sectionInstance, field: "schedule")}</td>
                    
                    <td>${fieldValue(bean: sectionInstance, field: "sectionNumber")}</td>
                    
                    <td class="link">
                        <g:link action="show" id="${sectionInstance.id}" class="btn btn-small">Show &raquo;</g:link>
                    </td>
                </tr>
            </g:each>
            </tbody>
        </table>

        <div class="pagination">
            <bootstrap:paginate total="${sectionInstanceTotal}"/>
        </div>
    </div>

</div>
</body>
</html>
