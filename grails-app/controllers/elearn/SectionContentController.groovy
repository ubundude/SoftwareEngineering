package elearn

import grails.plugins.springsecurity.Secured

import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.Statement

/**
 * Class to control the views for Section Content. Custom class to display and
 * manage content for the current Section.
 *
 * @author Kolby Cansler
 * @author Simeon Burns
 */
@Secured(['ROLE_ADMIN','ROLE_TEACHER','ROLE_STUDENT','ROLE_TA'])
class SectionContentController {
    ResultSet rs
    Connection src = DriverManager.getConnection("jdbc:postgresql:elearn","kolby","Cheese85")
    Statement stmt = src.createStatement()

    def index() {
        int section = params.getInt('section')
        ArrayList<HashMap<String, String>> contents = new ArrayList<HashMap<String, String>>();

        String getContent = "select title, summary, contenturi from content where section_id = ${section}"
        rs = stmt.executeQuery(getContent)

        while(rs.next()) {
            HashMap<String, String> map = new HashMap<String, String>()
            map.put("title", rs.getString('title'))
            map.put("summary", rs.getString('summary'))
            map.put("link", rs.getString('contenturi'))
            contents.add(map)
        }
        [section: section, contents: contents]
    }

    def addContent() {
        int section = params.getInt('section')

        [section: section]
    }

    def upload = {
        def output

        def title = params.get('title')
        def summary = params.get('summary')
        log.debug(params.uploadedFile)
        if(params.uploadedFile.getOriginalFilename()) {
            if(params.uploadedFile instanceof org.springframework.web.multipart.commons.CommonsMultipartFile) {
                new FileOutputStream("c:/users/kolby/grailsUploads/${params.uploadedFile.getOriginalFilename()}").leftShift(params.uploadedFile.getInputStream());
                output = "c:/users/kolby/IdeaProjects/SoftwareEngineering/web-app/grailsUpload/${params.uploadedFile.getOriginalFilename()}"
            } else {
                log.error("wrong attachment type [${params.uploadedFile.getClass()}");
            }
        }

        Section section = Section.get(params.section)
        params.remove('section')
        Content content = new Content([title: title, summary: summary, contentURI: output, section: section])
//        content.section = section
        if (content.validate()) {
            log.debug('saving')
            content.save(flush: true)
        } else if (content.hasErrors()) {
            log.debug(content.errors)

            render view:'addContent', model:params
            return
        }

        redirect action: index(), params: [params: params, section: section]
    }

    def download() {
        def link = params.get('link')
        log.debug(link)
        def file = new File("${link}")
        response.setHeader("Content-Type", "application/octet-stream;")
        response.setHeader("Content-Disposition", "attachment;filename=${file.getName()}")
        response.setHeader("Content-Length", "${file.size()}")

        response.outputStream << file.newInputStream()
        redirect action: index(), params: params
    }
}
