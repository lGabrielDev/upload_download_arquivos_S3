package br.com.lgabrieldev.download_upload_S3.aws;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class S3Config {

     //attributes
     @Value("${region}")
     private String region;

     @Value("${aws_access_key}")
     private String awsAccessKey;

     @Value("${aws_secret_access_key}")
     private String awsSecretAccessKey;
     
     @Bean
     public S3Client generateS3Client(){
          return S3Client.builder()
               .region(Region.of(region))
               .credentialsProvider(
                    StaticCredentialsProvider.create(
                         AwsBasicCredentials.create(awsAccessKey, awsSecretAccessKey)
                    )
               )
               .build();
     }
}