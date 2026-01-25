package br.com.lgabrieldev.download_upload_S3.fileRename;

import java.nio.file.Path;
import br.com.lgabrieldev.download_upload_S3.models.DefaultFile;

public interface FileRename {
     public abstract Boolean fileExists(DefaultFile defaultFile, Path downloadPath);
     public abstract String renameFileIfExists(DefaultFile defaultFile, Path downloadPath);
}
