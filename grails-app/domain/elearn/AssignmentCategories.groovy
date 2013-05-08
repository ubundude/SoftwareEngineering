package elearn

/**
 * Class defining the domain object for assignment categories
 * Each category belongs to a Section
 *
 * @author Kolby Cansler
 * @author Simeon Burns
 */
class AssignmentCategories {
    String name

    static belongsTo = [section: Section]

    static constraints = {
    }

    String toString() {
       return name
    }
}
