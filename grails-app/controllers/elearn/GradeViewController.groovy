package elearn

import grails.plugins.springsecurity.Secured
import org.hibernate.Session

import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.Statement
import java.sql.Connection

/**
 * Class to control the views for Teachers to create assignments and manage Student
 * grades for a section. Also lets Students view their grades for that section.
 *
 * @author Kolby Cansler
 * @author Simeon Burns
 */
@Secured(['ROLE_TEACHER','ROLE_STUDENT','ROLE_TA'])
class GradeViewController {
    ResultSet rs
    Connection src = DriverManager.getConnection("jdbc:postgresql:elearn","kolby","Cheese85")
    Statement stmt = src.createStatement()
    def springSecurityService

    def index() {
        ArrayList<HashMap<String, String>> assignments = new ArrayList<HashMap<String, String>>();

        def auth = springSecurityService.authentication.authorities
        log.debug(auth)

        String id = sec.loggedInUserInfo(field:'id')
        int userId = Integer.parseInt(id)
        int section = params.getInt('section')

        String getGrades = "select a.id, a.name, g.grade from assignment a left join grades g on a.id = g.assignment_id" +
                " where a.assignment_categories_id IN (select id from assignment_categories where section_id = ${section}) and g.students_id = ${userId}"
        String getAssignments = "select id, name from assignment where assignment_categories_id IN (select id from assignment_categories where section_id = ${section})"

        if (auth.grep('ROLE_TEACHER')) {
            log.debug("In the first if")
            rs = stmt.executeQuery(getAssignments)
            while(rs.next()) {
                HashMap<String, String> map = new HashMap<String, String>()
                map.put("id", rs.getInt('id'))
                map.put("name", rs.getString('name'))
                assignments.add(map)
            }
        }
        else if (auth.grep('ROLE_STUDENT')) {
            log.debug("in the second if")
            rs = stmt.executeQuery(getGrades)
            while (rs.next()) {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("id", rs.getInt('id'))
                map.put("name", rs.getString('name'))
                map.put("grade", rs.getString('grade'))
                assignments.add(map)
            }
        }


        log.debug(assignments)
        [assignments: assignments, section: section]
    }

    def addAssignment() {
        def section = params.getInt('section')
        [section: section]
    }

    def addHandler() {
        log.debug("Add handler got params: " + params)
        Long saveId
        AssignmentCategories assignmentCategories = AssignmentCategories.get(params.assignmentCategories)
        params.remove('assignmentCategories')
        Assignment assign = new Assignment(params)
        assign.assignmentCategories = assignmentCategories
        log.debug(assign)

        if (assign.validate()) {
            log.debug("saving")
            assign.save(flush: true)
            saveId = assign.id

            log.debug("The id saved is" + saveId)
        } else {
            if (assign.hasErrors()) {
                log.debug(assign.errors)
            }
            render view:'addAssignment', model:params
            return
        }

        params.put('assignment_id', saveId)
        log.debug('passing params' + params)
        redirect action: addStudents(), params: params
    }

    def addStudents() {
        log.debug("Add Students got params: " + params)
        Grades grades
        Assignment assignment = Assignment.get(params.assignment_id)
        params.remove('assignment_id')
        rs = stmt.executeQuery("select user_id from section_users where section_students_id = ${params.section}")
        while(rs.next()) {
            // TODO get help here. Doesn't like by column id
            User user = User.get(rs.getInt('user_id'))
            grades = new Grades([grade: 0])
            grades.assignment = assignment
            grades.students = user
            if (grades.validate()) {
                log.debug("saving student")
                grades.save(fulsh: true)
            } else {
                if (grades.hasErrors()) {
                    log.debug(grades.errors)
                    render view:'addAssignment', model:params
                    return
                }
            }

        }

        log.debug("param quick check " + params)
        redirect action: index(), params: params
    }


    def changeGrade() {
        log.debug(params)
        ArrayList<HashMap<String, String>> students = new ArrayList<HashMap<String, String>>();
        int section = params.getInt('section')
        int assignId = params.getInt('assignment')
        log.debug(assignId)
        // FIXME int section = params.sId
        String getStudents = "select g.id as gId, g.grade as grade, u.name as name, u.id as uId from grades g left join users u " +
                "ON g.students_id = u.id where g.assignment_id = ${assignId} AND u.id IN (select user_id from section_users where section_students_id  = 30)" // FIXME Add ${section}

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
        [students: students, section: section]

    }

    def manageCategories() {
        ArrayList<HashMap<String, String>> cats = new ArrayList<HashMap<String, String>>()
        rs = stmt.executeQuery("select id, name from assignment_categories where section_id = ${params.section}")
        while(rs.next()) {
            HashMap<String, String> map = new HashMap<String, String>()
            map.put('name', rs.getString('name'))
            map.put('id', rs.getString('id'))
            cats.add(map)
        }
        log.debug("section: " + params.section)
        [cats: cats, section: params.section]
    }

    def addCategory() {
        Section section = Section.get(params.section)
        params.remove('section')
        AssignmentCategories assignCat = new AssignmentCategories(params)
        log.debug(params)
        assignCat.section = section
        if (assignCat.validate()){
            log.debug("saving")
            assignCat.save(flush: true)
        } else {
            if (assignCat.hasErrors()) {
                log.debug(assignCat.errors)
                render view:'manageCategories', model:params
                return
            }
        }

        redirect action: manageCategories(), params: [section: section.id]
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

        redirect action:index(), params: [section: params.getInt('section')]
    }
}