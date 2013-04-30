package elearn

class Content {
    String title
    String summary
    String contentURI

    static belongsTo = [section: Section]

    static constraints = {
        summary nullable: true
    }
}
