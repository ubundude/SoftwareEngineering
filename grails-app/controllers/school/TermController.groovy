package school

import org.springframework.dao.DataIntegrityViolationException

class TermController {

    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']

    def index() {
        redirect action: 'list', params: params
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [termInstanceList: Term.list(params), termInstanceTotal: Term.count()]
    }

    def create() {
        switch (request.method) {
            case 'GET':
                [termInstance: new Term(params)]
                break
            case 'POST':
                def termInstance = new Term(params)
                if (!termInstance.save(flush: true)) {
                    render view: 'create', model: [termInstance: termInstance]
                    return
                }

                flash.message = message(code: 'default.created.message', args: [message(code: 'term.label', default: 'Term'), termInstance.id])
                redirect action: 'show', id: termInstance.id
                break
        }
    }

    def show() {
        def termInstance = Term.get(params.id)
        if (!termInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'term.label', default: 'Term'), params.id])
            redirect action: 'list'
            return
        }

        [termInstance: termInstance]
    }

    def edit() {
        switch (request.method) {
            case 'GET':
                def termInstance = Term.get(params.id)
                if (!termInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'term.label', default: 'Term'), params.id])
                    redirect action: 'list'
                    return
                }

                [termInstance: termInstance]
                break
            case 'POST':
                def termInstance = Term.get(params.id)
                if (!termInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'term.label', default: 'Term'), params.id])
                    redirect action: 'list'
                    return
                }

                if (params.version) {
                    def version = params.version.toLong()
                    if (termInstance.version > version) {
                            termInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
                                    [message(code: 'term.label', default: 'Term')] as Object[],
                                    "Another user has updated this Term while you were editing")
                        render view: 'edit', model: [termInstance: termInstance]
                        return
                    }
                }

                termInstance.properties = params

                if (!termInstance.save(flush: true)) {
                    render view: 'edit', model: [termInstance: termInstance]
                    return
                }

                flash.message = message(code: 'default.updated.message', args: [message(code: 'term.label', default: 'Term'), termInstance.id])
                redirect action: 'show', id: termInstance.id
                break
        }
    }

    def delete() {
        def termInstance = Term.get(params.id)
        if (!termInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'term.label', default: 'Term'), params.id])
            redirect action: 'list'
            return
        }

        try {
            termInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'term.label', default: 'Term'), params.id])
            redirect action: 'list'
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'term.label', default: 'Term'), params.id])
            redirect action: 'show', id: params.id
        }
    }
}