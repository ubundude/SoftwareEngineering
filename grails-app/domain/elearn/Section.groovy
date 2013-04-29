package elearn

class Section {
    Integer sectionNumber
    String schedule
    String building
    String room

    static belongsTo = [course: Course, term: Term, teacher: User, assistant: User]

    static hasMany = [students: User]

    static constraints = {
        assistant nullable: true
        term nullable: true
    }

    String toString() {
        return course.name
    }

}
