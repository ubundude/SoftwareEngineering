package elearn

import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.Statement

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

        def title = params.get('title')
        def summary = params.get('summary')
        log.debug(params.uploadedFile)
        if(params.uploadedFile.getOriginalFilename()) {
            if(params.uploadedFile instanceof org.springframework.web.multipart.commons.CommonsMultipartFile) {
                new FileOutputStream("c:/users/kolby/grailsUploads/${params.uploadedFile.getOriginalFilename()}").leftShift(params.uploadedFile.getInputStream());
                def output = "c:/users/kolby/IdeaProjects/SoftwareEngineering/web-app/grailsUpload/${params.uploadedFile.getOriginalFilename()}"
                stmt.execute("INSERT INTO content(title, summary, section_id, contenturi) VALUES('${title}','${summary}','${sectionId}','${output}')",  Statement.RETURN_GENERATED_KEYS)
            } else {
                log.error("wrong attachment type [${params.uploadedFile.getClass()}");
            }
        }
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
