package elearn

class SectionHomeController {

    def springSecurityService

    def index() {
        int sectionId = params.getInt('sectionId')
        log.debug("section home: " + sectionId)
        User user = springSecurityService.currentUser

        [user: user, sectionId: sectionId]
    }
}
