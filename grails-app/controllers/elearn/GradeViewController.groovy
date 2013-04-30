package elearn

import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.Statement
import java.sql.Connection

class GradeViewController {
    ResultSet rs
    Connection src = DriverManager.getConnection("jdbc:postgresql:elearn","kolby","Cheese85")
    Statement stmt = src.createStatement()
    def springSecurityService

    def index() {
        ArrayList<HashMap<String, String>> grades = new ArrayList<HashMap<String, String>>();

        String id = sec.loggedInUserInfo(field:'id')
        int userId = Integer.parseInt(id)
        int sectionId = params.getInt('sectionId')

        GString getGrades = "select a.id, a.name, g.grade from assignment a left join grades g on a.id = g.assignment_id where a.section_id = ${sectionId} and g.students_id = ${userId}"
        GString getAssignments = "select id, name from assignment where section_id = ${sectionId}"

        if (springSecurityService.ifAnyGranted('ROLE_TEACHER'))
            rs = stmt.executeQuery(getGrades)
        else if (sec.ifAnyGranted('ROLE_STUDENT'))
            rs = stmt.executeQuery(getAssignments)

            while (rs.next()) {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("id", rs.getInt('id'))
                map.put("name", rs.getString('name'))
                map.put("grade", rs.getString('grade'))
                grades.add(map)
            }

        log.debug(grades)
        [grades: grades, sectionId: sectionId]
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
        int sectionId = params.getInt('sectionId')
        int assignId = params.getInt('assignment')
        log.debug(assignId)
        // FIXME int sectionId = params.sId
        String getStudents = "select g.id as gId, g.grade as grade, u.name as name, u.id as uId from grades g left join users u ON g.students_id = u.id where g.assignment_id = ${assignId} AND u.id IN (select user_id from section_users where section_students_id  = 30)" // FIXME Add ${sectionId}

        rs = stmt.executeQuery(getStudents)

        while (rs.next()) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("gId", rs.getInt('gId'))
            map.put("user", rs.getInt('uId'))
            map.put("name", rs.getString('name'))
            map.put("grade", rs.getString('grade'))
            students.add(map)
        }

        log.debug(students)
        [students: students, sectionId: sectionId]

    }

    def submitGrades() {
        //def params = getParams()
        log.debug(params)
        String[] gId = params.getList('gId')
        String[] grades = params.getList('grade')
        String[] users = params.getList('uId')
        log.debug(grades)
        log.debug(users)

        Iterator gIt = gId.iterator()
        Iterator grIt = grades.iterator()
        Iterator uIt = users.iterator()

        while (gIt.hasNext()) {
            stmt.addBatch("UPDATE grades SET grade=${grIt.next()}, students_id=${uIt.next()} WHERE id=${gIt.next()}")
        }

        stmt.executeBatch()

        redirect action:index(), params: [sectionId: params.getInt('sectionId')]
    }
}