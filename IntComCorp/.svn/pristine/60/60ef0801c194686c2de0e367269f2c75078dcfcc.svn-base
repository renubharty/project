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
import com.intcomcorp.intcomcorpApplication.model.Reseller;
import com.intcomcorp.intcomcorpApplication.model.Role;
import com.intcomcorp.intcomcorpApplication.model.User;
import com.intcomcorp.intcomcorpApplication.service.UserService;
import com.intcomcorp.intcomcorpApplication.utils.RandomUniquePassword;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
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
		user.setPassword(registration.getPhone());
		user.setCompany(registration.getCompany());
		user.setCity(registration.getCity());
		user.setZip(registration.getZip());
		user.setReseller(registration.getReseller());
		user.setPassword(passwordEncoder.encode(RandomUniquePassword.generateRandomPassword(9)));
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
	
	public boolean updateUserHosts(User user) {
		
		try {
			userRepository.save(user);
			return true;
		} catch (Exception e) {
			
			return false;
		}
		
		
	}
	
public User  saveReseller(Reseller reseller) {
		
		User user = new User();
		user.setFirstName(reseller.getName());
		user.setEmail(reseller.getEmail());
		user.setPassword(passwordEncoder.encode(reseller.getPassword()));
		System.out.println(" reseller.getPassword() " +reseller.getPassword());
		user.setRoles(Arrays.asList(new Role("ROLE_RESELLER")));
		try {
			userRepository.save(user);	
		} catch (Exception e) {
			log.error("Error while saving in user table", e);
		}
		return user;
	    
	}
}