<%@ page import="school.Section" %>



<div class="fieldcontain ${hasErrors(bean: sectionInstance, field: 'building', 'error')} ">
    <label for="building">
        <g:message code="section.building.label" default="Building"/>

    </label>
    <g:textField name="building" value="${sectionInstance?.building}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: sectionInstance, field: 'course', 'error')} required">
    <label for="course">
        <g:message code="section.course.label" default="Course"/>
        <span class="required-indicator">*</span>
    </label>
    <g:select id="course" name="course.id" from="${school.Course.list()}" optionKey="id" required=""
              value="${sectionInstance?.course?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: sectionInstance, field: 'room', 'error')} ">
    <label for="room">
        <g:message code="section.room.label" default="Room"/>

    </label>
    <g:textField name="room" value="${sectionInstance?.room}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: sectionInstance, field: 'schedule', 'error')} ">
    <label for="schedule">
        <g:message code="section.schedule.label" default="Schedule"/>

    </label>
    <g:textField name="schedule" value="${sectionInstance?.schedule}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: sectionInstance, field: 'sectionNumber', 'error')} required">
    <label for="sectionNumber">
        <g:message code="section.sectionNumber.label" default="Section Number"/>
        <span class="required-indicator">*</span>
    </label>
    <g:field name="sectionNumber" type="number" value="${sectionInstance.sectionNumber}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: sectionInstance, field: 'students', 'error')} ">
    <label for="students">
        <g:message code="section.students.label" default="Students"/>

    </label>
    <g:select name="students" from="${school.User.list()}" multiple="multiple" optionKey="id" size="5"
              value="${sectionInstance?.students*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: sectionInstance, field: 'teacher', 'error')} required">
    <label for="teacher">
        <g:message code="section.teacher.label" default="Teacher"/>
        <span class="required-indicator">*</span>
    </label>
    <g:select id="teacher" name="teacher.id" from="${school.User.list()}" optionKey="id" required=""
              value="${sectionInstance?.teacher?.id}" class="many-to-one"/>
</div>

