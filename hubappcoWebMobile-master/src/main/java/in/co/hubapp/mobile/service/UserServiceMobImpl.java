package in.co.hubapp.mobile.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import in.co.hubapp.mobile.channel.HubGenRes;
import in.co.hubapp.mobile.channel.Login;
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
				try
				{
				user.setPassword(passwordEncoder.encode(req.getPassword()));
				}
				catch (Exception e)
				{
					res.setMessage("user Registered Unsuccessfull");
					res.setStatus("Failure");
					return res;
				}
			}
			try {
				if(req.getEmail()!=null)
				{
				User user1 = userRepositoryMob.findByEmailAddress(req.getEmail());
				
				if(user1!=null)
				{
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
	public HubGenRes post(Posts post, String path) {

		HubGenRes res = new HubGenRes();
		Posts newpost = new Posts();
		if (post != null && path != null) {
			if (post.getPostTitle() != null) {
				newpost.setPostTitle(post.getPostTitle());
			}
			if (post.getPostDescription() != null) {
				newpost.setPostDescription(post.getPostDescription());
			}
			if (post.getLikes() != null) {
				newpost.setLikes(post.getLikes());
			}
			if (path != null) {
				post.setPostImageUrl(path);
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

}
