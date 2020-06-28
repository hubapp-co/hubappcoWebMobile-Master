package in.co.hubapp.model;

public class OTPSystem {

	private String mobileNumber;
	private String otp;
	private long exiryTime;
	
	
	public long getExiryTime() {
		return exiryTime;
	}
	public void setExiryTime(long exiryTime) {
		this.exiryTime = exiryTime;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	
	
}
