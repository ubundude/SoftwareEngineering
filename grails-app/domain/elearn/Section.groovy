package elearn

/**
 * Class defining the domain object for a Section. A section is the acual class that a User
 * can be a part of. It is a child of Course, and has a User defined as a teacher, one as a
 * teaching assistant, and students.
 *
 * @author Kolby Cansler
 * @author Simeon Burns
 */
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
