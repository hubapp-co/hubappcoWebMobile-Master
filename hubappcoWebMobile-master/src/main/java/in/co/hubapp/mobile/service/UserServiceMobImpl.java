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

import in.co.hubapp.mobile.channel.HubGenReq;
import in.co.hubapp.mobile.channel.HubGenRes;
import in.co.hubapp.mobile.channel.Login;
import in.co.hubapp.mobile.channel.Post;
import in.co.hubapp.mobile.channel.Register;
import in.co.hubapp.mobile.repository.CategoryRepositoryMob;
import in.co.hubapp.mobile.repository.PostsRepositoryMob;
import in.co.hubapp.mobile.repository.UserRepositoryMob;
import in.co.hubapp.model.Category;
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

	public List<Post> getPost(HubGenReq req) throws FileNotFoundException {

		List<Post> res = new ArrayList<>();
		List<Posts> allPosts = new ArrayList<>();
		Post addPost = new Post();

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

}
