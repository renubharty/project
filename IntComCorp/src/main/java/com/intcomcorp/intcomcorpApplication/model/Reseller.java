package com.intcomcorp.intcomcorpApplication.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "reseller")
@Getter
@Setter
@NoArgsConstructor


public class Reseller {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@NotEmpty(message = "name is not empty")
	private String name;
	@NotEmpty(message = "address  is not empty")
	private String address;
	@NotEmpty(message = "city  is not empty")
	private String city;
	@NotEmpty(message = "state  is not empty")
	private String state;
	@NotEmpty(message = "zip  is not empty")
	private String zipcode;
	@NotEmpty(message = "phone  is not empty")
	private String phone;
	@Email(message = "Email shoul be in proper format")
	@NotEmpty(message = "email  is not empty")
	private String email;
	@Column(length = 32 ,name = "active")
	private boolean isActive = true;
	@Transient
	private String password;
	@OneToMany(mappedBy = "reseller",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	private Set<User> userList  =  new HashSet<>();
	
	@OneToMany(mappedBy = "reseller", fetch = FetchType.EAGER, cascade = CascadeType.ALL) 
	private Set<Inventory> inventoryList = new HashSet<>();
	
	@Transient
	private List<Long> userIds = new ArrayList<>();
	

}
