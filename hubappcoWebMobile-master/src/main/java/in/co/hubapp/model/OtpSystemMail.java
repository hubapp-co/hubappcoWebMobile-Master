package in.co.hubapp.model;

public class OtpSystemMail {
	private String mailId;
	private String otp;
	private long exiryTime;
	
	
	
	public OtpSystemMail() {
	}
	public String getMailId() {
		return mailId;
	}
	public void setMailId(String mailId) {
		this.mailId = mailId;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	public long getExiryTime() {
		return exiryTime;
	}
	public void setExiryTime(long exiryTime) {
		this.exiryTime = exiryTime;
	}
	

}
