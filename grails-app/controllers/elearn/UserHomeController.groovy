package elearn

import grails.plugins.springsecurity.Secured

import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.Statement

@Secured(['ROLE_ADMIN','ROLE_TEACHER','ROLE_STUDENT','ROLE_TA'])
class UserHomeController {

    def springSecurityService

    ResultSet rsNews, rsSections
    Connection src = DriverManager.getConnection("jdbc:postgresql:elearn","kolby","Cheese85")
    Statement stmt = src.createStatement()

    def index() {

        User user = springSecurityService.currentUser

        // TODO Display news from news table

        String uId = sec.loggedInUserInfo(field:"id")
        int userId = Integer.parseInt(uId)
        def tId = params.term
        log.debug("TermId: ${tId}")

        ArrayList<HashMap<String, String>> news = new ArrayList<HashMap<String, String>>()
        rsNews = stmt.executeQuery("select title, news_body from news")

        while(rsNews.next()) {
            HashMap<String, String> nMap = new HashMap<String, String>()
            nMap.put("title", rsNews.getString('title'))
            nMap.put("body", rsNews.getString('news_body'))
            news.add(nMap)
        }

        ArrayList<HashMap<String, String>> sections = new ArrayList<HashMap<String, String>>()
        rsSections = stmt.executeQuery("select c.code, c.name, s.id from section s, course c where s.course_id = c.id and s.term_id = ${tId} and s.id IN (select s.id from section s, section_users su where s.teacher_id = ${userId} or s.assistant_id = ${userId} or su.user_id = ${userId})")

        while(rsSections.next()) {
            HashMap<String, String> map = new HashMap<String, String>()
            map.put("code", rsSections.getString('code'))
            map.put("name", rsSections.getString('name'))
            map.put("section_id", rsSections.getInt('id'))
            sections.add(map)
        };
        log.debug(sections)
        log.debug(news)
        //render view:'index', model:[sections: sections, user: user]
        [news: news, sections: sections, user: user]
    }

}
