package in.co.hubapp.mobile.channel;

public class EventReq {

	private Long id;

	private String eventName;

	private String eventDecription;

	private Long eventImgId;

	private String eventDate;

	private String evenTime;

	private String memberName;

	private Long likes;

	private Long eventUserId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getEventDecription() {
		return eventDecription;
	}

	public void setEventDecription(String eventDecription) {
		this.eventDecription = eventDecription;
	}

	public Long getEventImgId() {
		return eventImgId;
	}

	public void setEventImgId(Long eventImgId) {
		this.eventImgId = eventImgId;
	}

	public String getEventDate() {
		return eventDate;
	}

	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}

	public String getEvenTime() {
		return evenTime;
	}

	public void setEvenTime(String evenTime) {
		this.evenTime = evenTime;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public Long getLikes() {
		return likes;
	}

	public void setLikes(Long likes) {
		this.likes = likes;
	}

	public Long getEventUserId() {
		return eventUserId;
	}

	public void setEventUserId(Long eventUserId) {
		this.eventUserId = eventUserId;
	}

	@Override
	public String toString() {
		return "EventReq [id=" + id + ", eventName=" + eventName + ", eventDecription=" + eventDecription
				+ ", eventImgId=" + eventImgId + ", eventDate=" + eventDate + ", evenTime=" + evenTime + ", memberName="
				+ memberName + ", likes=" + likes + ", eventUserId=" + eventUserId + "]";
	}

}
