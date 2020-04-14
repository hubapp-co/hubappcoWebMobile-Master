package in.co.hubapp.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import in.co.hubapp.mobile.channel.CategoryChildList;
import in.co.hubapp.mobile.channel.CategorySubChildList;
import in.co.hubapp.mobile.service.UserServiceMob;
import in.co.hubapp.mobile.types.RequestType;
import in.co.hubapp.model.CategoryChild;
import in.co.hubapp.model.CategorySubChild;


@RestController
public class DropDownController {
	
	@Autowired
	private UserServiceMob userServiceMob;

	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping(path = "/user/childCategory/{id}", method = RequestMethod.GET, produces = "application/json")
	public List<CategoryChildList> getChildById(@PathVariable("id") Long childId) {

		List<CategoryChildList> res = userServiceMob.getCategoryChildList(childId);

		return res;
	}
	
	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping(path ="/user/childSubCategory/{id}", method = RequestMethod.GET, produces = "application/json")
	public List<CategorySubChildList> getsubChildById(@PathVariable("id") Long childId) {

		List<CategorySubChildList> res = userServiceMob.getCategorySubChildList(childId);

		return res;
	}
	 
}
