package com.intcomcorp.intcomcorpApplication.dto.request;

import lombok.Data;

@Data
public class JsHostCreate {

	private String hostname;
	private String groupid;
	private String ip;
	private String port;
	private String dns;
	private String main;
	private String type;
	private String useip;
	private String templateid;
	private String macro;
	private String value;

}
