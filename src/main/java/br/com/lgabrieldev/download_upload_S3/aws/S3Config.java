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

     @Profile({"dockerizado"})
     @Bean
     public S3Client generateS3Client(  @Value("${aws_access_key}") String accessKey, //o spring so vai procurar esses cara se o profile 'dev' tiver ativo
                                                            @Value("${aws_secret_access_key}") String secretAcessKey,
                                                            @Value("${aws_region}") String region){
          return S3Client.builder()
               .region(Region.of(region))
               .credentialsProvider(
                    StaticCredentialsProvider.create(
                         AwsBasicCredentials.create(accessKey, secretAcessKey)
                    )
               )
               .build();
     }

     @Bean
     public S3Client generateS3ClientProducao(){ //esse @Value vai procurar sempre no profile que tiver ativo
          return S3Client.builder()
               .credentialsProvider(DefaultCredentialsProvider.builder().build()) // as credenciais vao ser passadas de forma automática pela AWS SDK, através da role dessa EC2
               //quando voce coloca uma role no seu EC2, dando permissao de acesso ao serviço S3, voce não precisa passar credentials...... Essa maquina ja vai ter o acesso.
               .build();
     }
}