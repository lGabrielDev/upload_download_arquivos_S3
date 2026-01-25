package br.com.lgabrieldev.download_upload_S3.aws;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.lgabrieldev.download_upload_S3.models.DefaultFile;

@RestController
@RequestMapping("")
public class S3Controller {

     
     //attributes
     private S3Service s3Service;

     //constructors
     public S3Controller(S3Service s3Service){
          this.s3Service = s3Service;
     }

     @PostMapping("/upload")
     public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file){
          return ResponseEntity.ok(this.s3Service.upload(file));
     }

     @GetMapping("/download/{file_name}")
     public ResponseEntity<byte[]> download(@PathVariable("file_name") String fileName) throws Exception{
          
          DefaultFile defaultFile = this.s3Service.download(fileName);

          return ResponseEntity.ok()
               .contentType(MediaType.valueOf(defaultFile.getFileContentType()))
               .body(defaultFile.getFileData());
     }
}
