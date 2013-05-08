package elearn

/**
 * Class defining the domain object for a Term. An example of a term is Spring 2013
 *
 * @author Kolby Cansler
 * @author Simeon Burns
 */
class Term {
    String name

    static constraints = {
    }

    String toString() {
        return name
    }
}
