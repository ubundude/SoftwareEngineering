package school

class Section {
    Integer sectionNumber

    static belongsTo = [course: Course, teacher: User]

    static hasMany = [students: User]

    static constraints = {
    }
}
