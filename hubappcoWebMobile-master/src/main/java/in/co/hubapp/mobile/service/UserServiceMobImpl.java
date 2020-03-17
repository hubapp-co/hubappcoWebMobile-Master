package in.co.hubapp.mobile.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import in.co.hubapp.mobile.channel.CategoryChildList;
import in.co.hubapp.mobile.channel.CategoryList;
import in.co.hubapp.mobile.channel.CategorySubChildList;
import in.co.hubapp.mobile.channel.DocumentDetails;
import in.co.hubapp.mobile.channel.HubGenRes;
import in.co.hubapp.mobile.channel.Login;
import in.co.hubapp.mobile.channel.Register;
import in.co.hubapp.mobile.repository.CategoryChildRepositoryMob;
import in.co.hubapp.mobile.repository.CategoryRepositoryMob;
import in.co.hubapp.mobile.repository.CategorySubChildRepositoryMob;
import in.co.hubapp.mobile.repository.DocumentRepositoryMob;
import in.co.hubapp.mobile.repository.UserRepositoryMob;
import in.co.hubapp.mobile.util.Document;
import in.co.hubapp.model.Category;
import in.co.hubapp.model.CategoryChild;
import in.co.hubapp.model.CategorySubChild;
import in.co.hubapp.model.User;

@Service
public class UserServiceMobImpl implements UserServiceMob {

	@Autowired
	UserRepositoryMob userRepositoryMob;

	@Autowired
	CategoryRepositoryMob categoryRepositoryMob;

	@Autowired
	CategoryChildRepositoryMob categoryChildRepositoryMob;

	@Autowired
	CategorySubChildRepositoryMob categorySubChildRepositoryMob;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	DocumentRepositoryMob documentRepositoryMob;

	public HubGenRes registerUser(Register req) {
		User user = new User();
		User user2 = new User();
		HubGenRes res = new HubGenRes();

		if (req != null) {
			if (req.getEmail() != null) {
				user.setEmail(req.getEmail());
			}
			if (req.getFirstName() != null) {
				user.setFirstName(req.getFirstName());
			}
			if (req.getLastName() != null) {
				user.setLastName(req.getLastName());
			}
			if (req.getPassword() != null) {
				try {
					user.setPassword(passwordEncoder.encode(req.getPassword()));
				} catch (Exception e) {
					res.setMessage("user Registered Unsuccessfull");
					res.setStatus("Failure");
					return res;
				}
			}
			try {
				if (req.getEmail() != null) {
					User user1 = userRepositoryMob.findByEmailAddress(req.getEmail());

					if (user1== null) {
						user2 = userRepositoryMob.save(user);
					}
				}
			} catch (Exception e) {
				res.setMessage("email id is already registered");
				res.setStatus("Success");
				return res;
			}

			if (user2 != null) {
				res.setMessage("user Registered Successfully");
				res.setStatus("Success");
				return res;
			} else {
				res.setMessage("user Registered Unsuccessfull");
				res.setStatus("Failure");
				return res;
			}

		}

		else {
			res.setMessage("Please enter the details");
			res.setStatus("Failure");
			return res;
		}

	}

	public HubGenRes login(Login req) {
		HubGenRes res = new HubGenRes();
		User userRes = new User();
		String email = null;
		String userpassword = null;
		if (req.getUsername() != null && req.getPassword() != null) {
			String pass = req.getPassword();
			User user = userRepositoryMob.findByEmailAddress(req.getUsername());
			if (user != null) {
				if (user.getEmail() != null) {
					email = user.getEmail();

				}
				if (user.getPassword() != null) {
					userpassword = user.getPassword();

				}
			}
			if (userpassword != null) {
				Boolean password = passwordEncoder.matches(pass, user.getPassword());

				if (password) {
					if (user != null) {
						res.setMessage("User is Registered");
						res.setStatus("Success");
						if (user.getFirstName() != null) {
							userRes.setFirstName(user.getFirstName());
						}
						if (user.getEmail() != null) {
							userRes.setEmail(user.getEmail());
						}
						if (user.getLastName() != null) {
							userRes.setLastName(user.getLastName());
						}
						if (user.getId() != null) {
							userRes.setId(user.getId());
						}
						res.setUser(userRes);
						return res;
					}
				} else {
					if (email != null) {
						res.setMessage("Please check the password");
						res.setStatus("Failure");
						return res;
					}
				}

			} else {

				res.setMessage("User is Not Registered please Register");
				res.setStatus("Failure");
				return res;
			}

		}
		res.setMessage("Please enter the credentials");
		res.setStatus("Failure");
		return res;

	}

	@Override
	public List<Category> getCategory() {
		List<Category> categories = categoryRepositoryMob.findAll();
		return categories;
	}

	public List<CategoryList> getCategoryList() {
		List<Category> req = new ArrayList<Category>();

		List<CategoryList> res = new ArrayList<>();

		req = (List<Category>) categoryRepositoryMob.findCategory();
		if (req != null) {
			for (Category categoryList : req) {
				CategoryList categ = new CategoryList();

				if (categoryList.getId() != null) {
					categ.setId(categoryList.getId());
				}

				if (categoryList.getCategoryName() != null) {
					categ.setCategoryName(categoryList.getCategoryName());
				}
				res.add(categ);

			}
			return res;

		}

		return res;

	}

	@Override
	public List<CategoryChildList> getCategoryChildList(Long id) {
		List<CategoryChild> req = new ArrayList<>();

		List<CategoryChildList> res = new ArrayList<>();

		req = (List<CategoryChild>) categoryChildRepositoryMob.findCategoryChildById(id);
		if (req != null) {
			for (CategoryChild categoryList : req) {
				CategoryChildList categ = new CategoryChildList();

				if (categoryList.getId() != null) {
					categ.setId(categoryList.getId());
				}

				if (categoryList.getCategoryChildName() != null) {
					categ.setCategoryChildName(categoryList.getCategoryChildName());
				}
				res.add(categ);

			}
			return res;

		}

		return res;

	}

	@Override
	public List<CategorySubChildList> getCategorySubChildList(Long id) {
		List<CategorySubChild> req = new ArrayList<>();

		List<CategorySubChildList> res = new ArrayList<>();

		req = (List<CategorySubChild>) categorySubChildRepositoryMob.findCategorySubChildById(id);
		if (req != null) {
			for (CategorySubChild categoryList : req) {
				CategorySubChildList categ = new CategorySubChildList();

				if (categoryList.getId() != null) {
					categ.setId(categoryList.getId());
				}

				if (categoryList.getCategorySubChildName() != null) {
					categ.setCategorySubChildName(categoryList.getCategorySubChildName());
				}
				res.add(categ);

			}
			return res;

		}

		return res;

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
			try {
				doc.setFilePath(filePath);
				doc.setFileName(fileName);
				Document docDetails = documentRepositoryMob.save(doc);
				if (docDetails != null) {
					docRes.setDocId(docDetails.getId());
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

	private static byte[] readFileToByteArray(File file) {
		FileInputStream fis = null;
		// Creating a byte array using the length of the file
		// file.length returns long which is cast to int
		byte[] bArray = new byte[(int) file.length()];
		try {
			fis = new FileInputStream(file);
			fis.read(bArray);
			fis.close();

		} catch (IOException ioExp) {
			ioExp.printStackTrace();
		}
		return bArray;
	}

	private static String writeByteToFile(byte[] bytes) throws IOException {
		LocalDateTime current = LocalDateTime.now();
		String filePath = null;
		final String dir = System.getProperty("user.dir") + "/uploads/";
		File convertFile = new File(dir + current);
		convertFile.createNewFile();
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(convertFile);
			fos.write(bytes);
			fos.close();
			filePath = convertFile.getAbsolutePath();

		} catch (IOException ioExp) {
			ioExp.printStackTrace();
		}
		return filePath;
	}

}
