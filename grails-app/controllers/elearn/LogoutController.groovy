package elearn

import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils

/**
 * Class to control Logout to the System. Created by the Spring Security Core plugin
 *
 * @author Kolby Cansler
 * @author Simeon Burns
 */
class LogoutController {

    /**
     * Index action. Redirects to the Spring security logout uri.
     */
    def index = {
        // TODO put any pre-logout code here
        redirect uri: SpringSecurityUtils.securityConfig.logout.filterProcessesUrl // '/j_spring_security_logout'
    }
}
