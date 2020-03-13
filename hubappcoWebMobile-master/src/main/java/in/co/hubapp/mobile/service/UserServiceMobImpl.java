package in.co.hubapp.mobile.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import in.co.hubapp.mobile.channel.CategoryChildList;
import in.co.hubapp.mobile.channel.CategoryList;
import in.co.hubapp.mobile.channel.CategorySubChildList;
import in.co.hubapp.mobile.channel.EventRes;
import in.co.hubapp.mobile.channel.HubGenReq;
import in.co.hubapp.mobile.channel.HubGenRes;
import in.co.hubapp.mobile.channel.Login;
import in.co.hubapp.mobile.channel.PostRes;
import in.co.hubapp.mobile.channel.Register;
import in.co.hubapp.mobile.repository.CategoryChildRepositoryMob;
import in.co.hubapp.mobile.repository.CategoryRepositoryMob;
import in.co.hubapp.mobile.repository.CategorySubChildRepositoryMob;
import in.co.hubapp.mobile.repository.EventRepositoryMob;
import in.co.hubapp.mobile.repository.PostsRepositoryMob;
import in.co.hubapp.mobile.repository.UserRepositoryMob;
import in.co.hubapp.model.Category;
import in.co.hubapp.model.CategoryChild;
import in.co.hubapp.model.CategorySubChild;
import in.co.hubapp.model.Events;
import in.co.hubapp.model.Posts;
import in.co.hubapp.model.User;

@Service
public class UserServiceMobImpl implements UserServiceMob {

	@Autowired
	UserRepositoryMob userRepositoryMob;

	@Autowired
	CategoryRepositoryMob categoryRepositoryMob;

	@Autowired
	PostsRepositoryMob postsRepositoryMob;

	@Autowired
	CategoryChildRepositoryMob categoryChildRepositoryMob;

	@Autowired
	CategorySubChildRepositoryMob categorySubChildRepositoryMob;

	@Autowired
	EventRepositoryMob eventRepositoryMob;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public HubGenRes registerUser(Register req) {
		User user = new User();
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

					if (user1 != null) {
						user = userRepositoryMob.save(user);
					}
				}
			} catch (Exception e) {
				res.setMessage("email id is already registered");
				res.setStatus("Success");
				return res;
			}

			if (user != null) {
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

	@Override
	public HubGenRes post(Posts post) {

		HubGenRes res = new HubGenRes();
		Posts newpost = new Posts();
		String filePath;
		if (post != null) {
			if (post.getPostTitle() != null) {
				newpost.setPostTitle(post.getPostTitle());
			}
			if (post.getPostDescription() != null) {
				newpost.setPostDescription(post.getPostDescription());
			}
			if (post.getLikes() != null) {
				newpost.setLikes(post.getLikes());
			}
			if (post.getPostUserId() != null) {
				newpost.setPostUserId(post.getPostUserId());
				;
			}
			if (post.getPostImage() != null) {

				LocalDateTime current = LocalDateTime.now();

				try {
					File convertFile = new File("/home/rajesh/Desktop/" + current);
					convertFile.createNewFile();
					FileOutputStream fos = new FileOutputStream(convertFile);
					fos.write(post.getPostImage());
					fos.close();
					filePath = convertFile.getAbsolutePath();
				} catch (IOException e) {
					res.setMessage("Error uploading image");
					res.setStatus("Failure");
					return res;
				}

				newpost.setPostImageUrl(filePath);

			}

			try {

				postsRepositoryMob.save(newpost);

				res.setMessage("post saved Successfully");
				res.setStatus("Success");
				return res;
			} catch (Exception e) {
				res.setMessage("unsuccessfull in saving post");
				res.setStatus("Failure");
				return res;
			}
		}

		res.setMessage("Enter the details");
		res.setStatus("Failure");
		return res;

	}

	public List<PostRes> getPostByUserId(HubGenReq req) throws FileNotFoundException {

		List<PostRes> res = new ArrayList<>();
		List<Posts> allPosts = new ArrayList<>();
		PostRes addPost = new PostRes();

		if (req.getSecureKey() != null) {
			try {
				allPosts = (List<Posts>) postsRepositoryMob.findByPostByUSer(req.getSecureKey());
			} catch (Exception e) {
				return res;

			}

			if (allPosts != null) {
				for (Posts posts : allPosts) {

					if (posts.getPostTitle() != null) {
						addPost.setPostTitle(posts.getPostTitle());
					}
					if (posts.getPostDescription() != null) {
						addPost.setPostDescription(posts.getPostDescription());
					}
					if (posts.getLikes() != null) {
						addPost.setLikes(posts.getLikes());
					}
					if (posts.getPostImageUrl() != null) {
						File convertFile = new File(posts.getPostImageUrl());
						byte[] bArray = readFileToByteArray(convertFile);

						addPost.setPostImage(bArray);
					}
					if (posts.getCategoryId().getId() != null) {
						addPost.setCategoryId(Integer.valueOf(String.valueOf(posts.getCategoryId().getId())));
						if (posts.getCategoryId().getCategoryName() != null) {
							addPost.setCategoryName(posts.getCategoryId().getCategoryName());
						}

					}

					if (posts.getCategoryChildId() != null) {
						addPost.setCategoryChildId(Integer.valueOf(
								String.valueOf(posts.getCategoryId().getSubCategories().get(0).getCategoryId())));
						if (posts.getCategoryChildId().getCategoryChildName() != null) {
							addPost.setCategoryChildName(posts.getCategoryChildId().getCategoryChildName());
						}
					}

					if (posts.getCategorySubChildId() != null) {
						addPost.setCategorySubChildId(Integer.valueOf(String.valueOf(posts.getCategoryId()
								.getSubCategories().get(0).getSubCategoriesChild().get(0).getCategoryChildId())));
						if (posts.getCategorySubChildId().getCategorySubChildName() != null) {
							addPost.setCategorySubChildName(posts.getCategorySubChildId().getCategorySubChildName());
						}
					}

					res.add(addPost);

				}

			}
			return res;

		}

		return res;
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

	@Override
	public HubGenRes postEvents(Events req) {
		String finalFilePath = null;
		HubGenRes res = new HubGenRes();
		Events newEvent = new Events();
		if (req != null) {
			if (req.getEventName() != null) {
				newEvent.setEventName(req.getEventName());
			}
			if (req.getEventDecription() != null) {
				newEvent.setEventDecription(req.getEventDecription());
			}
			if (req.getLikes() != null) {
				newEvent.setLikes(req.getLikes());
			}
			if (req.getEventUserId()!= null) {
				newEvent.setEventUserId(req.getEventUserId());
			}
			if (req.getEventDate() != null) {
				newEvent.setEventDate(req.getEventDate());
			}
			if (req.getEvenTime() != null) {
				newEvent.setEvenTime(req.getEvenTime());
			}
			if (req.getMemberName() != null) {
				newEvent.setMemberName(req.getMemberName());
			}
			if (req.getEventImageInBytes() != null) {

				try {

					finalFilePath = writeByteToFile(req.getEventImageInBytes());

				} catch (IOException e) {
					res.setMessage("Error uploading image");
					res.setStatus("Failure");
					return res;
				}

				newEvent.setEventImg(finalFilePath);

			}

			try {

				eventRepositoryMob.save(newEvent);

				res.setMessage("event saved Successfully");
				res.setStatus("Success");
				return res;
			} catch (Exception e) {
				res.setMessage("unsuccessfull in saving event");
				res.setStatus("Failure");
				return res;
			}
		}

		res.setMessage("Enter the details");
		res.setStatus("Failure");
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
		File convertFile = new File("/home/rajesh/Desktop/" + current);
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

	@Override
	public List<EventRes> getEventByUserId(HubGenReq req) throws FileNotFoundException {

		List<EventRes> res = new ArrayList<>();
		List<Events> allEvents = new ArrayList<>();
		EventRes addEvent = new EventRes();

		if (req.getSecureKey() != null) {
			try {
				allEvents = (List<Events>) eventRepositoryMob.findEventById(req.getSecureKey());
			} catch (Exception e) {
				return res;

			}

			if (allEvents != null) {
				for (Events events : allEvents) {

					if (events.getEventName() != null) {
						addEvent.setEventName(events.getEventName());
					}
					if (events.getEventDecription() != null) {
						addEvent.setEventDecription(events.getEventDecription());
					}
					if (events.getLikes() != null) {
						addEvent.setLikes(events.getLikes());
					}
					if (events.getEventImg() != null) {
						File convertFile = new File(events.getEventImg());
						byte[] bArray = readFileToByteArray(convertFile);

						addEvent.setEventImageInBytes(bArray);
					}
					if (events.getEventDate() != null) {
						addEvent.setEventDate(events.getEventDate());
					}
					if (events.getEvenTime() != null) {
						addEvent.setEvenTime(events.getEvenTime());
					}

					if (events.getEventUserId()!= null) {

						addEvent.setEventUserId(events.getEventUserId());
					}

					res.add(addEvent);

				}

			}
			return res;

		}

		return res;
	}

}
