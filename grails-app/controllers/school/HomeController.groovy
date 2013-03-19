package school

import grails.plugins.springsecurity.Secured

@Secured("ROLE_USER")
class HomeController {

    def springSecurityService

    def index() {
        def user = springSecurityService.principal
        String name = user.name
    }
}
