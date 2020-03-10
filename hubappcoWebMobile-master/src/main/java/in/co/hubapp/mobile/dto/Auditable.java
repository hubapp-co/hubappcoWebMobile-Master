package in.co.hubapp.mobile.dto;

import java.util.Date;

import javax.persistence.Embeddable;

@Embeddable
public class Auditable {
	
	private Date createdOn;
	private Date updatedOn;
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public Date getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}
	
	
	
	

}
