package school

import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.Statement
import java.sql.Connection

class GradeViewController {

    def index() {
        ResultSet rs
        Connection src
        Statement stmt
        src = DriverManager.getConnection("jdbc:postgresql:elearn","kolby","Cheese85")
        stmt = src.createStatement()
        ArrayList<HashMap<String, String>> grades = new ArrayList<HashMap<String, String>>();

        String id = sec.loggedInUserInfo(field:'id')
        int userId = Integer.parseInt(id)
        GString getGrades = "select a.name, g.grade from assignment a left join grades g on a.id = g.assignment_id where g.students_id = ${userId}"

        rs = stmt.executeQuery(getGrades)

        while (rs.next()) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("name", rs.getString('name'))
            map.put("grade", rs.getString('grade'))
            grades.add(map)
        }
        log.debug(grades)
        [grades: grades]
    }

    def create() {
        GString getAllStudents = "select a.name, g.grade from assignment a right join grades g on a.id = g.assignment_id "
    }
}
