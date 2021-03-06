package elearn

import grails.plugins.springsecurity.Secured

/**
 * Class defining the domain object for a User.
 *
 * @author Kolby Cansler
 * @author Simeon Burns
 */
@Secured('ROLE_ADMIN')
class User {
    transient springSecurityService

    String name
    String username
    String password
    boolean enabled = true
    boolean accountExpired = false
    boolean accountLocked = false
    boolean passwordExpired = false

    static constraints = {
        name blank: false
        username blank: false, unique: true
        password blank: false
    }

    static mapping = {
        table 'users'
        password column: '`password`'
    }

    Set<Role> getAuthorities() {
        UserRole.findAllByUser(this).collect { it.role } as Set
    }

    def beforeInsert() {
        encodePassword()
    }

    def beforeUpdate() {
        if (isDirty('password')) {
            encodePassword()
        }
    }

    protected void encodePassword() {
        password = springSecurityService.encodePassword(password)
    }

    String toString() {
        return name
    }
}
