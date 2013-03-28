package school

import org.springframework.dao.DataIntegrityViolationException

class GradesController {

    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']

    def index() {
        redirect action: 'list', params: params
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [gradesInstanceList: Grades.list(params), gradesInstanceTotal: Grades.count()]
    }

    def create() {
        switch (request.method) {
            case 'GET':
                [gradesInstance: new Grades(params)]
                break
            case 'POST':
                def gradesInstance = new Grades(params)
                if (!gradesInstance.save(flush: true)) {
                    render view: 'create', model: [gradesInstance: gradesInstance]
                    return
                }

                flash.message = message(code: 'default.created.message', args: [message(code: 'grades.label', default: 'Grades'), gradesInstance.id])
                redirect action: 'show', id: gradesInstance.id
                break
        }
    }

    def show() {
        def gradesInstance = Grades.get(params.id)
        if (!gradesInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'grades.label', default: 'Grades'), params.id])
            redirect action: 'list'
            return
        }

        [gradesInstance: gradesInstance]
    }

    def edit() {
        switch (request.method) {
            case 'GET':
                def gradesInstance = Grades.get(params.id)
                if (!gradesInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'grades.label', default: 'Grades'), params.id])
                    redirect action: 'list'
                    return
                }

                [gradesInstance: gradesInstance]
                break
            case 'POST':
                def gradesInstance = Grades.get(params.id)
                if (!gradesInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'grades.label', default: 'Grades'), params.id])
                    redirect action: 'list'
                    return
                }

                if (params.version) {
                    def version = params.version.toLong()
                    if (gradesInstance.version > version) {
                        gradesInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
                                [message(code: 'grades.label', default: 'Grades')] as Object[],
                                "Another user has updated this Grades while you were editing")
                        render view: 'edit', model: [gradesInstance: gradesInstance]
                        return
                    }
                }

                gradesInstance.properties = params

                if (!gradesInstance.save(flush: true)) {
                    render view: 'edit', model: [gradesInstance: gradesInstance]
                    return
                }

                flash.message = message(code: 'default.updated.message', args: [message(code: 'grades.label', default: 'Grades'), gradesInstance.id])
                redirect action: 'show', id: gradesInstance.id
                break
        }
    }

    def delete() {
        def gradesInstance = Grades.get(params.id)
        if (!gradesInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'grades.label', default: 'Grades'), params.id])
            redirect action: 'list'
            return
        }

        try {
            gradesInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'grades.label', default: 'Grades'), params.id])
            redirect action: 'list'
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'grades.label', default: 'Grades'), params.id])
            redirect action: 'show', id: params.id
        }
    }
}