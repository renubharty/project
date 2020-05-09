package com.intcomcorp.intcomcorpApplication.dto;

import lombok.Data;

@Data
public class OrganizationDto {

	private long id;
	private String name;
	private String address;
	private String city;
	private String state;
	private String zipcode;
	private String phone;
	private String email;

}
