package com.intcomcorp.intcomcorpApplication.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.intcomcorp.intcomcorpApplication.dto.UserRegistrationDto;
import com.intcomcorp.intcomcorpApplication.model.User;

public interface UserService extends UserDetailsService {

	User findByEmail(String email);

	User save(UserRegistrationDto registration);
	 void updatePassword(String password, Long userId);
}