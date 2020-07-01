package com.intcomcorp.intcomcorpApplication.dto;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.intcomcorp.intcomcorpApplication.model.Reseller;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class UserRegistrationDto {

	@NotEmpty
	private String firstName;

	@NotEmpty
	private String lastName;
    
	@NotEmpty
	private String company;
	@NotEmpty
	private String phone;
	@NotEmpty
	private String city;
	@NotEmpty
	private String zip;
	
	
	private String password;

	@Email
	@NotEmpty
	private String email;

	

	@AssertTrue
	private Boolean terms;
	
    private String resId;
	
	private Reseller reseller;

	
}