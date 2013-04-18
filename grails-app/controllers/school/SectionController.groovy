package school

import grails.plugins.springsecurity.Secured
import org.springframework.dao.DataIntegrityViolationException

@Secured("ROLE_ADMIN")
class SectionController {

    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']

    def index() {
        redirect action: 'list', params: params
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [sectionInstanceList: Section.list(params), sectionInstanceTotal: Section.count()]
    }

    def create() {
        switch (request.method) {
            case 'GET':
                List teachers = User.executeQuery("select u from User u, UserRole ur where ur.user.id = u.id and ur.role.authority = 'ROLE_TEACHER'")
                List students = User.executeQuery("select u from User u, UserRole ur where ur.user.id = u.id and ur.role.authority = 'ROLE_STUDENT'")
                List ta =  User.executeQuery("select u from User u, UserRole ur where ur.user.id = u.id and ur.role.authority = 'ROLE_TA'")
                List terms = Term.list()
                [sectionInstance: new Section(params), teachers: teachers, students: students, ta: ta, terms: terms]
                break
            case 'POST':
                def sectionInstance = new Section(params)
                if (!sectionInstance.save(flush: true)) {
                    render view: 'create', model: [sectionInstance: sectionInstance]
                    return
                }

                flash.message = message(code: 'default.created.message', args: [message(code: 'section.label', default: 'Section'), sectionInstance.id])
                redirect action: 'show', id: sectionInstance.id
                break
        }
    }

    def show() {
        def sectionInstance = Section.get(params.id)
        if (!sectionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'section.label', default: 'Section'), params.id])
            redirect action: 'list'
            return
        }

        [sectionInstance: sectionInstance]
    }

    def edit() {
        switch (request.method) {
            case 'GET':
                def sectionInstance = Section.get(params.id)
                if (!sectionInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'section.label', default: 'Section'), params.id])
                    redirect action: 'list'
                    return
                }
                      //TODO add lists
                [sectionInstance: sectionInstance]
                break
            case 'POST':
                def sectionInstance = Section.get(params.id)
                if (!sectionInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'section.label', default: 'Section'), params.id])
                    redirect action: 'list'
                    return
                }

                if (params.version) {
                    def version = params.version.toLong()
                    if (sectionInstance.version > version) {
                        sectionInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
                                [message(code: 'section.label', default: 'Section')] as Object[],
                                "Another user has updated this Section while you were editing")
                        render view: 'edit', model: [sectionInstance: sectionInstance]
                        return
                    }
                }

                sectionInstance.properties = params

                if (!sectionInstance.save(flush: true)) {
                    render view: 'edit', model: [sectionInstance: sectionInstance]
                    return
                }

                flash.message = message(code: 'default.updated.message', args: [message(code: 'section.label', default: 'Section'), sectionInstance.id])
                redirect action: 'show', id: sectionInstance.id
                break
        }
    }

    def delete() {
        def sectionInstance = Section.get(params.id)
        if (!sectionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'section.label', default: 'Section'), params.id])
            redirect action: 'list'
            return
        }

        try {
            sectionInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'section.label', default: 'Section'), params.id])
            redirect action: 'list'
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'section.label', default: 'Section'), params.id])
            redirect action: 'show', id: params.id
        }
    }
}
