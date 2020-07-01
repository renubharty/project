package com.intcomcorp.intcomcorpApplication.zabbix.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "hstgrp")
@Getter
@Setter
@NoArgsConstructor
public class Groups {
	
	@Id
	private Long id;
	
	private String  groupid;
	
	private String name;
	
	@Transient
	private String groupId;
	
	public Groups(String groupid,String name) {
		this.groupid = groupid;
		this.name = name;
	}

}
