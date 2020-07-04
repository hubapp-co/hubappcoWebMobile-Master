package in.co.hubapp.web;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.co.hubapp.service.MailService;

@RestController
public class MailController {
	
	@Autowired
	private MailService smtpMailSender;

	@RequestMapping("/send-mail")
	public void sendMail() throws MessagingException {
		
		smtpMailSender.send("deepak.malghan@gmail.com", "Test mail from Spring", "Howdy");
		
	}

}
