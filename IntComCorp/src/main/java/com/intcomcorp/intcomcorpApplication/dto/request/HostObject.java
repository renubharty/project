package com.intcomcorp.intcomcorpApplication.dto.request;

import lombok.Data;

@Data
public class HostObject {
	private String hostid;
	private String host;
	private int available;
	private String description;
	private String error;
	private String name;
	private int snmp_available;
	private String snmp_error;
	private int status;

}
