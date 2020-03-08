package in.co.hubapp.mobile.channel;

import in.co.hubapp.model.User;

public class HubGenRes {
	
	String status;
	Long secureKey;
	String message;
	User user;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getSecureKey() {
		return secureKey;
	}
	public void setSecureKey(Long secureKey) {
		this.secureKey = secureKey;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
	
	

}
