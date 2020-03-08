package in.co.hubapp.mobile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import in.co.hubapp.mobile.channel.HubGenRes;
import in.co.hubapp.mobile.channel.Login;
import in.co.hubapp.mobile.channel.Register;
import in.co.hubapp.mobile.service.UserServiceMob;

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

}
