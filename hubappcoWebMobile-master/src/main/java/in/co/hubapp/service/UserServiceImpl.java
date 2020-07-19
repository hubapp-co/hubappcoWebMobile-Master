package in.co.hubapp.service;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import in.co.hubapp.model.Role;
import in.co.hubapp.model.User;
import in.co.hubapp.model.UserProfile;
import in.co.hubapp.repository.UserProfileRepository;
import in.co.hubapp.repository.UserRepository;
import in.co.hubapp.dto.UserRegistrationDto;
import in.co.hubapp.mobile.channel.DocumentDetails;
import in.co.hubapp.mobile.channel.HubGenRes;
import in.co.hubapp.mobile.repository.DocumentRepositoryMob;
import in.co.hubapp.mobile.util.Document;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	DocumentRepositoryMob documentRepositoryMob;

	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public User save(UserRegistrationDto registration) throws Exception {
		User user = new User();
		user.setFirstName(registration.getFirstName());
		user.setLastName(registration.getLastName());
		user.setEmail(registration.getEmail());
		user.setPassword(passwordEncoder.encode(registration.getPassword()));
		user.setRoles(Arrays.asList(new Role("ROLE_USER")));
		
		return userRepository.save(user);
	}

	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
				mapRolesToAuthorities(user.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

	public HubGenRes uploadDocument(MultipartFile file) {
		HubGenRes res = new HubGenRes();
		Document doc = new Document();
		DocumentDetails docRes = new DocumentDetails();
		LocalDateTime current = LocalDateTime.now();

		try {
			String dir = System.getProperty("user.dir") + "/uploads/";
			File convertFile = new File(dir + file.getOriginalFilename());
			convertFile.createNewFile();
			
			FileOutputStream fos = new FileOutputStream(convertFile);
			fos.write(file.getBytes());
			fos.close();
			
			String filePath = convertFile.getAbsolutePath();
			String fileName = convertFile.getName();
			
			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
					.path("/uploads/")
					.path(fileName)
					.toUriString();
			try {
				doc.setFilePath(filePath);
				doc.setFileName(fileName);
				doc.setDownloadUri(fileDownloadUri);
				Document docDetails = documentRepositoryMob.save(doc);
				if (docDetails != null) {
					docRes.setDocId(docDetails.getId());
					docRes.setFileName(doc.getFileName());
					docRes.setFilePath(doc.getFilePath());
					docRes.setDownloadUri(doc.getDownloadUri());
					res.setStatus("Success");
					res.setMessage("Document uploaded Successfuly");
					res.setDoc(docRes);
					return res;
				}

			} catch (Exception e) {

				res.setStatus("Failure");
				res.setMessage("Error in uploading document");
				return res;
			}
		} catch (Exception e) {
			res.setStatus("Failure");
			res.setMessage("Error in uploading document");
			return res;

		}

		return res;

	}

	@Override
	public User findUserByUserName(String name) {

		return userRepository.findByEmail(name);
	}

}