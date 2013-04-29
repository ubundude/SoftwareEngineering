package elearn

class Grades {
    String grade

    static belongsTo = [assignment:Assignment, students:User]

    static constraints = {
    }
}
