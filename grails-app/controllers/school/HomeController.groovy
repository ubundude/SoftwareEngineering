package school

import grails.plugins.springsecurity.Secured

@Secured("ROLE_ADMIN")
class HomeController {

    def springSecurityService

    def index() {
        def user = springSecurityService.principal
    }
}
