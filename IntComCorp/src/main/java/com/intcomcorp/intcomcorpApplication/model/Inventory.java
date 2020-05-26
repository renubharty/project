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

import lombok.Getter;
import lombok.Setter;

@Getter

@Setter

@Entity
@Table(name = "Inventory")
public class Inventory {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String type;
	private String name;
	@Column(name = "mac_address")
	private String macaddress;
	private String model;
	private String vendor;
	@Column(name = "serial_number")
	private String serialNumber;
	@Transient
	private Long distId;
	
	
	 @ManyToOne(fetch = FetchType.EAGER, optional = false)
	 @JoinTable(
	            name = "distributor_inventory",
	            joinColumns = @JoinColumn(name = "inventory_id"),
	            inverseJoinColumns = @JoinColumn(name = "distributor_id")
	    )  
    
	private Organization org ;
	 
	private boolean isDeleted;
	private boolean isAssign;

}
