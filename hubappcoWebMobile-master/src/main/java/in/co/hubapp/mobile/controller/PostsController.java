package in.co.hubapp.mobile.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import in.co.hubapp.mobile.channel.HubGenReq;
import in.co.hubapp.mobile.channel.HubGenRes;
import in.co.hubapp.mobile.channel.PostReq;
import in.co.hubapp.mobile.channel.PostRes;
import in.co.hubapp.mobile.service.PostServiceMob;
import in.co.hubapp.mobile.types.RequestType;

@RestController
@RequestMapping(RequestType.API + RequestType.POST)
public class PostsController {

	@Autowired
	PostServiceMob postServiceMob;

	@RequestMapping(value = RequestType.USER_POST, method = RequestMethod.POST, consumes = { "application/json",
			"multipart/form-data" })
	public HubGenRes posts(@RequestBody PostReq req) throws IOException {

		HubGenRes res = postServiceMob.post(req);

		return res;
	}

	@RequestMapping(value = RequestType.USER_POST, method = RequestMethod.GET, consumes = { "application/json",
			"multipart/form-data" })
	public List<PostRes> getPosts(@RequestBody HubGenReq req) throws IOException {

		List<PostRes> res = postServiceMob.getPostByUserId(req);

		return res;
	}

}
