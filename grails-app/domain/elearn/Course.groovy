package elearn

/**
 * Class defining the domain object for a Course. A course is the general course description
 * including name and amount of credits.
 *
 * @author Kolby Cansler
 * @author Simeon Burns
 */
class Course {
    String code
    String name
    Integer credits
    String description

    static constraints = {
    }

    String toString() {
        return name
    }
}
