package in.co.hubapp.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.validator.constraints.UniqueElements;


@Entity
public class UserProfile implements Serializable {
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String bannerImage;
	private String profilePic;
	
	private String phone;
	private String about;
	
	private String livesIn;
	private String fromCity;
	private String profession;
	private String worksFor;
	
	@OneToOne(cascade = CascadeType.ALL)
	private FileUploadReponse uploadFileResponseProfile;
	
	@OneToOne(cascade = CascadeType.ALL)
	private FileUploadReponse uploadFileResponseBanner;
	
	 @OneToOne(cascade = CascadeType.ALL)
	 private User user;

	

	public Long getUserId() {
		return id;
	}

	public void setUserId(Long id) {
		this.id = id;
	}

	public String getBannerImage() {
		return bannerImage;
	}

	public void setBannerImage(String bannerImage) {
		this.bannerImage = bannerImage;
	}

	public String getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public String getLivesIn() {
		return livesIn;
	}

	public void setLivesIn(String livesIn) {
		this.livesIn = livesIn;
	}

	public String getFromCity() {
		return fromCity;
	}

	public void setFromCity(String fromCity) {
		this.fromCity = fromCity;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getWorksFor() {
		return worksFor;
	}

	public void setWorksFor(String worksFor) {
		this.worksFor = worksFor;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public FileUploadReponse getUploadFileResponseProfile() {
		return uploadFileResponseProfile;
	}

	public void setUploadFileResponseProfile(FileUploadReponse uploadFileResponseProfile) {
		this.uploadFileResponseProfile = uploadFileResponseProfile;
	}

	public FileUploadReponse getUploadFileResponseBanner() {
		return uploadFileResponseBanner;
	}

	public void setUploadFileResponseBanner(FileUploadReponse uploadFileResponseBanner) {
		this.uploadFileResponseBanner = uploadFileResponseBanner;
	}

	public UserProfile() {

    }

	public UserProfile(Long id, String bannerImage, String profilePic, String phone, String about, String livesIn,
			String fromCity, String profession, String worksFor, FileUploadReponse uploadFileResponseProfile,
			FileUploadReponse uploadFileResponseBanner, User user) {
		super();
		this.id = id;
		this.bannerImage = bannerImage;
		this.profilePic = profilePic;
		this.phone = phone;
		this.about = about;
		this.livesIn = livesIn;
		this.fromCity = fromCity;
		this.profession = profession;
		this.worksFor = worksFor;
		this.uploadFileResponseProfile = uploadFileResponseProfile;
		this.uploadFileResponseBanner = uploadFileResponseBanner;
		this.user = user;
	}

	@Override
	public String toString() {
		return "UserProfile [id=" + id + ", bannerImage=" + bannerImage + ", profilePic=" + profilePic + ", phone="
				+ phone + ", about=" + about + ", livesIn=" + livesIn + ", fromCity=" + fromCity + ", profession="
				+ profession + ", worksFor=" + worksFor + ", uploadFileResponseProfile=" + uploadFileResponseProfile
				+ ", uploadFileResponseBanner=" + uploadFileResponseBanner + ", user=" + user + "]";
	}

	


	
	

	
}
