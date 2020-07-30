package in.co.hubapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.co.hubapp.model.FileUploadReponse;

public interface UploadResponseFileRepository extends JpaRepository<FileUploadReponse, Long>{


}
