package in.co.hubapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.co.hubapp.model.User;
import in.co.hubapp.model.UserProfile;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long>{

	Optional<UserProfile> findByUser(Long userId);

	Optional<UserProfile> getByUser(User user);

	UserProfile findByUser(User user);






}
