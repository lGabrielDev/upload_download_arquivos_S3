package br.com.lgabrieldev.download_upload_S3.aws;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.lgabrieldev.download_upload_S3.models.DatabaseFile;

@Repository
public interface S3Repository extends JpaRepository<DatabaseFile, Long>{}