package school

import grails.plugins.springsecurity.Secured

@Secured('ROLE_ADMIN')
class CourseController {

    static scaffold = Course
}
