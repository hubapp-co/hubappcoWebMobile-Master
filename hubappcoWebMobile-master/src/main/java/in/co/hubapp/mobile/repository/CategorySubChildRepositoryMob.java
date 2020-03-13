package in.co.hubapp.mobile.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import in.co.hubapp.model.CategorySubChild;

@Repository
public interface CategorySubChildRepositoryMob extends JpaRepository<CategorySubChild, Long> {
	
	@Query(value = "SELECT * FROM category_sub_child where id = ?1", nativeQuery = true)
	List<CategorySubChild> findCategorySubChildById(Long id);	
	
	

}
