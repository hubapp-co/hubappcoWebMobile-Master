package in.co.hubapp.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import in.co.hubapp.model.User;
import in.co.hubapp.dto.UserRegistrationDto;

public interface UserService extends UserDetailsService {

    User findByEmail(String email);

    User save(UserRegistrationDto registration);
}