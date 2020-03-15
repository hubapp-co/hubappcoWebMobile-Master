package in.co.hubapp.mobile.channel;

import java.util.List;

import in.co.hubapp.model.User;

public class HubGenRes {

	String status;
	Long secureKey;
	String message;
	User user;
	DocumentDetails doc;
	List<PostRes> posts;
	List<EventRes> events;

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

	public DocumentDetails getDoc() {
		return doc;
	}

	public void setDoc(DocumentDetails doc) {
		this.doc = doc;
	}

	public List<PostRes> getPosts() {
		return posts;
	}

	public void setPosts(List<PostRes> posts) {
		this.posts = posts;
	}

	public List<EventRes> getEvents() {
		return events;
	}

	public void setEvents(List<EventRes> events) {
		this.events = events;
	}

	@Override
	public String toString() {
		return "HubGenRes [status=" + status + ", secureKey=" + secureKey + ", message=" + message + ", user=" + user
				+ ", doc=" + doc + ", posts=" + posts + ", events=" + events + "]";
	}

}
