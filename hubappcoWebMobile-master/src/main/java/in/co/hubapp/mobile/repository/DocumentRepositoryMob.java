package in.co.hubapp.mobile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import in.co.hubapp.mobile.util.Document;

@Repository
public interface DocumentRepositoryMob extends JpaRepository<Document, Long> {

	@Query(value = "SELECT * FROM document where doc_id= ?1", nativeQuery = true)
	Document findDocumentById(Long id);

}
