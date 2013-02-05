package school

class Course {
    String code
    String name
    Integer credits
    String description

    static hasMany = [section: Section]

    static constraints = {
    }

    String toString() {
        return name
    }
}
