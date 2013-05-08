package elearn

import grails.plugins.springsecurity.Secured
import org.springframework.dao.DataIntegrityViolationException

/**
 * Class to control the views for managing assignments. Not publicly accessible.
 *
 * @author Kolby Cansler
 * @author Simeon Burns
 */
@Secured(['ROLE_TEACHER', 'ROLE_TA'])
class AssignmentController {

    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']

    def index() {
        redirect action: 'list', params: params
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [assignmentInstanceList: Assignment.list(params), assignmentInstanceTotal: Assignment.count()]
    }

    def create() {
        switch (request.method) {
            case 'GET':
                [assignmentInstance: new Assignment(params)]
                break
            case 'POST':
                def assignmentInstance = new Assignment(params)
                if (!assignmentInstance.save(flush: true)) {
                    render view: 'create', model: [assignmentInstance: assignmentInstance]
                    return
                }

                flash.message = message(code: 'default.created.message', args: [message(code: 'assignment.label', default: 'Assignment'), assignmentInstance.id])
                redirect action: 'show', id: assignmentInstance.id
                break
        }
    }

    def show() {
        def assignmentInstance = Assignment.get(params.id)
        if (!assignmentInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'assignment.label', default: 'Assignment'), params.id])
            redirect action: 'list'
            return
        }

        [assignmentInstance: assignmentInstance]
    }

    def edit() {
        switch (request.method) {
            case 'GET':
                def assignmentInstance = Assignment.get(params.id)
                if (!assignmentInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'assignment.label', default: 'Assignment'), params.id])
                    redirect action: 'list'
                    return
                }

                [assignmentInstance: assignmentInstance]
                break
            case 'POST':
                def assignmentInstance = Assignment.get(params.id)
                if (!assignmentInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'assignment.label', default: 'Assignment'), params.id])
                    redirect action: 'list'
                    return
                }

                if (params.version) {
                    def version = params.version.toLong()
                    if (assignmentInstance.version > version) {
                            assignmentInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
                                    [message(code: 'assignment.label', default: 'Assignment')] as Object[],
                                    "Another user has updated this Assignment while you were editing")
                        render view: 'edit', model: [assignmentInstance: assignmentInstance]
                        return
                    }
                }

                assignmentInstance.properties = params

                if (!assignmentInstance.save(flush: true)) {
                    render view: 'edit', model: [assignmentInstance: assignmentInstance]
                    return
                }

                flash.message = message(code: 'default.updated.message', args: [message(code: 'assignment.label', default: 'Assignment'), assignmentInstance.id])
                redirect action: 'show', id: assignmentInstance.id
                break
        }
    }

    def delete() {
        def assignmentInstance = Assignment.get(params.id)
        if (!assignmentInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'assignment.label', default: 'Assignment'), params.id])
            redirect action: 'list'
            return
        }

        try {
            assignmentInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'assignment.label', default: 'Assignment'), params.id])
            redirect action: 'list'
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'assignment.label', default: 'Assignment'), params.id])
            redirect action: 'show', id: params.id
        }
    }
}