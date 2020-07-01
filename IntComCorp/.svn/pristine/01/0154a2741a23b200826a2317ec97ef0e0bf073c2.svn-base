package com.intcomcorp.intcomcorpApplication.dto;

import java.util.UUID;

import javax.validation.constraints.NotEmpty;

import com.intcomcorp.intcomcorpApplication.constraint.FieldMatch;
import com.intcomcorp.intcomcorpApplication.model.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@FieldMatch(first = "password", second = "confirmPassword", message = "The password fields must match")
@Getter
@Setter
@NoArgsConstructor
public class PasswordResetDto {

	@NotEmpty
	private String password;

	@NotEmpty
	private String confirmPassword;

	@NotEmpty
	private String token;
	
	private User user;

	
}
