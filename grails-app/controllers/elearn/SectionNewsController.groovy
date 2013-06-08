package elearn

import org.springframework.dao.DataIntegrityViolationException

class SectionNewsController {

    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']

    def index() {
        redirect action: 'list', params: params
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [sectionNewsInstanceList: SectionNews.list(params), sectionNewsInstanceTotal: SectionNews.count()]
    }

    def create() {
        switch (request.method) {
            case 'GET':
                [sectionNewsInstance: new SectionNews(params)]
                break
            case 'POST':
                def sectionNewsInstance = new SectionNews(params)
                if (!sectionNewsInstance.save(flush: true)) {
                    render view: 'create', model: [sectionNewsInstance: sectionNewsInstance]
                    return
                }

                flash.message = message(code: 'default.created.message', args: [message(code: 'sectionNews.label', default: 'SectionNews'), sectionNewsInstance.id])
                redirect action: 'show', id: sectionNewsInstance.id
                break
        }
    }

    def show() {
        def sectionNewsInstance = SectionNews.get(params.id)
        if (!sectionNewsInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'sectionNews.label', default: 'SectionNews'), params.id])
            redirect action: 'list'
            return
        }

        [sectionNewsInstance: sectionNewsInstance]
    }

    def edit() {
        switch (request.method) {
            case 'GET':
                def sectionNewsInstance = SectionNews.get(params.id)
                if (!sectionNewsInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'sectionNews.label', default: 'SectionNews'), params.id])
                    redirect action: 'list'
                    return
                }

                [sectionNewsInstance: sectionNewsInstance]
                break
            case 'POST':
                def sectionNewsInstance = SectionNews.get(params.id)
                if (!sectionNewsInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'sectionNews.label', default: 'SectionNews'), params.id])
                    redirect action: 'list'
                    return
                }

                if (params.version) {
                    def version = params.version.toLong()
                    if (sectionNewsInstance.version > version) {
                        sectionNewsInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
                                [message(code: 'sectionNews.label', default: 'SectionNews')] as Object[],
                                "Another user has updated this SectionNews while you were editing")
                        render view: 'edit', model: [sectionNewsInstance: sectionNewsInstance]
                        return
                    }
                }

                sectionNewsInstance.properties = params

                if (!sectionNewsInstance.save(flush: true)) {
                    render view: 'edit', model: [sectionNewsInstance: sectionNewsInstance]
                    return
                }

                flash.message = message(code: 'default.updated.message', args: [message(code: 'sectionNews.label', default: 'SectionNews'), sectionNewsInstance.id])
                redirect action: 'show', id: sectionNewsInstance.id
                break
        }
    }

    def delete() {
        def sectionNewsInstance = SectionNews.get(params.id)
        if (!sectionNewsInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'sectionNews.label', default: 'SectionNews'), params.id])
            redirect action: 'list'
            return
        }

        try {
            sectionNewsInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'sectionNews.label', default: 'SectionNews'), params.id])
            redirect action: 'list'
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'sectionNews.label', default: 'SectionNews'), params.id])
            redirect action: 'show', id: params.id
        }
    }
}