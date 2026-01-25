package br.com.lgabrieldev.download_upload_S3.converter;

import org.springframework.web.multipart.MultipartFile;
import br.com.lgabrieldev.download_upload_S3.models.DefaultFile;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;

public interface FileConverter {
     
     public abstract DefaultFile convertMultiPartFile(MultipartFile file);
     public abstract DefaultFile convertGetObjectResponse( ResponseInputStream<GetObjectResponse> file, String fileName);
     


     
}