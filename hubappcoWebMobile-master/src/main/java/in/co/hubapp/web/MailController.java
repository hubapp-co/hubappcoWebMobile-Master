package in.co.hubapp.web;

import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import in.co.hubapp.model.OtpSystemMail;

@Controller
public class MailController {
	
	private Map<String,OtpSystemMail> otpDataMail = new HashMap<>();
	
	@Autowired
	private JavaMailSender javaMailSender;

	@RequestMapping(value="/user/send-mail", method=RequestMethod.POST)
	public void sendMail() throws MessagingException {
		
		
		String to = "melwinpinto.tri@gmail.com";
		String subject = "Hubapp Registration OTP";
		String body = "Hubapp Registration. Your OTP is ";
		
		OtpSystemMail otpSystemMail = new OtpSystemMail();
		otpSystemMail.setMailId(to);
		otpSystemMail.setOtp(String.valueOf(((int)(Math.random()*(10000-1000)))+1000));
		otpSystemMail.setExiryTime(System.currentTimeMillis()+30000);
		otpDataMail.put(to, otpSystemMail);
		
		body =body + otpSystemMail.getOtp() + ". The OTP Expires in 30 seconds";
		
		
		
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper;
		
		helper = new MimeMessageHelper(message, true); // true indicates
													   // multipart message
		helper.setSubject(subject);
		helper.setFrom("info@hubapp.co.in");
		helper.setTo(to);
		helper.setText(body, true); // true indicates html
		// continue using helper object for more functionalities like adding attachments, etc.  
		
		
		
		javaMailSender.send(message);
	}

	@RequestMapping(value = "/user/send-mail/{emailId}", method = RequestMethod.PUT)
	public ResponseEntity<Object> verifyOTP(@PathVariable("emailId") String email, @RequestBody OtpSystemMail requestBodyOTPSystem) {
		
		if(requestBodyOTPSystem.getOtp()==null || requestBodyOTPSystem.getOtp().trim().length()<=0) {
			return new ResponseEntity<>("Please provide OTP", HttpStatus.BAD_REQUEST);
		}
		
		if(otpDataMail.containsKey(email)) {
			OtpSystemMail otpSystemMail = otpDataMail.get(email);
			if(otpSystemMail!=null) {
				if(otpSystemMail.getExiryTime()>=System.currentTimeMillis()) {
					if(otpSystemMail.getOtp().equals(requestBodyOTPSystem.getOtp())) {
						otpDataMail.remove(email);
						return new ResponseEntity<>("OTP is verified successfully", HttpStatus.OK);
					}
					return new ResponseEntity<>("Invalid OTP", HttpStatus.BAD_REQUEST);
				}
				return new ResponseEntity<>("OTP is expired...", HttpStatus.BAD_REQUEST);	
			}
			return new ResponseEntity<>("Something went wrong...", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Email Id not found", HttpStatus.NOT_FOUND);
		
	}
	
}
