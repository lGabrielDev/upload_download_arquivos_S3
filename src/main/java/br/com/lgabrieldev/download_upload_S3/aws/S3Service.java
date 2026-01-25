package br.com.lgabrieldev.download_upload_S3.aws;

import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import br.com.lgabrieldev.download_upload_S3.converter.FileConverter;
import br.com.lgabrieldev.download_upload_S3.fileHandling.FileHandling;
import br.com.lgabrieldev.download_upload_S3.fileRename.FileRename;
import br.com.lgabrieldev.download_upload_S3.models.DefaultFile;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Service
public class S3Service {

     //attributes
     @Value("${bucket_name}")
     private String bucketName;

     private S3Client s3Client;
     private FileConverter fileConverter;
     private FileRename fileRename;
     private FileHandling fileHandling;

     //constructors
     public S3Service( S3Client s3Client, FileConverter fileConverter, FileRename fileRename, FileHandling fileHandling){
          this.s3Client = s3Client;
          this.fileConverter = fileConverter;
          this.fileRename = fileRename;
          this.fileHandling = fileHandling;
     }

     // *************************************** Upload ***************************************
     public String upload(MultipartFile file){
          DefaultFile defaultFile = fileConverter.convertMultiPartFile(file);

          PutObjectRequest request = PutObjectRequest.builder()
               .bucket(this.bucketName)
               .key(defaultFile.getFileName())
               .contentType(defaultFile.getFileContentType())
               .build();
          this.s3Client.putObject(request, RequestBody.fromBytes(defaultFile.getFileData()));
          return String.format("File '%s' has been successfully uploaded to bucket '%s'.", defaultFile.getFileName(), bucketName);
     }
     
     // *************************************** Download ***************************************
     public DefaultFile download(String fileName) {

          GetObjectRequest getObjectRequest = GetObjectRequest.builder()
               .bucket(this.bucketName)
               .key(fileName)
               .build();
          ResponseInputStream<GetObjectResponse> s3Object = this.s3Client.getObject(getObjectRequest);

          DefaultFile defaultFile = this.fileConverter.convertGetObjectResponse(s3Object, fileName);
          Path downloadPath = Paths.get("downloads"); //ALTERAR ISSO E COLCOAR NO PROPERTIES. ASISM O USUARIO QUE TIVER USANDO CONSEGUE PASSAR O CAMIHO COMPLETO DO CONTAINER...
          Boolean fileAlreadyExists = fileRename.fileExists(defaultFile, downloadPath);

          if(fileAlreadyExists){
               fileRename.renameFileIfExists(defaultFile, downloadPath);
          }
          this.fileHandling.createFile(defaultFile, downloadPath);
          return defaultFile;
     }
}