package school

class Assignment {
    String name
    Date dateDue

    static belongsTo = [section: Section]

    static hasMany = [grades: Grades]

    static constraints = {
    }

    String toString() {
        return Section
    }
}
