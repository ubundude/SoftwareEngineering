<ul class="nav nav-tabs">
    <li<g:if test="${request.getRequestURI().startsWith("eLearn/sectionHome")}">class="active"</g:if>><g:link controller="sectionHome" params="[sectionId: sectionId]" >Class Home</g:link></li>
    <li<g:if test="${request.getRequestURI().startsWith("eLearn/sectionContent")}">class="active"</g:if>><g:link controller="sectionContent" action="index" params="[sectionId: sectionId]" >Content</g:link></li>
    <li<g:if test="${request.getRequestURI().startsWith("eLearn/gradeView")}">class="active"</g:if>><g:link controller="gradeView" action="index" params="[sectionId: sectionId]" >Grades</g:link></li>
</ul>