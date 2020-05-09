package com.intcomcorp.intcomcorpApplication.dto.request;

import java.io.Serializable;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class HostMacros implements Serializable {

	private static final long serialVersionUID = 1L;
	@NotBlank(message = "macro.required")
	private String macro;
	@NotBlank(message = "macro_value.required")
	private String value;

}
