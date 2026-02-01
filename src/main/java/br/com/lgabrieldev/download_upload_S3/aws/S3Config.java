package br.com.lgabrieldev.download_upload_S3.aws;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class S3Config {

     @Bean
     public S3Client generateS3ClientProducao(){ //esse @Value vai procurar sempre no profile que tiver ativo
          return S3Client.builder()
               .credentialsProvider(DefaultCredentialsProvider.builder().build()) // as credenciais vao ser passadas de forma automática pela AWS SDK, através da role dessa EC2
               //quando voce coloca uma role no seu EC2, dando permissao de acesso ao serviço S3, voce não precisa passar credentials...... Essa maquina ja vai ter o acesso.
               .build();
     }
}