package school

class Section {
    String sectionNumber

    static belongsTo = [course: Course]

    static constraints = {
    }
}
