package in.co.hubapp.web;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.loader.plan.exec.process.spi.ReturnReader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/*import com.twilio.sdk.Twilio;
import com.twilio.sdk.resource.api.v2010.account.Message;
import com.twilio.sdk.type.PhoneNumber;*/

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import in.co.hubapp.model.OTPSystem;

@Controller
public class OTPSystemController {

	private Map<String,OTPSystem> otpData = new HashMap<>();
	
	private final static String AccountId= "AC820a496314d4ff7660ad51ee46d50475";
	private final static String AuthId = "18885b16f88f40428f859933c63ee1b6";
	
	static {
		Twilio.init(AccountId, AuthId);
	}
	
	@RequestMapping(value="/mobilenumbers/{mobilenumber}/otp", method=RequestMethod.POST)
	public ResponseEntity<Object> sendOTP(@PathVariable("mobilenumber") String mobilenumber){
		OTPSystem otpSystem = new OTPSystem();
		otpSystem.setMobileNumber(mobilenumber);
		otpSystem.setOtp(String.valueOf(((int)(Math.random()*(10000-1000)))+1000));
		otpSystem.setExiryTime(System.currentTimeMillis()+20000);
		
		otpData.put(mobilenumber, otpSystem);
		/*Message.creator(new PhoneNumber("to-phone-number"), new PhoneNumber("from-phone-number"),
				"Your OTP is " + otpSystem.getOtp()).create();*/
		Message.creator(new PhoneNumber(mobilenumber), new PhoneNumber("+15614625192"),
				"Hubapp Registration. Your OTP is " + otpSystem.getOtp()).create();
		return new ResponseEntity<>("OTP is send successfully", HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/mobilenumbers/{mobilenumber}/otp", method = RequestMethod.PUT)
	public ResponseEntity<Object> verifyOTP(@PathVariable("mobilenumber") String mobilenumber, @RequestBody OTPSystem requestBodyOTPSystem) {
		
		if(requestBodyOTPSystem.getOtp()==null || requestBodyOTPSystem.getOtp().trim().length()<=0) {
			return new ResponseEntity<>("Please provide OTP", HttpStatus.BAD_REQUEST);
		}
		
		if(otpData.containsKey(mobilenumber)) {
			OTPSystem otpSystem = otpData.get(mobilenumber);
			if(otpSystem!=null) {
				if(otpSystem.getExiryTime()>=System.currentTimeMillis()) {
					if(otpSystem.getOtp().equals(requestBodyOTPSystem.getOtp())) {
						otpData.remove(mobilenumber);
						return new ResponseEntity<>("OTP is verified successfully", HttpStatus.OK);
					}
					return new ResponseEntity<>("Invalid OTP", HttpStatus.BAD_REQUEST);
				}
				return new ResponseEntity<>("OTP is expired...", HttpStatus.BAD_REQUEST);	
			}
			return new ResponseEntity<>("Something went wrong...", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Mobile number not found", HttpStatus.NOT_FOUND);
		
	}
	
	
}
