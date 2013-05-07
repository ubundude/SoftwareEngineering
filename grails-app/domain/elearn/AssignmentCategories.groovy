package elearn

class AssignmentCategories {
    String name

    static belongsTo = [section: Section]

    static constraints = {
    }

    String toString() {
       return name
    }
}
