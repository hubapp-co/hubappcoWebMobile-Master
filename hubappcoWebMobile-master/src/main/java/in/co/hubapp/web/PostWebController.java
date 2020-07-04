package in.co.hubapp.web;

import java.io.IOException;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import in.co.hubapp.mobile.channel.DocumentDetails;
import in.co.hubapp.mobile.channel.HubGenRes;
import in.co.hubapp.mobile.channel.PostReq;
import in.co.hubapp.mobile.service.PostServiceMob;
import in.co.hubapp.mobile.util.Document;
import in.co.hubapp.service.UserService;

@Controller
public class PostWebController {
	
	private static final Logger log=LoggerFactory.getLogger(PostWebController.class);
	
	@Autowired
	PostServiceMob postService;

	@Autowired
	UserService userService;
	
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
  	@RequestMapping(value="/user/postweb",method = RequestMethod.POST,consumes = {"multipart/form-data"})
  	public String createUserposts(@ModelAttribute PostReq req,MultipartFile postImageFile,Model model, BindingResult result)throws IOException {
  		log.info("Submit TTESTTTTTTT" +req.toString());
  		log.info(postImageFile.getName()+postImageFile.getOriginalFilename());
  		
  		HubGenRes docData = userService.uploadDocument(postImageFile);
  		DocumentDetails doc = new DocumentDetails();
  		doc.setFileName(docData.getDoc().getFileName());
  		doc.setFileName(docData.getDoc().getFilePath());
  		doc.setDocId(docData.getDoc().getDocId());
  		
  		HubGenRes res = new HubGenRes();
  		res.setDoc(doc);
  		postService.post(req);
  		model.addAttribute("res", res);
  		return "user/index";
  	}
	
	
	

}
