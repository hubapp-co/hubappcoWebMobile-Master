package in.co.hubapp.web;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import in.co.hubapp.mobile.channel.HubGenRes;
import in.co.hubapp.mobile.channel.PostReq;
import in.co.hubapp.mobile.service.PostServiceMob;

@Controller
public class PostController {
	
	@Autowired
	PostServiceMob postServiceMob;

	@GetMapping("/posts")
	public Model getposts(Model model) {
		return model;
	}
	
	@GetMapping("/posts/user")
	public Model getUserposts(Model model) {
		return model;
	}
	@GetMapping("/posts/details")
	public Model getUserpostsDetails(Model model) {
		return model;
	}
	@PostMapping(value="/posts",consumes = {"multipart/form-data" })
	public HubGenRes createUserposts(@RequestBody PostReq req)throws IOException {
		
		HubGenRes res = postServiceMob.post(req);

		return res;
	}
	
	@DeleteMapping("/posts/{id}")
	public Model deleteUserposts(Model model) {
		return model;
	}
}
