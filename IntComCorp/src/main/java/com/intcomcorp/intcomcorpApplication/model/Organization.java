package com.intcomcorp.intcomcorpApplication.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "Organization")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Organization {
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
	@Email(message = "Email should be in proper format")
	@NotEmpty
	private String email;
	@ManyToMany(mappedBy = "orgSet", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Reseller> resSet = new HashSet<>();
	@OneToMany(mappedBy = "org", fetch = FetchType.EAGER, cascade = CascadeType.ALL) 
	private List<Inventory> inventoryList = new ArrayList<>();
	
	public Organization(Organization org) {
		this.id = org.getId();
		this.name= org.name;
		this.address= org.getAddress();
		this.city=org.getCity();
		this.state=org.getState();
		this.zipcode=org.getZipcode();
		this.phone = org.getPhone();
		this.email=org.getEmail();
		
	}
	
	public Organization(Long id) {
		this.id=id;
	}

}
