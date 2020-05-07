package in.co.hubapp.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

import in.co.hubapp.model.User;
import in.co.hubapp.dto.UserRegistrationDto;
import in.co.hubapp.mobile.channel.HubGenRes;

public interface UserService extends UserDetailsService {

    User findByEmail(String email);

    User save(UserRegistrationDto registration);
    
    public HubGenRes uploadDocument(MultipartFile file);
}