package elearn

/**
 * Class defining the domain object for the Roles a User can have
 *
 * @author Kolby Cansler
 * @author Simeon Burns
 */
class Role {

    String authority

    static mapping = {
        cache true
    }

    static constraints = {
        authority blank: false, unique: true
    }
}
