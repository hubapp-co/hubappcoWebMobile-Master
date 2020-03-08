package in.co.hubapp.mobile.service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.co.hubapp.mobile.channel.HubGenRes;
import in.co.hubapp.mobile.channel.Login;
import in.co.hubapp.mobile.channel.Register;
import in.co.hubapp.mobile.repository.UserRepositoryMob;
import in.co.hubapp.model.User;

@Service
public class UserServiceMobImpl implements UserServiceMob {

	@Autowired
	UserRepositoryMob userRepositoryMob;

	private EntityManager em;

	@Autowired
	public UserServiceMobImpl(EntityManager theEntityManager) {
		em = theEntityManager;
	}

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
				user.setPassword(req.getPassword());
			}
			try {
			User user1=userRepositoryMob.findByEmailAddress(req.getEmail());
			System.out.println(user1);
				user = userRepositoryMob.save(user);
			} catch (Exception e) {
				res.setMessage("email id is already registered");
				res.setStatus("Failure");
				return res;
			}

			if (user != null) {
				res.setMessage("user Registered Successfully");
				res.setStatus("success");
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
		if (req.getUsername() != null && req.getPassword() != null) {
			User user = userRepositoryMob.findByEmailAddress(req.getUsername());
			System.out.println(user);
			if (user != null) {
				res.setMessage("User is Registered");
				res.setStatus("Success");
				res.setUser(user);
				return res;
			} else {
				res.setMessage("user is Not Registered With Us");
				res.setStatus("Failure");
				return res;
			}

		}

		res.setMessage("Please enter the credentials");
		res.setStatus("Failure");
		return res;

	}

}
