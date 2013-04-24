package school

class SectionHomeController {

    def springSecurityService

    def index() {
        User user = springSecurityService.currentUser

        [user: user]
    }
}
