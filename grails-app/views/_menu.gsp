<ul class="nav nav-tabs">
    <li<g:if test="${request.getRequestURI().startsWith("eLearn/sectionHome")}">class="active"</g:if>><g:link controller="sectionHome" params="[section: section]" >Class Home</g:link></li>
    <li<g:if test="${request.getRequestURI().startsWith("eLearn/sectionContent")}">class="active"</g:if>><g:link controller="sectionContent" action="index" params="[section: section]" >Content</g:link></li>
    <li<g:if test="${request.getRequestURI().startsWith("eLearn/gradeView")}">class="active"</g:if>><g:link controller="gradeView" action="index" params="[section: section]" >Grades</g:link></li>
</ul>