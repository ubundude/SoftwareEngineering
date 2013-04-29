package elearn

class SectionHomeController {

    def springSecurityService

    def index() {
        int section = params.getInt('section')
        log.debug("section home: " + section)
        User user = springSecurityService.currentUser

        [user: user, section: section]
    }
}
