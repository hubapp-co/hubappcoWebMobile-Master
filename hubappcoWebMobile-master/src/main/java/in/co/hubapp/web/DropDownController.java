package in.co.hubapp.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import in.co.hubapp.mobile.channel.CategoryChildList;
import in.co.hubapp.mobile.channel.CategorySubChildList;
import in.co.hubapp.mobile.service.UserServiceMob;
import in.co.hubapp.mobile.types.RequestType;
import in.co.hubapp.model.User;
import in.co.hubapp.service.UserService;


@RestController
public class DropDownController {
	
	
	
	@Autowired
	private UserService userService;
	
	
	@Autowired
	private UserServiceMob userServiceMob;

	@CrossOrigin(origins = RequestType.DOMAIN)
	@RequestMapping(path = "/user/childCategory/{id}", method = RequestMethod.GET, produces = "application/json")
	public List<CategoryChildList> getChildById(@PathVariable("id") Long childId) {

		List<CategoryChildList> res = userServiceMob.getCategoryChildList(childId);
		return res;
	}
	
	@CrossOrigin(origins = RequestType.DOMAIN)
	@RequestMapping(path ="/user/childSubCategory/{id}", method = RequestMethod.GET, produces = "application/json")
	public List<CategorySubChildList> getsubChildById(@PathVariable("id") Long childId) {

		List<CategorySubChildList> res = userServiceMob.getCategorySubChildList(childId);
		return res;
	}
	
	@CrossOrigin(origins = RequestType.DOMAIN)
	@GetMapping("/user/userId_name/{username}")
	public User userId(@PathVariable("id") String username) {
	    	
	    User user = userService.findByEmail(username);
	    System.out.println("userId_nameeeeeeeeeeeeeee"+username);
	    return user;
	}
}
