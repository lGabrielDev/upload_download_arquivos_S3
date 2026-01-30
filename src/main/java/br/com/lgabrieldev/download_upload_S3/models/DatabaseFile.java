package br.com.lgabrieldev.download_upload_S3.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "tb_files")
public class DatabaseFile {
     
     //attributes
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;

     @Column(name = "file_name")
     private String fileName;

     @Column(name = "file_type")
     private String fileType;

     @Column(name = "bucket_name")
     private String bucketName;

     //constructors
     public DatabaseFile(String fileName, String fileType, String bucketName){
          this.fileName = fileName;
          this.fileType = fileType;
          this.bucketName = bucketName;
     }

     //setters
     public void setFileName(String fileName) {
          this.fileName = fileName;
     }

     public void setFileType(String fileType) {
          this.fileType = fileType;
     }

     public void setBucketName(String bucketName) {
          this.bucketName = bucketName;
     }
}