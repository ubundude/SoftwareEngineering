package elearn

import grails.plugins.springsecurity.Secured

import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.Statement

@Secured(['ROLE_ADMIN','ROLE_TEACHER','ROLE_STUDENT','ROLE_TA'])
class SectionContentController {
    ResultSet rs
    Connection src = DriverManager.getConnection("jdbc:postgresql:elearn","kolby","Cheese85")
    Statement stmt = src.createStatement()

    def index() {
        int sectionId = params.getInt('sectionId')
        ArrayList<HashMap<String, String>> contents = new ArrayList<HashMap<String, String>>();

        String getContent = "select title, summary, contenturi from content where section_id = ${sectionId}"
        rs = stmt.executeQuery(getContent)

        while(rs.next()) {
            HashMap<String, String> map = new HashMap<String, String>()
            map.put("title", rs.getString('title'))
            map.put("summary", rs.getString('summary'))
            map.put("link", rs.getString('contenturi'))
            contents.add(map)
        }
        [sectionId: sectionId, contents: contents]
    }

    def addContent() {
        int sectionId = params.getInt('sectionId')

        [sectionId: sectionId]
    }

    def upload = {
        def sectionId = params.getInt('sectionId')
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

        Content content = new Content([title: title, summary: summary, contentURI: output, section: sectionId]).save()
        log.debug(content)

        redirect action: index(), params: params
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
