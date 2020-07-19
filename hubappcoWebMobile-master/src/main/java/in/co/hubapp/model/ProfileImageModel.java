package in.co.hubapp.model;

import in.co.hubapp.mobile.channel.DocumentDetails;

public class ProfileImageModel {
	
	private DocumentDetails doc;
	private String status;
	private Long secureKey;
	private String message;

	

	public DocumentDetails getDoc() {
		return doc;
	}

	public void setDoc(DocumentDetails doc) {
		this.doc = doc;
	}

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

	@Override
	public String toString() {
		return "ProfileImageModel [doc=" + doc + ", status=" + status + ", secureKey=" + secureKey + ", message="
				+ message + "]";
	}
	
	

}
