package in.co.hubapp.web;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.sun.javafx.geom.PickRay;

import in.co.hubapp.mobile.channel.DocumentDetails;
import in.co.hubapp.mobile.service.PostServiceMob;
import in.co.hubapp.mobile.service.UserServiceMob;
import in.co.hubapp.model.Category;
import in.co.hubapp.model.OtpMail;
import in.co.hubapp.model.Posts;
import in.co.hubapp.model.ProfileImageModel;
import in.co.hubapp.model.User;
import in.co.hubapp.model.UserProfile;
import in.co.hubapp.repository.UserProfileRepository;
import in.co.hubapp.service.MailService;
import in.co.hubapp.service.UserProfileService;
import in.co.hubapp.service.UserService;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Controller
public class HomeController {

	@Autowired
	private UserProfileService userProfileService;

	@Autowired
	private UserProfileRepository userProfileRepository;

	@Autowired
	private MailService mailService;

	@Autowired
	private UserServiceMob userServiceMob;

	@Autowired
	PostServiceMob postService;

	@Autowired
	private UserService userService;

	@GetMapping("/")
	public String root() {
		return "index";
	}

	@GetMapping("/home")
	public String home() {
		return "index";
	}

	@GetMapping("/about_us")
	public String about() {
		return "about_us";
	}

	@GetMapping("/registration_success")
	public String registration_success() {

		return "registration_success";
	}

	@PostMapping("/otp_verify")
	public String otp_verify(@ModelAttribute OtpMail otpMail, Model model) {

		ResponseEntity<Object> result = mailService.mailOtpVerify(otpMail);

		System.out.println(result);

		if (result.getBody().equals("OTP is verified successfully")) {
			return "login";
		} else if (result.getBody().equals("Invalid OTP")) {
			model.addAttribute("otp_status", "Invalid OTP");
			return "registration_success";
		} else if (result.getBody().equals("Please provide OTP")) {
			model.addAttribute("otp_status", "Please provide OTP");
			return "registration_success";
		}
		return "registration_success";

	}

	@GetMapping("/services")
	public String services() {
		return "services";
	}

	@GetMapping("/subscription")
	public String subscription() {
		return "subscription";
	}

	@GetMapping("/sapling_foundation")
	public String sapling_foundation() {
		return "sapling_foundation";
	}

	@GetMapping("/contact_us")
	public String contact() {
		return "contact_us";
	}

	/*
	 * @GetMapping("/user") public String userIndex() { return "user/index"; }
	 */

	@GetMapping("/user")
	public String userIndex(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByUserName(auth.getName());

		System.out.println(user.getEmail() + user.getId() + " XXXXXXXXXX");
		List<Posts> posts = postService.getPostsByUserId(user.getId());
		List<Category> categories = userServiceMob.getCategory();
		model.addAttribute("categories", categories);
		if (posts != null) {
			model.addAttribute("posts", posts);
		} else {
			model.addAttribute("posts", "No Posts available");
		}
		return "user/index";
	}

	@GetMapping("/user/event")
	public String userEvent() {
		return "user/event";
	}

	@GetMapping("/user/explore")
	public String userExplore() {
		return "user/explore";
	}

	@GetMapping("/user/addposts")
	public String addPosts(Model model) {
		List<Category> categories = userServiceMob.getCategory();
		model.addAttribute("categories", categories);
		return "user/addposts";
	}

	@RequestMapping(value = "/user/edit-profile", method = RequestMethod.GET)
	public String editProfile(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByUserName(auth.getName());

		UserProfile userProfile = new UserProfile();
		userProfile.setUser(user);

		Optional<UserProfile> userProfileByUser = userProfileRepository.getByUser(user);

		if (userProfileByUser.isPresent()) {
			model.addAttribute("userProfile", userProfileByUser);
			model.addAttribute("user", user);
			System.out.println("IsPresent!!!!!!!!!"+userProfileByUser);
		} else {
			
			
			System.out.println("!!!!!!!!!"+userProfileByUser);
			userProfileRepository.save(userProfile);
			Optional<UserProfile> userProfileEdit = userProfileService.getUserProfileById(userProfile.getUserId());
			
			model.addAttribute("userProfile", userProfileEdit);
			model.addAttribute("user", user);
			return "user/edit-profile";
		}
		return "user/edit-profile";
	}

	@RequestMapping(value = "/user/edituserProfile", method = RequestMethod.POST, consumes = { "multipart/form-data" })
	public String updateUserProfile(@ModelAttribute UserProfile req, MultipartFile profilepic,MultipartFile profilebanner, Model model,
			BindingResult result) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByUserName(auth.getName());
		req.setUser(user);
		System.out.println(user.getFirstName());
		if (userProfileRepository.findByUser(user) != null){
            UserProfile existingUserPRofile = userProfileRepository.findByUser(user);
            System.out.println(req.getAbout());
            existingUserPRofile.setAbout(req.getAbout());
            existingUserPRofile.setFromCity(req.getFromCity());
            existingUserPRofile.setLivesIn(req.getLivesIn());
            existingUserPRofile.setPhone(req.getPhone());
            existingUserPRofile.setProfession(req.getProfession());
            existingUserPRofile.setWorksFor(req.getWorksFor());
            
            ProfileImageModel pim = userProfileService.uploadProfileBanner(profilebanner, user.getFirstName());
    			if(pim!=null) {
    		DocumentDetails doc = new DocumentDetails();
    		doc.setFileName(pim.getDoc().getFileName());
    		doc.setFilePath(pim.getDoc().getFilePath());
    		doc.setDownloadUri(pim.getDoc().getDownloadUri());
    		existingUserPRofile.setBannerImage(doc.getFileName());
    			System.out.println(doc.getFileName());
    			}
    			
    		ProfileImageModel pimPic = userProfileService.uploadProfilePic(profilepic, user.getFirstName());
    		if(pimPic!=null) {
    		DocumentDetails docProfile = new DocumentDetails();
    		docProfile.setFileName(pimPic.getDoc().getFileName());
    		docProfile.setFilePath(pimPic.getDoc().getFilePath());
    		docProfile.setDownloadUri(pimPic.getDoc().getDownloadUri());
            existingUserPRofile.setProfilePic(docProfile.getFileName());
            System.out.println(docProfile.getFileName());
    		}

            UserProfile updatedProfile = userProfileRepository.save(existingUserPRofile);
            
            System.out.println("updatedProfile.getBannerImage : "+updatedProfile.getBannerImage());

            model.addAttribute("userProfile", updatedProfile);
    		return "user/edit-profile";
    		
        }else{
            return null;
        }
		
	}

	@GetMapping("/user/categories")
	public String categories() {
		return "user/categories";
	}

	@GetMapping("/user/posts")
	public String userPosts() {
		return "user/posts";
	}

	@GetMapping("/user/profile")
	public String userProfile() {
		return "user/profile";
	}

	@GetMapping("/login")
	public String login(Model model) {
		return "login";
	}

	@GetMapping("error/access-denied")
	public String accessDenied() {
		return "/error/access-denied";
	}

	@GetMapping("/error")
	public String error() {
		return "/error/access-denied";
	}

}