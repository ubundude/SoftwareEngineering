package school

class UserHomeController {

    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']

    def index() {
        String id = sec.loggedInUserInfo(field:"id")
        int userId = Integer.parseInt(id)

        //List sections = Section.executeQuery("select c.code from Course c, Section s, section_users su where s.course_id = c.id and su.section_students_id = s.id and s.teacher_id = ${userId} or s.assistant_id = ${userId} or su.user_id = ${userId}")
        //[sections: sections]
    }

    def edit() {
        switch (request.method) {
            case 'GET':

            break
            case 'POST':

            break
        }
    }
}
