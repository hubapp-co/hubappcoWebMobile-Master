package in.co.hubapp.web;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import in.co.hubapp.mobile.channel.HubGenRes;
import in.co.hubapp.mobile.channel.PostReq;
import in.co.hubapp.mobile.service.PostServiceMob;
import in.co.hubapp.mobile.service.UserServiceMob;

@Controller
public class PostWebController {
	
	@Autowired
	PostServiceMob postService;

	@Autowired
	UserServiceMob userServiceMob;
	
	@GetMapping("/user/postweb")
	public Model getposts(Model model) {
		return model;
	}
	
	@GetMapping("/user/postweb/{id}")
	public Model getUserposts(Model model) {
		return model;
	}
	@GetMapping("/user/postweb-details")
	public Model getUserpostsDetails(Model model) {
		return model;
	}
	
	
	@DeleteMapping("/user/postweb/{id}")
	public Model deleteUserposts(Model model) {
		return model;
	}
	//@RequestMapping(path = "/user/postweb", method = RequestMethod.POST, consumes= {"multipart/form-data"})
  	@RequestMapping(value="/user/postweb",method = RequestMethod.POST,consumes = {"multipart/form-data" })
  	public String createUserposts(@ModelAttribute PostReq req,MultipartFile postImageFile,Model model, BindingResult result)throws IOException {
  		System.out.println("Submit TTESTTTTTTT" +req.toString());
  		System.out.println(postImageFile.getName()+postImageFile.getOriginalFilename());
  		HubGenRes res = postService.post(req);
  		userServiceMob.uploadDocument(postImageFile);
  		model.addAttribute("res", res);
  		return "user/index";
  	}
	
	@RequestMapping(value="/user/postwebdebug",method = RequestMethod.POST)
  	public String createUserDebugposts(@ModelAttribute PostReq req,MultipartFile postImageFile,Model model, BindingResult result)throws IOException {
  		System.out.println("Submit TTESTTTTTTT" +req.toString());
  		//HubGenRes res = postService.post(req);
  		//model.addAttribute("res", res);
  		return "user/index";
  	}
	

}
