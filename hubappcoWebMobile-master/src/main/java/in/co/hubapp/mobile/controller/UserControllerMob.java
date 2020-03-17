package in.co.hubapp.mobile.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import in.co.hubapp.mobile.channel.CategoryChildList;
import in.co.hubapp.mobile.channel.CategoryList;
import in.co.hubapp.mobile.channel.CategorySubChildList;
import in.co.hubapp.mobile.channel.HubGenRes;
import in.co.hubapp.mobile.channel.Login;
import in.co.hubapp.mobile.channel.Register;
import in.co.hubapp.mobile.service.UserServiceMob;
import in.co.hubapp.mobile.types.RequestType;
import in.co.hubapp.model.Category;

@RestController
@RequestMapping(RequestType.API + RequestType.USER)
public class UserControllerMob {

	@Autowired
	UserServiceMob userServiceMob;

	@RequestMapping(value = RequestType.REGISTER, method = RequestMethod.POST, consumes = "application/json")
	public HubGenRes register(@RequestBody Register req) {
		HubGenRes res = userServiceMob.registerUser(req);
		return res;
	}

	@RequestMapping(value = RequestType.LOGIN_MOB, method = RequestMethod.POST, produces = "application/json")
	public HubGenRes login(@RequestBody Login req) {
		HubGenRes res = userServiceMob.login(req);
		return res;
	}

	@RequestMapping(value = RequestType.CATEGORIES, method = RequestMethod.GET, produces = "application/json")
	public List<Category> login() {
		List<Category> res = userServiceMob.getCategory();
		return res;
	}

	@RequestMapping(value = RequestType.CATEGORIES_MAIN, method = RequestMethod.GET, produces = "application/json")
	public List<CategoryList> getMain() {

		List<CategoryList> res = userServiceMob.getCategoryList();

		return res;
	}

	@RequestMapping(path = RequestType.CATEGORIES_CHILD
			+ "/{id}", method = RequestMethod.GET, produces = "application/json")
	public List<CategoryChildList> getChildById(@PathVariable("id") Long childId) {

		List<CategoryChildList> res = userServiceMob.getCategoryChildList(childId);

		return res;
	}

	@RequestMapping(path = RequestType.CATEGORIES_SUB_CHILD
			+ "/{id}", method = RequestMethod.GET, produces = "application/json")
	public List<CategorySubChildList> getsubChildById(@PathVariable("id") Long childId) {

		List<CategorySubChildList> res = userServiceMob.getCategorySubChildList(childId);

		return res;
	}

	@RequestMapping(value = RequestType.UPDATE_PROFILE, method = RequestMethod.POST, consumes = "application/json")
	public HubGenRes update_profile(@RequestBody Register req) {
		HubGenRes res = userServiceMob.registerUser(req);
		return res;
	}

	@RequestMapping(value = RequestType.DOC_UPLOAD, method = RequestMethod.POST, consumes = { "multipart/form-data" })
	public HubGenRes docUpload(@RequestPart("file") MultipartFile file) throws IOException {

		HubGenRes res = userServiceMob.uploadDocument(file);

		return res;
	}
}
