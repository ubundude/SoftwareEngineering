package elearn

class Assignment {
    String name
    Date dateDue
    int maxPoints

    static belongsTo = [section: Section, assignmentCategories: AssignmentCategories]

    static constraints = {
    }

    String toString() {
        return name
    }
}
