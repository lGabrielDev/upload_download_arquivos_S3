package br.com.lgabrieldev.download_upload_S3.fileRename;

import java.nio.file.Files;
import java.nio.file.Path;
import org.springframework.stereotype.Component;
import br.com.lgabrieldev.download_upload_S3.error.exceptions.DirectoryNotFoundException;
import br.com.lgabrieldev.download_upload_S3.models.DefaultFile;


@Component
public class FileRenameImp implements FileRename{


     @Override
     public Boolean fileExists(DefaultFile defaultFile, Path downloadPath){
          if( !(Files.exists(downloadPath)) ){
               throw new DirectoryNotFoundException(String.format("directory '%s' not found.... Check the path. ", downloadPath));
          }
          return true;
     }
     
     @Override
     public String renameFileIfExists(DefaultFile defaultFile, Path downloadPath) {
               Integer dotLastPosition = defaultFile.getFileName().lastIndexOf(".");
               String fileExtension = defaultFile.getFileName().substring(dotLastPosition);
               String fileNameWithOutExtension = defaultFile.getFileName().substring(0, dotLastPosition);
               long duplicateFileCount = 0;
               try{
                    duplicateFileCount = Files.list(downloadPath)
                         .filter((file) -> file.getFileName().toString().contains(fileNameWithOutExtension))
                         .count();
               }
               catch(Exception e){
                    throw new DirectoryNotFoundException(String.format("directory '%s' not found.... Check the path. ", downloadPath));
               }
               String renamedFile = String.format("%s(%d)%s", fileNameWithOutExtension, duplicateFileCount, fileExtension);
               defaultFile.setFileName(renamedFile);        
               return renamedFile;
     }
}