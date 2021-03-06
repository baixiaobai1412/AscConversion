package ord.AscConversion;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;


import java.io.File;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;


@SpringBootApplication
@RestController
public class DocumentationExport {
    public static void main(String[] args) {
        SpringApplication.run(DocumentationExport.class, args);
    }

    @GetMapping("/")
    public RedirectView redirect(){
        RedirectView redirectView = new RedirectView("http://localhost:8080/index.html");
        //访问 localhost:8080/ 跳转到index.html
        redirectView.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
        return redirectView;
    }


    @RequestMapping(path = "/Download/PDF", method = RequestMethod.GET)
    public ResponseEntity<ByteArrayResource> DownloadPDF() throws IOException {
        long time = new Date().getTime();
        ConvertAndCompress.GenerateToPDF(time);
        File file = new File(String.format("generate/PDF/%s/MyPDF.7z", time));

        //新建一个对象来保存http的header
        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=MyPDF_%s.7z", time));
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");

        Path path = Paths.get(file.getAbsolutePath());
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
        return ResponseEntity.ok()
                .headers(header)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);

    }

    @RequestMapping(path = "/Download/HTML", method = RequestMethod.GET)
    public ResponseEntity<ByteArrayResource> DownloadHTML() throws IOException {
        long time = new Date().getTime();
        ConvertAndCompress.GenerateToHTML(time);
        File file = new File(String.format("generate/HTML/%s/MyHTML.7z", time));

        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=MyHTML_%s.7z", time));
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");

        Path path = Paths.get(file.getAbsolutePath());
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
        return ResponseEntity.ok()
                .headers(header)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);

    }
    @RequestMapping(path = "/Download/ASC", method = RequestMethod.GET)
    public ResponseEntity<ByteArrayResource> DownloadASC() throws IOException {
        long time = new Date().getTime();
        ConvertAndCompress.GenerateToASC(time);
        File file = new File(String.format("generate/ASC/%s/MyASC.7z", time));

        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=MyASC_%s.7z", time));
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");

        Path path = Paths.get(file.getAbsolutePath());
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
        return ResponseEntity.ok()
                .headers(header)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
    }
}