package school

class SectionHomeController {

    def springSecurityService

    def index() {
        def section = params.section
        log.debug(section)
        User user = springSecurityService.currentUser

        [user: user]
    }
}
