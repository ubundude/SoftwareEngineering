package elearn

class News {
    String title
    String newsBody
    Date expires

    static mapping = {
        newsBody type: 'text'
    }
    static constraints = {
    }
}
