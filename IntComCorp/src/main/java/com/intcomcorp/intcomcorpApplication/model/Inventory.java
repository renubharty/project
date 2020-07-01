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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import lombok.Getter;
import lombok.Setter;

@Getter

@Setter

@Entity
@Table(name = "inventory")
public class Inventory {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotBlank
	private String type;
	@NotBlank
	private String name;
	@NotBlank
	@Column(name = "mac_address")
	private String macaddress;
	@NotBlank
	private String model;
	@NotBlank
	@Column(name = "serial_number")
	private String serialNumber;
	@Transient
	private Long resId;
	
	
	 @ManyToOne(fetch = FetchType.EAGER)
	 @JoinTable(
	            name = "reseller_inventory",
	            joinColumns = @JoinColumn(name = "inventory_id"),
	            inverseJoinColumns = @JoinColumn(name = "reseller_id")
	    )
	private Reseller reseller ;
	 
	private boolean isDeleted;
	
	private boolean isAssign;
	

}
