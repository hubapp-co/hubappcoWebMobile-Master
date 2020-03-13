package in.co.hubapp.model;

import java.time.LocalDate;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "event")
public class Events {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Column(name = "event_name")
	private String eventName;

	@Column(name = "event_decription")
	private String eventDecription;

	@Column(name = "event_img")
	private String eventImg;

	@Transient
	private byte[] eventImageInBytes;

	@Column(name = "event_date")
	private LocalDate eventDate;

	@Column(name = "event_time")
	private LocalDate evenTime;

	@Column(name = "members")
	private String memberName;

	@Column(name = "likes")
	private Long likes;

	/*
	 * @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	 * 
	 * @JoinColumn(name = "event_user_id", referencedColumnName = "id") private User
	 * eventUserId;
	 */

	private Long eventUserId;

	public Events() {
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
