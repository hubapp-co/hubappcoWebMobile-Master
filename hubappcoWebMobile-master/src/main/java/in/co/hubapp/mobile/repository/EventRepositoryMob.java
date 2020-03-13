package in.co.hubapp.mobile.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import in.co.hubapp.model.Events;

@Repository
public interface EventRepositoryMob extends JpaRepository<Events, Long> {
	
	@Query(value = "SELECT * FROM event where event_user_id = ?1", nativeQuery = true)
	List<Events> findEventById(Long id);	
	
	

}
