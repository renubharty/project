package com.intcomcorp.intcomcorpApplication.dto;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.intcomcorp.intcomcorpApplication.model.Reseller;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class UserRegistrationDto {

	@NotEmpty(message = "First name is not empty")
	private String firstName;

	@NotEmpty(message = "last name is not empty")
	private String lastName;
    
	@NotEmpty(message = "company  is not empty")
	private String company;
	@NotEmpty(message = "phone  is not empty")
	private String phone;
	@NotEmpty(message = "city is not empty")
	private String city;
	@NotEmpty(message = "zip  is not empty")
	private String zip;
	
	
	private String password;

	@Email(message = "email should be in proper format")
	@NotEmpty(message = "email  is not empty")
	private String email;

	

	@AssertTrue
	private Boolean terms;
	@NotBlank(message = "Select Reseller")
    private String resId;
	
	private Reseller reseller;

	
}