package in.co.hubapp.mobile.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import in.co.hubapp.mobile.channel.HubGenRes;
import in.co.hubapp.mobile.channel.Login;
import in.co.hubapp.mobile.channel.Register;
import in.co.hubapp.mobile.service.UserServiceMob;
import in.co.hubapp.model.Category;
import in.co.hubapp.model.Posts;

@RestController
@RequestMapping("/api")
public class UserControllerMob {

	@Autowired
	UserServiceMob userServiceMob;

	@RequestMapping(value="/register",method = RequestMethod.POST, consumes = "application/json")
	public HubGenRes register(@RequestBody Register req) {
		HubGenRes res = userServiceMob.registerUser(req);
		return res;
	}
	
	@RequestMapping(value="/loginMob",method= RequestMethod.POST,produces="application/json")
	public HubGenRes login(@RequestBody Login req)
	{
		HubGenRes res= userServiceMob.login(req);
		return res;
	}
	
	@RequestMapping(value="/categories",method= RequestMethod.GET,produces="application/json")
	public List<Category> login()
	{
		List<Category> res= userServiceMob.getCategory();
		return res;
	}
	
	
	  @RequestMapping(value="/post",method=RequestMethod.POST,consumes
	  ={"multipart/form-data"}) public HubGenRes posts(@RequestPart("file")
	  MultipartFile file,@RequestBody Posts req) throws IOException { File
	  convertFile= new File("/home/rajesh/Desktop/"+file.getOriginalFilename());
	  convertFile.createNewFile(); FileOutputStream fos= new
	  FileOutputStream(convertFile); fos.write(file.getBytes()); fos.close();
	  String path=convertFile.getAbsolutePath();
	  
	  HubGenRes res=userServiceMob.post(req,path);
	  
	  return res; }
	 	


}
