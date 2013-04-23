package school

import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.Statement

class UserHomeController {

    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']

    def springSecurityService

    def index() {

        User user = springSecurityService.currentUser

        ResultSet rs
        Connection src
        Statement stmt
        src = DriverManager.getConnection("jdbc:postgresql:elearn","kolby","Cheese85")
        stmt = src.createStatement()
        String uId = sec.loggedInUserInfo(field:"id")
        int userId = Integer.parseInt(uId)
        //TODO Get term from combobox
        int tId = 33
        ArrayList<HashMap<String, String>> sections = new ArrayList<HashMap<String, String>>();

        rs = stmt.executeQuery("select c.code, c.name, s.id from course c, section s, section_users su where s.term_id = ${tId} and s.course_id = c.id and su.section_students_id = s.id and s.teacher_id = ${userId} or s.assistant_id = ${userId} or su.user_id = ${userId}")
        while(rs.next()) {
            HashMap<String, String> map = new HashMap<String, String>()
            map.put("code", rs.getString('code'))
            map.put("name", rs.getString('name'))
            map.put("section_id", rs.getInt('id'))
            sections.add(map)
        };
        log.debug(sections)
        [sections: sections, user: user]
    }

    def changeTerm() {

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
