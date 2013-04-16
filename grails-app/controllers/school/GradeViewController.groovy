package school

import groovy.sql.Sql

class GradeViewController {

    def index() {
        sql = Sql.newInstance( 'jdbc:jtds:sqlserver://localhost/dbName-elearn','kolby',
                'Cheese85', 'net.sourceforge.jtds.jdbc.Driver')

        sql.rowEach('select ')
    }
}
