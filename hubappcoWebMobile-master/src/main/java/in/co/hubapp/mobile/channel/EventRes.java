package in.co.hubapp.mobile.channel;

import java.time.LocalDate;
import java.util.Arrays;
import in.co.hubapp.model.User;

public class EventRes {
	
	
	private Long id;

	private String eventName;

	private String eventDecription;

	private String eventImg;

	private byte[] eventImageInBytes;

	private LocalDate eventDate;

	private LocalDate evenTime;

	private String memberName;

	private Long likes;

	private Long eventUserId;

	public EventRes() {
		super();
	}

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

	public String getEventImg() {
		return eventImg;
	}

	public void setEventImg(String eventImg) {
		this.eventImg = eventImg;
	}

	public byte[] getEventImageInBytes() {
		return eventImageInBytes;
	}

	public void setEventImageInBytes(byte[] eventImageInBytes) {
		this.eventImageInBytes = eventImageInBytes;
	}

	public LocalDate getEventDate() {
		return eventDate;
	}

	public void setEventDate(LocalDate eventDate) {
		this.eventDate = eventDate;
	}

	public LocalDate getEvenTime() {
		return evenTime;
	}

	public void setEvenTime(LocalDate evenTime) {
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
		return "Events [id=" + id + ", eventName=" + eventName + ", eventDecription=" + eventDecription + ", eventImg="
				+ eventImg + ", eventImageInBytes=" + Arrays.toString(eventImageInBytes) + ", eventDate=" + eventDate
				+ ", evenTime=" + evenTime + ", memberName=" + memberName + ", likes=" + likes + ", eventUserId="
				+ eventUserId + "]";
	}



}
