package in.co.hubapp.mobile.service;

import java.io.FileNotFoundException;
import java.util.List;

import in.co.hubapp.mobile.channel.CategoryChildList;
import in.co.hubapp.mobile.channel.CategoryList;
import in.co.hubapp.mobile.channel.CategorySubChildList;
import in.co.hubapp.mobile.channel.EventRes;
import in.co.hubapp.mobile.channel.HubGenReq;
import in.co.hubapp.mobile.channel.HubGenRes;
import in.co.hubapp.mobile.channel.Login;
import in.co.hubapp.mobile.channel.PostRes;
import in.co.hubapp.mobile.channel.Register;
import in.co.hubapp.model.Category;
import in.co.hubapp.model.Events;
import in.co.hubapp.model.Posts;

public interface UserServiceMob {
	
	public HubGenRes registerUser(Register req);
	
	public HubGenRes login(Login req);
	
	public List<Category> getCategory();
	
	public HubGenRes post(Posts post);
	
	public List<PostRes> getPostByUserId(HubGenReq req) throws FileNotFoundException;
	
	public List<CategoryList> getCategoryList();
	
	public List<CategoryChildList> getCategoryChildList(Long id);
	
	public List<CategorySubChildList> getCategorySubChildList(Long id);
	
	public HubGenRes postEvents(Events req);
	
	public List<EventRes> getEventByUserId(HubGenReq req) throws FileNotFoundException;
	
	
}
