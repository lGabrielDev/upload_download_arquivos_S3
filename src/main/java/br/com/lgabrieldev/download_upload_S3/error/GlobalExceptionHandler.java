package br.com.lgabrieldev.download_upload_S3.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import br.com.lgabrieldev.download_upload_S3.error.exceptions.DirectoryNotFoundException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.services.s3.model.NoSuchBucketException;
import software.amazon.awssdk.services.s3.model.NoSuchKeyException;
import software.amazon.awssdk.services.s3.model.S3Exception;

@ControllerAdvice
public class GlobalExceptionHandler {
     
     @ExceptionHandler(DirectoryNotFoundException.class)
     public ResponseEntity<ExceptionDTO> directoryNotFoundExceptionHandler(DirectoryNotFoundException e){
         
          StackTraceElement[] stackTraces = e.getStackTrace();
          StackTraceElement firstStackTrace = stackTraces[0];

          ExceptionDTO dto = new ExceptionDTO();
          dto.setStatusCode(HttpStatus.BAD_REQUEST.value());
          dto.setErrorMessage(e.getMessage());
          dto.setExceptionSourceClass(firstStackTrace.getClassName());
          dto.setExceptionSourceMethod(firstStackTrace.getMethodName() + "()");
          return ResponseEntity.badRequest().body(dto);
     }

     // ************************************* Credential exceptions (access key, secret access key and region) *************************************
     
     //access key and secret access key
     @ExceptionHandler(S3Exception.class)
     public ResponseEntity<ExceptionDTO> s3CredentialsExceptionHandler(S3Exception e){
          String errorMessage = e.getMessage();

          String acessKeyErrorMessage = "Invalid AWS Access Key";
          String secretAcessKeyErrorMessage = "Invalid AWS Secret Access Key";

          //tomar cuidado caso a AWS altere a mensagem de erro. Depois, procurar outra forma de encontrar se o erro foi no access key ou na secret.
          if(errorMessage.contains("The AWS Access Key Id you provided does not exist")){ 
               errorMessage = acessKeyErrorMessage;
          } 
          else if(errorMessage.contains("The request signature we calculated does not match")){
               errorMessage = secretAcessKeyErrorMessage;
          }

          StackTraceElement[] stackTraces = e.getStackTrace();
          StackTraceElement firstStackTrace = stackTraces[0];

          ExceptionDTO dto = new ExceptionDTO();
          dto.setStatusCode(HttpStatus.FORBIDDEN.value());
          dto.setErrorMessage(errorMessage);
          dto.setExceptionSourceClass(firstStackTrace.getClassName());
          dto.setExceptionSourceMethod(firstStackTrace.getMethodName() + "()");
          return ResponseEntity.status(HttpStatus.FORBIDDEN).body(dto);
     }
     
     //region wrong
     @ExceptionHandler(SdkClientException.class)
     public ResponseEntity<ExceptionDTO> regionWrongExceptionHandler(SdkClientException e){
          String errorMessage = e.getMessage();

          if(errorMessage.contains("UnknownHostException")){
               errorMessage = "AWS Region does not exist";
          }
          StackTraceElement[] stackTraces = e.getStackTrace();
          StackTraceElement firstStackTrace = stackTraces[0];

          ExceptionDTO dto = new ExceptionDTO();
          dto.setStatusCode(HttpStatus.NOT_FOUND.value());
          dto.setErrorMessage(errorMessage);
          dto.setExceptionSourceClass(firstStackTrace.getClassName());
          dto.setExceptionSourceMethod(firstStackTrace.getMethodName() + "()");
          return ResponseEntity.status(HttpStatus.NOT_FOUND).body(dto);
     }

     // ************************************* bucket not found *************************************
     @ExceptionHandler(NoSuchBucketException.class)
     public ResponseEntity<ExceptionDTO> noSuchBucketExceptionHandler(NoSuchBucketException e){
          StackTraceElement[] stackTraces = e.getStackTrace();
          StackTraceElement firstStackTrace = stackTraces[0];

          ExceptionDTO dto = new ExceptionDTO();
          dto.setStatusCode(HttpStatus.NOT_FOUND.value());
          dto.setErrorMessage("The specified bucket does not exist");
          dto.setExceptionSourceClass(firstStackTrace.getClassName());
          dto.setExceptionSourceMethod(firstStackTrace.getMethodName() + "()");
          return ResponseEntity.status(HttpStatus.NOT_FOUND).body(dto);
     }

     // ************************************* s3 Object/file not found *************************************
     @ExceptionHandler(NoSuchKeyException.class)
     public ResponseEntity<ExceptionDTO> noSuchKeyExceptionHandler(NoSuchKeyException e, WebRequest request){
          StackTraceElement[] stackTraces = e.getStackTrace();
          StackTraceElement firstStackTrace = stackTraces[0];

          String requestUrl = request.getDescription(false);
          String fileName = requestUrl.substring(requestUrl.lastIndexOf("/") + 1);

          ExceptionDTO dto = new ExceptionDTO();
          dto.setStatusCode(HttpStatus.NOT_FOUND.value());
          dto.setErrorMessage(String.format("The specified key does not exist. File/object '%s' not found.", fileName));
          dto.setExceptionSourceClass(firstStackTrace.getClassName());
          dto.setExceptionSourceMethod(firstStackTrace.getMethodName() + "()");
          return ResponseEntity.status(HttpStatus.NOT_FOUND).body(dto);
     }
}