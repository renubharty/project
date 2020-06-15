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
	@NotEmpty
	private String name;
	@NotEmpty
	private String address;
	@NotEmpty
	private String city;
	@NotEmpty
	private String state;
	@NotEmpty
	private String zipcode;
	@NotEmpty
	private String phone;
	@Email(message = "Email shoul be in proper format")
	@NotEmpty
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
