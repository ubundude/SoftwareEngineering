package school

import grails.plugins.springsecurity.Secured

@Secured('ROLE_ADMIN')
class Section {
    Integer sectionNumber

    static belongsTo = [course: Course, teacher: User]

    static hasMany = [students: User]

    static constraints = {
    }
}
