import school.Role
import school.User
import school.UserRole

class BootStrap {

    def init = { servletContext ->
        Role roleAdmin = Role.findByAuthority('ROLE_ADMIN')
        if (!roleAdmin) {
            roleAdmin = new Role(authority: 'ROLE_ADMIN').save()
        }

        Role roleUser = Role.findByAuthority('ROLE_USER')
        if (!roleUser) {
            new Role(authority: 'ROLE_USER').save()
        }

        List<User> users = User.executeQuery("select u from User u, UserRole ur where u.id = ur.user.id and ur.role.authority = 'ROLE_ADMIN'")
        if (!users) {
            User admin = new User(username: 'admin', password: 'admin', name: 'Admin User').save()
            UserRole.create(admin, roleAdmin)
        }
    }

    def destroy = {
    }
}
