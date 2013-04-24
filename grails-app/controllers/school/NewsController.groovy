package school

import org.springframework.dao.DataIntegrityViolationException

class NewsController {

    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']

    def index() {
        redirect action: 'list', params: params
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [newsInstanceList: News.list(params), newsInstanceTotal: News.count()]
    }

    def create() {
        switch (request.method) {
            case 'GET':
                [newsInstance: new News(params)]
                break
            case 'POST':
                def newsInstance = new News(params)
                if (!newsInstance.save(flush: true)) {
                    render view: 'create', model: [newsInstance: newsInstance]
                    return
                }

                flash.message = message(code: 'default.created.message', args: [message(code: 'news.label', default: 'News'), newsInstance.id])
                redirect action: 'show', id: newsInstance.id
                break
        }
    }

    def show() {
        def newsInstance = News.get(params.id)
        if (!newsInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'news.label', default: 'News'), params.id])
            redirect action: 'list'
            return
        }

        [newsInstance: newsInstance]
    }

    def edit() {
        switch (request.method) {
            case 'GET':
                def newsInstance = News.get(params.id)
                if (!newsInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'news.label', default: 'News'), params.id])
                    redirect action: 'list'
                    return
                }

                [newsInstance: newsInstance]
                break
            case 'POST':
                def newsInstance = News.get(params.id)
                if (!newsInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'news.label', default: 'News'), params.id])
                    redirect action: 'list'
                    return
                }

                if (params.version) {
                    def version = params.version.toLong()
                    if (newsInstance.version > version) {
                            newsInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
                                    [message(code: 'news.label', default: 'News')] as Object[],
                                    "Another user has updated this News while you were editing")
                        render view: 'edit', model: [newsInstance: newsInstance]
                        return
                    }
                }

                newsInstance.properties = params

                if (!newsInstance.save(flush: true)) {
                    render view: 'edit', model: [newsInstance: newsInstance]
                    return
                }

                flash.message = message(code: 'default.updated.message', args: [message(code: 'news.label', default: 'News'), newsInstance.id])
                redirect action: 'show', id: newsInstance.id
                break
        }
    }

    def delete() {
        def newsInstance = News.get(params.id)
        if (!newsInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'news.label', default: 'News'), params.id])
            redirect action: 'list'
            return
        }

        try {
            newsInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'news.label', default: 'News'), params.id])
            redirect action: 'list'
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'news.label', default: 'News'), params.id])
            redirect action: 'show', id: params.id
        }
    }
}