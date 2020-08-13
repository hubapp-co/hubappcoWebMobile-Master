package in.co.hubapp.service;

import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import in.co.hubapp.model.OtpMail;
import in.co.hubapp.model.OtpSystemMail;
import in.co.hubapp.web.HomeController;

@Component
public class MailService {
	
	private static final Logger logger = LoggerFactory.getLogger(MailService.class);
	
	
	private Map<String, OtpSystemMail> otpDataMail = new HashMap<>();

	@Autowired
	private JavaMailSender javaMailSender;

	public void send(String to, String subject, String body) throws MessagingException {
		OtpSystemMail otpSystemMail = new OtpSystemMail();
		otpSystemMail.setMailId(to);
		otpSystemMail.setOtp(String.valueOf(((int) (Math.random() * (10000 - 1000))) + 1000));
		otpSystemMail.setExiryTime(System.currentTimeMillis() + 30000);
		otpDataMail.put(otpSystemMail.getOtp(), otpSystemMail);

		body = body + otpSystemMail.getOtp() + ". The OTP Expires in 30 seconds";
		
		System.out.println(body);

		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper;

		helper = new MimeMessageHelper(message, true); // true indicates
													// multipart message
		helper.setSubject(subject);
		helper.setFrom("info@hubapp.co.in");
		helper.setTo(to);
		helper.setText(body, true); // true indicates html
		// continue using helper object for more functionalities like adding
		// attachments, etc.

	logger.info("$$$$$$$$$   "+message.toString());
		
	javaMailSender.send(message);

	}
	
	public ResponseEntity<Object> mailOtpVerify(OtpMail otp) {
		
		if (otp.getOtp() == null || otp.getOtp().trim().length() <= 0) {
			return new ResponseEntity<>("Please provide OTP", HttpStatus.BAD_REQUEST);
		}
		OtpSystemMail otpSystemMail;
		otpSystemMail = otpDataMail.get(otp.getOtp());
		
		if (otpSystemMail != null && otpSystemMail.getOtp() != null) {
			if (otp.getOtp().equals(otpSystemMail.getOtp())) {
					otpDataMail.remove(otp.getOtp());
			return new ResponseEntity<>("OTP is verified successfully", HttpStatus.OK);
			}
		}
		return new ResponseEntity<>("Invalid OTP", HttpStatus.BAD_REQUEST);
	}
}
