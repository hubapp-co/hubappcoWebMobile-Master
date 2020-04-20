package in.co.hubapp.web;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import in.co.hubapp.mobile.channel.HubGenRes;
import in.co.hubapp.mobile.channel.PostReq;
import in.co.hubapp.mobile.service.PostServiceMob;

@RestController
public class PostRestWebController {
	@Autowired
	private PostServiceMob postService;
	
	//@RequestMapping(path = "/user/postweb", method = RequestMethod.POST, consumes= {"multipart/form-data"})
  	@RequestMapping(value="/user/postrestweb",method = RequestMethod.POST,consumes = { "application/json",
	"multipart/form-data" })
  	public HubGenRes createUserposts(@ModelAttribute PostReq req)throws IOException {
  		System.out.println("Submit TTESTTTTTTT" +req.toString());
  		HubGenRes res = postService.post(req);
  		//model.addAttribute("res", res);
  		return res;
  	}
	
}