package school

import grails.plugins.springsecurity.Secured

@Secured('ROLE_ADMIN')
class Section {
    Integer sectionNumber
    String schedule
    String building
    String room

    static belongsTo = [course: Course, teacher: User, assistant: User]

    static hasMany = [students: User]

    static constraints = {
        assistant nullable: true
    }
}
