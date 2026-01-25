package br.com.lgabrieldev.download_upload_S3.fileHandling;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Path;
import org.springframework.stereotype.Component;
import br.com.lgabrieldev.download_upload_S3.error.exceptions.DirectoryNotFoundException;
import br.com.lgabrieldev.download_upload_S3.models.DefaultFile;

@Component
public class FileHandlingImp implements FileHandling{

     @Override
     public void createFile(DefaultFile defaultFile, Path downloadPath) {
          File downloadedFile = new File(downloadPath + "/" + defaultFile.getFileName());
          try{
               FileOutputStream fileWriter = new FileOutputStream(downloadedFile);
               fileWriter.write(defaultFile.getFileData());
               fileWriter.close();
          }
          catch(Exception e){
               throw new DirectoryNotFoundException(String.format("directory '%s' not found.... Check the path. ", downloadPath));
          }
     }
}