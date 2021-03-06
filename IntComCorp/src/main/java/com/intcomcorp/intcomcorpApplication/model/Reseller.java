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
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Reseller_TAB")
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
	@NotEmpty
	@Transient
	private String organization;

	@ManyToMany(mappedBy = "resSet", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	Set<Organization> orgSet = new HashSet<>();

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "reseller_roles", joinColumns = @JoinColumn(name = "reseller_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	private Collection<Role> roles;

}
