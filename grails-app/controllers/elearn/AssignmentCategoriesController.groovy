package elearn

import org.springframework.dao.DataIntegrityViolationException

class AssignmentCategoriesController {

    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']

    def index() {
        redirect action: 'list', params: params
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [assignmentCategoriesInstanceList: AssignmentCategories.list(params), assignmentCategoriesInstanceTotal: AssignmentCategories.count()]
    }

    def create() {
        switch (request.method) {
            case 'GET':
                [assignmentCategoriesInstance: new AssignmentCategories(params)]
                break
            case 'POST':
                def assignmentCategoriesInstance = new AssignmentCategories(params)
                if (!assignmentCategoriesInstance.save(flush: true)) {
                    render view: 'create', model: [assignmentCategoriesInstance: assignmentCategoriesInstance]
                    return
                }

                flash.message = message(code: 'default.created.message', args: [message(code: 'assignmentCategories.label', default: 'AssignmentCategories'), assignmentCategoriesInstance.id])
                redirect action: 'show', id: assignmentCategoriesInstance.id
                break
        }
    }

    def show() {
        def assignmentCategoriesInstance = AssignmentCategories.get(params.id)
        if (!assignmentCategoriesInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'assignmentCategories.label', default: 'AssignmentCategories'), params.id])
            redirect action: 'list'
            return
        }

        [assignmentCategoriesInstance: assignmentCategoriesInstance]
    }

    def edit() {
        switch (request.method) {
            case 'GET':
                def assignmentCategoriesInstance = AssignmentCategories.get(params.id)
                if (!assignmentCategoriesInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'assignmentCategories.label', default: 'AssignmentCategories'), params.id])
                    redirect action: 'list'
                    return
                }

                [assignmentCategoriesInstance: assignmentCategoriesInstance]
                break
            case 'POST':
                def assignmentCategoriesInstance = AssignmentCategories.get(params.id)
                if (!assignmentCategoriesInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'assignmentCategories.label', default: 'AssignmentCategories'), params.id])
                    redirect action: 'list'
                    return
                }

                if (params.version) {
                    def version = params.version.toLong()
                    if (assignmentCategoriesInstance.version > version) {
                        assignmentCategoriesInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
                                [message(code: 'assignmentCategories.label', default: 'AssignmentCategories')] as Object[],
                                "Another user has updated this AssignmentCategories while you were editing")
                        render view: 'edit', model: [assignmentCategoriesInstance: assignmentCategoriesInstance]
                        return
                    }
                }

                assignmentCategoriesInstance.properties = params

                if (!assignmentCategoriesInstance.save(flush: true)) {
                    render view: 'edit', model: [assignmentCategoriesInstance: assignmentCategoriesInstance]
                    return
                }

                flash.message = message(code: 'default.updated.message', args: [message(code: 'assignmentCategories.label', default: 'AssignmentCategories'), assignmentCategoriesInstance.id])
                redirect action: 'show', id: assignmentCategoriesInstance.id
                break
        }
    }

    def delete() {
        def assignmentCategoriesInstance = AssignmentCategories.get(params.id)
        if (!assignmentCategoriesInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'assignmentCategories.label', default: 'AssignmentCategories'), params.id])
            redirect action: 'list'
            return
        }

        try {
            assignmentCategoriesInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'assignmentCategories.label', default: 'AssignmentCategories'), params.id])
            redirect action: 'list'
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'assignmentCategories.label', default: 'AssignmentCategories'), params.id])
            redirect action: 'show', id: params.id
        }
    }
}