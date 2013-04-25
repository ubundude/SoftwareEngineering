package school

import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.Statement

class UserHomeController {

    def springSecurityService

    ResultSet rs
    Connection src = DriverManager.getConnection("jdbc:postgresql:elearn","kolby","Cheese85")
    Statement stmt = src.createStatement()

    def index() {

        User user = springSecurityService.currentUser

        // TODO Display news from news table

        String uId = sec.loggedInUserInfo(field:"id")
        int userId = Integer.parseInt(uId)
        def tId = params.term
        log.debug("TermId: ${tId}")
        ArrayList<HashMap<String, String>> sections = new ArrayList<HashMap<String, String>>();
        rs = stmt.executeQuery("select c.code, c.name, s.id from section s, course c where s.course_id = c.id and s.term_id = ${tId} and s.id IN (select s.id from section s, section_users su where s.teacher_id = ${userId} or s.assistant_id = ${userId} or su.user_id = ${userId})")

        while(rs.next()) {
            HashMap<String, String> map = new HashMap<String, String>()
            map.put("code", rs.getString('code'))
            map.put("name", rs.getString('name'))
            map.put("section_id", rs.getInt('id'))
            sections.add(map)
        };
        log.debug(sections)
        render view:'index', model:[sections: sections, user: user]
        [sections: sections, user: user]
    }

}
