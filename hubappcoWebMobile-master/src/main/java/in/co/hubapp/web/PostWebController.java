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

import in.co.hubapp.mobile.channel.HubGenRes;
import in.co.hubapp.mobile.channel.PostReq;
import in.co.hubapp.mobile.service.PostServiceMob;

@Controller
public class PostWebController {
	
	@Autowired
	PostServiceMob postService;

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
	
	//@RequestMapping(path = "/user/postweb", method = RequestMethod.POST, consumes= {"multipart/form-data"})
	@PostMapping(value="/user/postweb",consumes = {"multipart/form-data"})
	public HubGenRes createUserposts(@Valid PostReq req, BindingResult result, Model model)throws IOException {
		
		if (result.hasErrors()) {
           System.out.println("result error");
        }
		
		System.out.println("Submit TTESTTTTTTT" +req.toString());
		HubGenRes res = postService.post(req);
		return res;
	}
	
	@DeleteMapping("/user/postweb/{id}")
	public Model deleteUserposts(Model model) {
		return model;
	}
}
