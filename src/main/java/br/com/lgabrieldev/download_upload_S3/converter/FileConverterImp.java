package br.com.lgabrieldev.download_upload_S3.converter;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import br.com.lgabrieldev.download_upload_S3.models.DefaultFile;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;

@Component
public class FileConverterImp implements FileConverter{

     @Override
     public DefaultFile convertMultiPartFile (MultipartFile file) {
          
          DefaultFile defaultFile = new DefaultFile();
               defaultFile.setFileName(file.getOriginalFilename());
               defaultFile.setFileContentType(file.getContentType());
          try{
               defaultFile.setFileData(file.getBytes());
          }
          catch(Exception e){
               throw new RuntimeException(String.format("Failed to read bytes from file: '%s'", defaultFile.getFileName()));
          }
          return defaultFile;
     }

     @Override
     public DefaultFile convertGetObjectResponse(ResponseInputStream<GetObjectResponse> s3Object, String fileName) {

          DefaultFile defaultFile = new DefaultFile();
               defaultFile.setFileName(fileName);
               defaultFile.setFileContentType(s3Object.response().contentType());
               try{
               defaultFile.setFileData(s3Object.readAllBytes());
          }
          catch(Exception e){
               throw new RuntimeException(String.format("Failed to read bytes from file: '%s'", defaultFile.getFileName()));
          }
               return defaultFile;
     }
}