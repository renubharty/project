package com.intcomcorp.intcomcorpApplication.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "host")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Host {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	private String name;
	private int hostId;
    
	@ManyToOne(fetch = FetchType.EAGER, optional = false)

	@JoinTable(name = "users_hosts", joinColumns = @JoinColumn(name = "host_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	private User user;
   
	public Host(String name,int hostId) {
		this.name = name;
		this.hostId = hostId;
	}
	
	
}
