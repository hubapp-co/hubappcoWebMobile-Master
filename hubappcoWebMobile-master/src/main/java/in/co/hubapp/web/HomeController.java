package in.co.hubapp.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

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
    @GetMapping("/services")
    public String services() {
        return "services";
    }
    @GetMapping("/contact_us")
    public String contact() {
        return "contact_us";
    }
    @GetMapping("/user")
    public String userIndex() {
        return "user/index";
    }
    @GetMapping("/user/event")
    public String userEvent() {
        return "user/event";
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
    public String login(Model model)  { 
		return "login"; 
	}
	 
    @GetMapping("/access-denied")
    public String accessDenied() {
        return "/error/access-denied";
    }
    @GetMapping( "/error")
    public String error() {
        return "/error/access-denied";
    }

}