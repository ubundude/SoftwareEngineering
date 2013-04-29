package elearn

class Content {
    String title
    String summary

    static belongsTo = [section: Section]

    static constraints = {
        summary nullable: true
    }
}
