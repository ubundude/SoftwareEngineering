package elearn

class SectionNews {
    String title
    String newsBody

    static belongsTo = [section: Section]

    static mapping = {
        newsBody type: 'text'
    }

    static constraints = {
    }
}
