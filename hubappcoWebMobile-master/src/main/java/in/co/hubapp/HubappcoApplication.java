package in.co.hubapp;


import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HubappcoApplication {

	public static void main(String[] args) {
		new File("user.dir","/uploads").mkdir();
		SpringApplication.run(HubappcoApplication.class, args);
		
		
	}

}
