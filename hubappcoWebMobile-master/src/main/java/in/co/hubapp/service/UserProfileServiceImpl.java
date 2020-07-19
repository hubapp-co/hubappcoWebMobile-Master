package in.co.hubapp.service;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import in.co.hubapp.mobile.channel.DocumentDetails;
import in.co.hubapp.mobile.channel.HubGenRes;
import in.co.hubapp.mobile.repository.DocumentRepositoryMob;
import in.co.hubapp.mobile.util.Document;
import in.co.hubapp.model.ProfileImageModel;
import in.co.hubapp.model.UserProfile;
import in.co.hubapp.repository.UserProfileRepository;

@Service
public class UserProfileServiceImpl implements UserProfileService {

	@Autowired
	private UserProfileRepository userProfileRepository;
	
	@Autowired
	DocumentRepositoryMob documentRepositoryMob;


	@Override
	public Optional<UserProfile> getUserProfileByUser(Long userId) {
		
		Optional<UserProfile> userProfile = userProfileRepository.findByUser(userId);
		return userProfile;
	}


	@Override
	public Optional<UserProfile> getUserProfileById(Long userId) {
		Optional<UserProfile> userProfile = userProfileRepository.findById(userId);
		return userProfile;
	}


	@Override
	public ProfileImageModel uploadProfileBanner(MultipartFile profilebanner, String firstName) {
		ProfileImageModel pim = new ProfileImageModel();
		Document doc = new Document();
		DocumentDetails docRes = new DocumentDetails();
		LocalDateTime current = LocalDateTime.now();

		try {
					
			String dir = System.getProperty("user.dir") + "/uploads/";
			
					File convertFile = new File(dir + profilebanner.getOriginalFilename());
					convertFile.createNewFile();
					
					FileOutputStream fos = new FileOutputStream(convertFile);
					fos.write(profilebanner.getBytes());
					fos.close();
					
					String filePath = convertFile.getAbsolutePath();
					String fileName = convertFile.getName();
					
					String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
							.path("/uploads/")
							.path(fileName)
							.toUriString();
					System.out.println("UserProfileServiceImplDocument : "+fileDownloadUri);
					try {
						doc.setFilePath(filePath);
						doc.setFileName(fileName);
						doc.setDownloadUri(fileDownloadUri);
						Document docDetails = documentRepositoryMob.save(doc);
						System.out.println("UserProfileServiceImplDocument"+docDetails.getDownloadUri());
						if (docDetails != null) {
							docRes.setDocId(docDetails.getId());
							docRes.setFileName(doc.getFileName());
							docRes.setFilePath(doc.getFilePath());
							docRes.setDownloadUri(doc.getDownloadUri());
							pim.setDoc(docRes);
							pim.setStatus("Success");
							pim.setMessage("Document uploaded Successfuly");
							
							
							System.out.println(pim.getDoc().getFileName());
							
							return pim;
						}

					} catch (Exception e) {

						pim.setStatus("Failure");
						pim.setMessage("Error in uploading document");
						return pim;
					}
			
		} catch (Exception e) {
			pim.setStatus("Failure");
			pim.setMessage("Error in uploading document");
			return pim;

		}

		return pim;
	}


	@Override
	public ProfileImageModel uploadProfilePic(MultipartFile profilepic, String firstName) {
		ProfileImageModel pim = new ProfileImageModel();
		Document doc = new Document();
		DocumentDetails docRes = new DocumentDetails();
		LocalDateTime current = LocalDateTime.now();

		try {
			
			String dir = System.getProperty("user.dir") + "/uploads/";
					File convertFile = new File(dir + profilepic.getOriginalFilename());
					convertFile.createNewFile();
					
					FileOutputStream fos = new FileOutputStream(convertFile);
					fos.write(profilepic.getBytes());
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
							pim.setStatus("Success");
							pim.setMessage("Document uploaded Successfuly");
							pim.setDoc(docRes);
							return pim;
						}

					} catch (Exception e) {

						pim.setStatus("Failure");
						pim.setMessage("Error in uploading document");
						return pim;
					}  
		} catch (Exception e) {
			pim.setStatus("Failure");
			pim.setMessage("Error in uploading document");
			return pim;

		}

		return pim;
	}




}
