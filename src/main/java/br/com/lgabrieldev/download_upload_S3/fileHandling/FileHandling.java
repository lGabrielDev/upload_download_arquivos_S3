package br.com.lgabrieldev.download_upload_S3.fileHandling;

import java.nio.file.Path;
import br.com.lgabrieldev.download_upload_S3.models.DefaultFile;

public interface FileHandling {     
     public abstract void createFile(DefaultFile defaultFile, Path downloadPath);
}
