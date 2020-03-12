package in.co.hubapp.mobile.channel;

import java.time.LocalDate;

public class HubGenReq {
	
	Long secureKey;
	
	String type;
	
	LocalDate date;
	
	public Long getSecureKey() {
		return secureKey;
	}
	public void setSecureKey(Long secureKey) {
		this.secureKey = secureKey;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	
	
	
	
}
