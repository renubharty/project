package com.intcomcorp.intcomcorpApplication.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "Organization_tab")
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
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "org_reseller", joinColumns = {
			@JoinColumn(name = "org_id", referencedColumnName = "id", nullable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "reseller_id", referencedColumnName = "id", nullable = false) })
	private Set<Reseller> resSet = new HashSet<>();
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "org_roles", joinColumns = @JoinColumn(name = "org_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	private Collection<Role> roles;
	
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
