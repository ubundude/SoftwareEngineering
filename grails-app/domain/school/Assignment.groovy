package school

class Assignment {
    String name
    Date dateDue

    static belongsTo = [section: Section]

    static constraints = {
    }

    String toString() {
        return name
    }
}
