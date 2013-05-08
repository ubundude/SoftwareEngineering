package elearn

/**
 * Class defining the domain object for News to be displayed on the home page when a User
 * logs in
 *
 * @author Kolby Cansler
 * @author Simeon Burns
 */
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
