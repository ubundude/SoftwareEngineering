package school

import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.Statement
import java.sql.Connection

class GradeViewController {
    ResultSet rs
    Connection src = DriverManager.getConnection("jdbc:postgresql:elearn","kolby","Cheese85")
    Statement stmt = src.createStatement()

    def index() {
        //stmt = src.createStatement()
        ArrayList<HashMap<String, String>> grades = new ArrayList<HashMap<String, String>>();

        String id = sec.loggedInUserInfo(field:'id')
        int userId = Integer.parseInt(id)
        int section = 41
        //TODO need to get sectionId and pass to the select
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

    def addAssignment() {
        //src = DriverManager.getConnection("jdbc:postgresql:elearn","kolby","Cheese85")
        //stmt = src.createStatement()
        GString addAssign = "insert into assignment(name, section_id) values('${name}', ${sectionId})"

        stmt.executeQuery()

    }

    def changeGrade() {
        ArrayList<HashMap<String, String>> students = new ArrayList<HashMap<String, String>>();
        // FIXME int sectionId = params.sId
        GString getStudents = "select s.user_id, u.name, g.grade from section_users s, users u, grades g where section_students_id = 41 and su.user_id = u.id and " // FIXME Add ${sectionId}

        rs = stmt.executeQuery(getStudents)

        while (rs.next()) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("id", rs.getInt('user_id'))
            map.put("name", rs.getString('name'))
            students.add(map)
        }

        log.debug(students)
                [students: students]

    }

    def submitGrades() {

    }
}