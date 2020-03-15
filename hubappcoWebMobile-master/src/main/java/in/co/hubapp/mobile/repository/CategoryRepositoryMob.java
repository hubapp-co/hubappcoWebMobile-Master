package in.co.hubapp.mobile.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import in.co.hubapp.model.Category;
import in.co.hubapp.model.CategoryChild;
import in.co.hubapp.model.User;

@Repository
public interface CategoryRepositoryMob extends JpaRepository < Category, Long > {

	@Query(value = "SELECT * FROM category WHERE id = ?1", nativeQuery = true)
	Category findByCategoryId(Long id);
	
	
	@Query(value = "SELECT * FROM category", nativeQuery = true)
	List<Category> findCategory();
	
	
	
	
}
