package school

import grails.plugins.springsecurity.Secured
import org.springframework.dao.DataIntegrityViolationException

@Secured("ROLE_ADMIN")
class CourseController {

    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']

    def index() {
        redirect action: 'list', params: params
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [courseInstanceList: Course.list(params), courseInstanceTotal: Course.count()]
    }

    def create() {
        switch (request.method) {
            case 'GET':
                [courseInstance: new Course(params)]
                break
            case 'POST':
                def courseInstance = new Course(params)
                if (!courseInstance.save(flush: true)) {
                    render view: 'create', model: [courseInstance: courseInstance]
                    return
                }

                flash.message = message(code: 'default.created.message', args: [message(code: 'course.label', default: 'Course'), courseInstance.id])
                redirect action: 'show', id: courseInstance.id
                break
        }
    }

    def show() {
        def courseInstance = Course.get(params.id)
        if (!courseInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'course.label', default: 'Course'), params.id])
            redirect action: 'list'
            return
        }

        [courseInstance: courseInstance]
    }

    def edit() {
        switch (request.method) {
            case 'GET':
                def courseInstance = Course.get(params.id)
                if (!courseInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'course.label', default: 'Course'), params.id])
                    redirect action: 'list'
                }

                [courseInstance: courseInstance]
                break
            case 'POST':
                def courseInstance = Course.get(params.id)
                if (!courseInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'course.label', default: 'Course'), params.id])
                    redirect action: 'list'
                    return
                }

                if (params.version) {
                    def version = params.version.toLong()
                    if (courseInstance.version > version) {
                        courseInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
                                [message(code: 'course.label', default: 'Course')] as Object[],
                                "Another user has updated this Course while you were editing")
                        render view: 'edit', model: [courseInstance: courseInstance]
                        return
                    }
                }

                courseInstance.properties = params

                if (!courseInstance.save(flush: true)) {
                    render view: 'edit', model: [courseInstance: courseInstance]
                    return
                }

                flash.message = message(code: 'default.updated.message', args: [message(code: 'course.label', default: 'Course'), courseInstance.id])
                redirect action: 'show', id: courseInstance.id
                break
        }
    }

    def delete() {
        def courseInstance = Course.get(params.id)
        if (!courseInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'course.label', default: 'Course'), params.id])
            redirect action: 'list'
            return
        }

        try {
            courseInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'course.label', default: 'Course'), params.id])
            redirect action: 'list'
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'course.label', default: 'Course'), params.id])
            redirect action: 'show', id: params.id
        }
    }
}
