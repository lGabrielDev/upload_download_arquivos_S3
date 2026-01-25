package br.com.lgabrieldev.download_upload_S3.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DefaultFile {
     
     //attributes
     private String fileName;
     private String fileContentType;
     byte[] fileData;
}