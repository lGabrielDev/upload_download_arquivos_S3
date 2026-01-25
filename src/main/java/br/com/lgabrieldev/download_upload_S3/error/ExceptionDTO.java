package br.com.lgabrieldev.download_upload_S3.error;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonPropertyOrder({ "timestamp", "statusCode", "errorMessage", "exceptionSourceClass", "exceptionSourceMethod"})
public class ExceptionDTO {
     
     //attributes
     LocalDateTime timestamp;
     Integer statusCode;
     String errorMessage;
     String exceptionSourceClass;
     String exceptionSourceMethod;

     public ExceptionDTO(){
          this.timestamp = LocalDateTime.now();
     }

     public String getTimestamp(){
          DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
          return dtf.format(this.timestamp);
     }
}