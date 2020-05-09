package com.intcomcorp.intcomcorpApplication.zabbix.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Test {

	@Id
	private int id;
	private String name;

}
