package in.co.hubapp.mobile.service;

import org.springframework.data.jpa.repository.Query;

import in.co.hubapp.mobile.channel.HubGenRes;
import in.co.hubapp.mobile.channel.Login;
import in.co.hubapp.mobile.channel.Register;
import in.co.hubapp.model.User;

public interface UserServiceMob {
	
	public HubGenRes registerUser(Register req);
	
	public HubGenRes login(Login req);
	
	
	
	
}
