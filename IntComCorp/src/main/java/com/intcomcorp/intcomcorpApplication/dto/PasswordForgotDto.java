package com.intcomcorp.intcomcorpApplication.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PasswordForgotDto {

	@Email
	@NotEmpty
	private String email;


}
