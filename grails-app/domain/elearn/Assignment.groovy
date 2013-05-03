package elearn

class Assignment {
    String name
    Date dateDue
    int maxPoints

    static belongsTo = [section: Section, assignmentCategories: AssignmentCategories]

    static constraints = {
        dateDue nullable: true
    }

    String toString() {
        return name
    }
}
