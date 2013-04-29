package elearn

class Course {
    String code
    String name
    Integer credits
    String description

    static constraints = {
    }

    String toString() {
        return name
    }
}
