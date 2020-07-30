package in.co.hubapp;


import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import in.co.hubapp.fileupload.FileStorageProperties;


@SpringBootApplication
@EnableConfigurationProperties({
	FileStorageProperties.class
})
public class HubappcoApplication {

	public static void main(String[] args) {
		new File("user.dir","/uploads").mkdir();
		SpringApplication.run(HubappcoApplication.class, args);
		
		
	}

}
