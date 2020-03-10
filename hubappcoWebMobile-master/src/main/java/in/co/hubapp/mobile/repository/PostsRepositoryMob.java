package in.co.hubapp.mobile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import in.co.hubapp.model.Posts;
import in.co.hubapp.model.User;

@Repository
public interface PostsRepositoryMob extends JpaRepository < Posts, Long > {

	@Query(value = "SELECT * FROM user WHERE email = ?1", nativeQuery = true)
	  User findByEmailAddress(String emailAddress);
	
	
}
