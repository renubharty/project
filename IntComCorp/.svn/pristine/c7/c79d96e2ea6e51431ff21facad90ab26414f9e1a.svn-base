package com.intcomcorp.intcomcorpApplication.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.intcomcorp.intcomcorpApplication.dto.UserRegistrationDto;
import com.intcomcorp.intcomcorpApplication.model.Reseller;
import com.intcomcorp.intcomcorpApplication.model.User;

public interface UserService extends UserDetailsService {

	User findByEmail(String email);

	 User save(UserRegistrationDto registration);
	 void updatePassword(String password, Long userId);
	 public List<User> getAllCustomer();
	 public List<User> getAllCustomerByHost();
	 User getUserById(Long id);
	 public boolean updateUserHosts(User user);
	 User saveReseller(Reseller reseller);
}