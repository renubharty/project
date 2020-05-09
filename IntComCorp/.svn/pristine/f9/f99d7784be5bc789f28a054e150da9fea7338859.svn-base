package com.intcomcorp.intcomcorpApplication.dto.request;

import java.io.Serializable;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class HostInterfaces implements Serializable {

	private static final long serialVersionUID = -8446868068206601166L;
	@NotNull(message = "ip_type.required")
	private int type;
	private int main;
	private int useip;
	@NotBlank(message = "ip_address.required")
	private String ip;
	private String dns;
	@NotBlank(message = "ip_port.required")
	private String port;

}
