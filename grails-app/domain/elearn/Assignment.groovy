package elearn

/**
 * Class defining the domain object for an Assignment. Assignments are assigned by a Teacher of
 * a Section and a Student can be given a grade for it.
 *
 * @author Kolby Cansler
 * @author Simeon Burns
 */
class Assignment {
    String name
    Date dateDue
    int maxPoints

    static belongsTo = [assignmentCategories: AssignmentCategories]

    static constraints = {
        dateDue nullable: true
    }

    String toString() {
        return name
    }
}
