package in.co.hubapp.mobile.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import in.co.hubapp.mobile.channel.CategoryChildList;
import in.co.hubapp.mobile.channel.CategoryList;
import in.co.hubapp.mobile.channel.CategorySubChildList;
import in.co.hubapp.mobile.channel.EventRes;
import in.co.hubapp.mobile.channel.HubGenReq;
import in.co.hubapp.mobile.channel.HubGenRes;
import in.co.hubapp.mobile.channel.Login;
import in.co.hubapp.mobile.channel.PostRes;
import in.co.hubapp.mobile.channel.Register;
import in.co.hubapp.mobile.repository.CategoryRepositoryMob;
import in.co.hubapp.mobile.service.UserServiceMob;
import in.co.hubapp.model.Category;
import in.co.hubapp.model.Events;
import in.co.hubapp.model.Posts;

@RestController
@RequestMapping("/api")
public class UserControllerMob {

	@Autowired
	UserServiceMob userServiceMob;

	@Autowired
	CategoryRepositoryMob categoryRepositoryMob;

	@RequestMapping(value = "/register", method = RequestMethod.POST, consumes = "application/json")
	public HubGenRes register(@RequestBody Register req) {
		HubGenRes res = userServiceMob.registerUser(req);
		return res;
	}

	@RequestMapping(value = "/loginMob", method = RequestMethod.POST, produces = "application/json")
	public HubGenRes login(@RequestBody Login req) {
		HubGenRes res = userServiceMob.login(req);
		return res;
	}

	@RequestMapping(value = "/categories", method = RequestMethod.GET, produces = "application/json")
	public List<Category> login() {
		List<Category> res = userServiceMob.getCategory();
		return res;
	}

	/*
	 * @RequestMapping(value = "/post", method = RequestMethod.POST, consumes = {
	 * "multipart/form-data" }) public HubGenRes posts(@RequestPart("file")
	 * MultipartFile file, @RequestBody Posts req) throws IOException { File
	 * convertFile = new File("/home/rakesh/Desktop/" + file.getOriginalFilename());
	 * convertFile.createNewFile(); FileOutputStream fos = new
	 * FileOutputStream(convertFile); fos.write(file.getBytes()); fos.close();
	 * String path = convertFile.getAbsolutePath();
	 * 
	 * HubGenRes res = userServiceMob.post(req, path);
	 * 
	 * return res; }
	 */
	@RequestMapping(value = "/post", method = RequestMethod.POST, consumes = { "application/json",
			"multipart/form-data" })
	public HubGenRes posts(@RequestBody Posts req) throws IOException {

		HubGenRes res = userServiceMob.post(req);

		return res;
	}

	@RequestMapping(value = "/post", method = RequestMethod.GET, consumes = { "application/json",
			"multipart/form-data" })
	public List<PostRes> getPosts(@RequestBody HubGenReq req) throws IOException {

		List<PostRes> res = userServiceMob.getPostByUserId(req);

		return res;
	}

	@RequestMapping(value = "/categories/main", method = RequestMethod.GET, produces = "application/json")
	public List<CategoryList> getMain() {

		List<CategoryList> res = userServiceMob.getCategoryList();

		return res;
	}

	@RequestMapping(path = "/categories/child/{id}", method = RequestMethod.GET, produces = "application/json")
	public List<CategoryChildList> getChildById(@PathVariable("id") Long childId) {

		List<CategoryChildList> res = userServiceMob.getCategoryChildList(childId);

		return res;
	}

	@RequestMapping(path = "/categories/subchild/{id}", method = RequestMethod.GET, produces = "application/json")
	public List<CategorySubChildList> getsubChildById(@PathVariable("id") Long childId) {

		List<CategorySubChildList> res = userServiceMob.getCategorySubChildList(childId);

		return res;
	}

	@RequestMapping(value = "/event", method = RequestMethod.POST, consumes = { "application/json",
			"multipart/form-data" })
	public HubGenRes Events(@RequestBody Events req) {

		HubGenRes res = userServiceMob.postEvents(req);

		return res;
	}

	@RequestMapping(value = "/event", method = RequestMethod.GET, produces = { "application/json","multipart/form-data" })
	public List<EventRes> getEventsById(@RequestBody HubGenReq req) throws IOException {

		List<EventRes> res = userServiceMob.getEventByUserId(req);

		return res;
	}

}
