package in.co.hubapp.service;

import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import in.co.hubapp.model.ProfileImageModel;
import in.co.hubapp.model.UserProfile;

public interface UserProfileService {
	

	public Optional<UserProfile> getUserProfileByUser(Long userId);

	public Optional<UserProfile> getUserProfileById(Long userId);

	public ProfileImageModel uploadProfileBanner(MultipartFile profilebanner,String firstName);

	public ProfileImageModel uploadProfilePic(MultipartFile profilepic,String firstName);

	public UserProfile getUserProfile(Long userId);




}
