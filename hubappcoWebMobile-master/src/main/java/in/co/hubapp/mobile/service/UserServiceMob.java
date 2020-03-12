package in.co.hubapp.mobile.service;

import java.io.FileNotFoundException;
import java.util.List;

import in.co.hubapp.mobile.channel.HubGenReq;
import in.co.hubapp.mobile.channel.HubGenRes;
import in.co.hubapp.mobile.channel.Login;
import in.co.hubapp.mobile.channel.Post;
import in.co.hubapp.mobile.channel.Register;
import in.co.hubapp.model.Category;
import in.co.hubapp.model.Posts;

public interface UserServiceMob {
	
	public HubGenRes registerUser(Register req);
	
	public HubGenRes login(Login req);
	
	public List<Category> getCategory();
	
	public HubGenRes post(Posts post);
	
	public List<Post> getPost(HubGenReq req) throws FileNotFoundException;
	
	
	
}
