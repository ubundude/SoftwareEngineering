package elearn

/**
 * Class defining the domain object for Grades. Grades belong to a User and an Assignment.
 *
 * @author Kolby Cansler
 * @author Simeon Burns
 */
class Grades {
    String grade

    static belongsTo = [assignment: Assignment, students: User]

    static constraints = {
        grade nullable: true
    }
}
