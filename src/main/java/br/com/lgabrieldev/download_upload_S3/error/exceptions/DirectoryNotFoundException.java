package br.com.lgabrieldev.download_upload_S3.error.exceptions;

public class DirectoryNotFoundException extends RuntimeException {

     public DirectoryNotFoundException(String errorMessage){
          super(errorMessage);
     }
}