package com.intcomcorp.intcomcorpApplication.zabbix.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "hosts")
@Getter
@Setter
@NoArgsConstructor
public class Templates {
	
	@Id
	private Long id;
	

    private String name;
	
	private String status;
	
	private String hostid;
    
	@Transient
	private String tmpId;
	
	
	
			
	public Templates(String name,String hostid) {
		    
		this.name = name;
		this.hostid = hostid;
	}
    
}
