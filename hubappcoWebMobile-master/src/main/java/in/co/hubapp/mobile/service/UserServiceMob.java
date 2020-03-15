package in.co.hubapp.mobile.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import in.co.hubapp.mobile.channel.CategoryChildList;
import in.co.hubapp.mobile.channel.CategoryList;
import in.co.hubapp.mobile.channel.CategorySubChildList;
import in.co.hubapp.mobile.channel.HubGenRes;
import in.co.hubapp.mobile.channel.Login;
import in.co.hubapp.mobile.channel.Register;
import in.co.hubapp.model.Category;

public interface UserServiceMob {
	
	public HubGenRes registerUser(Register req);
	
	public HubGenRes login(Login req);
	
	public List<Category> getCategory();	
	
	public List<CategoryList> getCategoryList();
	
	public List<CategoryChildList> getCategoryChildList(Long id);
	
	public List<CategorySubChildList> getCategorySubChildList(Long id);
	
	public HubGenRes uploadDocument(MultipartFile file);
	
	
	
}
