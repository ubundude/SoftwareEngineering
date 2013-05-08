package elearn

/**
 * Class defining the domain object for Content. Content is any files that a teacher may want to upload
 * for the students to download, such as syllabi or PowerPoint's
 *
 * @author Kolby Cansler
 * @author Simeon Burns
 */
class Content {
    String title
    String summary
    String contentURI

    static belongsTo = [section: Section]

    static constraints = {
        summary nullable: true
    }
}
