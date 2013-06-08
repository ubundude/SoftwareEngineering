package elearn

import grails.plugins.springsecurity.Secured

/**
 * Class to control the views for the Section Home. Used to display news for the section
 *
 * @author Kolby Cansler
 * @author Simeon Burns
 */
@Secured(['ROLE_TEACHER','ROLE_STUDENT','ROLE_TA'])
class SectionHomeController {

    def springSecurityService

    def index() {
        int section = params.getInt('section')
        User user = springSecurityService.currentUser
        println "Request URI: " + request.getRequestURI()

        [user: user, section: section]
    }
}
