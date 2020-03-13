package in.co.hubapp.mobile.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import in.co.hubapp.model.CategoryChild;

@Repository
public interface CategoryChildRepositoryMob extends JpaRepository<CategoryChild, Long> {
	
	@Query(value = "SELECT * FROM category_child where id = ?1", nativeQuery = true)
	List<CategoryChild> findCategoryChildById(Long id);	
	
	

}
