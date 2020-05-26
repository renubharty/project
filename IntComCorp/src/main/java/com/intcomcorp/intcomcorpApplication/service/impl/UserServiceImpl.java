package com.intcomcorp.intcomcorpApplication.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.intcomcorp.intcomcorpApplication.dto.UserRegistrationDto;
import com.intcomcorp.intcomcorpApplication.iccn.repo.UserRepository;
import com.intcomcorp.intcomcorpApplication.model.Role;
import com.intcomcorp.intcomcorpApplication.model.User;
import com.intcomcorp.intcomcorpApplication.service.UserService;

@Service

public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public User save(UserRegistrationDto registration) {
		User user = new User();
		user.setFirstName(registration.getFirstName());
		user.setLastName(registration.getLastName());
		user.setEmail(registration.getEmail());
		user.setPassword(passwordEncoder.encode(registration.getPassword()));
		user.setRoles(Arrays.asList(new Role("ROLE_USER")));
		return userRepository.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
				mapRolesToAuthorities(user.getRoles()));
	}

	     @Override
	    public void updatePassword(String password, Long userId) {
	        userRepository.updatePassword(password, userId);
	    }

	
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}
	
	public List<User> getAllCustomer(){
		List<User> custList =	userRepository.findAll();
		List<User> filteredList =  new ArrayList<User>();
		 custList.forEach(cust ->{
			cust.getRoles().forEach(role ->{
				
				if (role.getName().equalsIgnoreCase("ROLE_USER")) {
					filteredList.add(cust);
				}
			});
		});

		return filteredList;
		}
	
	
	public List<User> getAllCustomerByHost(){
		List<User> custList =	userRepository.findAll();
		List<User> filteredList =  new ArrayList<User>();
		 custList.forEach(cust ->{
			
			 if (!cust.getUsersHostList().isEmpty()) {
				 filteredList.add(cust);
			 }
		});

		return filteredList;
		}
	
	
	public User getUserById(Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + id));
		return user;

	}
}