package elearn

import grails.plugins.springsecurity.Secured

import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.Statement

/**
 * Class to control the views for the views displaying the Class List for the section.
 *
 * @author Kolby Cansler
 * @author Simeon Burns
 */
@Secured(['ROLE_TEACHER', 'ROLE_TA', 'ROLE_STUDENT'])
class SectionUsersController {
    ResultSet rs
    Connection src = DriverManager.getConnection("jdbc:postgresql:elearn","kolby","Cheese85")
    Statement stmt = src.createStatement()

    def index() {
        rs = stmt.executeQuery("select u.name from users u join section s on s.teacher_id = u.id where s.id = ${params.section}")
        rs.next()
        String teacher = rs.getString('name')

        rs = stmt.executeQuery("select u.name from users u join section s on s.assistant_id = u.id where s.id = ${params.section}")
        String ta
        if (rs.next()) {
            ta = rs.getString('name')
        } else {
            ta = "No Assistant"
        }

        ArrayList<HashMap<String, String>> students = new ArrayList<HashMap<String, String>>()
        rs = stmt.executeQuery("select u.name from users u join section_users su on su.user_id = u.id where su.section_students_id = ${params.section}")
        while (rs.next()) {
            HashMap<String, String> map = new HashMap<String, String>()
            map.put("name", rs.getString('name'))
            students.add(map)
        }

        [section: params.section, teacher: teacher, ta: ta, students: students]

    }
}
