package elearn

import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.Statement
import java.sql.Connection

class GradeViewController {
    ResultSet rs
    Connection src = DriverManager.getConnection("jdbc:postgresql:elearn","kolby","Cheese85")
    Statement stmt = src.createStatement()

    def index() {
        ArrayList<HashMap<String, String>> grades = new ArrayList<HashMap<String, String>>();

        String id = sec.loggedInUserInfo(field:'id')
        int userId = Integer.parseInt(id)
        int section = params.getInt('section')

        //TODO need to get sectionId and pass to the select
        GString getGrades = "select a.id, a.name, g.grade from assignment a left join grades g on a.id = g.assignment_id where a.section_id = ${section} and g.students_id = ${userId}"

        rs = stmt.executeQuery(getGrades)

        while (rs.next()) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("id", rs.getInt('id'))
            map.put("name", rs.getString('name'))
            map.put("grade", rs.getString('grade'))
            grades.add(map)
        }
        log.debug(grades)
        [grades: grades, section: section]
    }

    def addAssignment() {
        //src = DriverManager.getConnection("jdbc:postgresql:elearn","kolby","Cheese85")
        //stmt = src.createStatement()
        //String addAssign = "insert into assignment(name, section_id) values('${name}', ${sectionId})"

       //stmt.executeQuery()

    }

    def addHandler() {
        String addAssign = "insert into assignment(name, section_id) values('${name}', ${sectionId})"

        log.debug(addAssign)
        stmt.executeQuery()

        redirect(action: addAssignment())
    }

    def changeGrade() {
        log.debug(params)
        ArrayList<HashMap<String, String>> students = new ArrayList<HashMap<String, String>>();
        int sectionId = params.getInt('section')
        int assignId = params.getInt('assignment')
        log.debug(assignId)
        // FIXME int sectionId = params.sId
        String getStudents = "select g.grade as grade, u.name as name, u.id as uId from grades g left join users u ON g.students_id = u.id where g.assignment_id = ${assignId} AND u.id IN (select user_id from section_users where section_students_id  = 30)" // FIXME Add ${sectionId}

        rs = stmt.executeQuery(getStudents)

        while (rs.next()) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("id", rs.getInt('uId'))
            map.put("name", rs.getString('name'))
            map.put("grade", rs.getString('grade'))
            students.add(map)
        }

        log.debug(students)
        [students: students, section:sectionId]

    }

    def submitGrades() {

    }
}