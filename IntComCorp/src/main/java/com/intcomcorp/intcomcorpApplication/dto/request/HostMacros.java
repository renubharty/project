package com.intcomcorp.intcomcorpApplication.dto.request;

import java.io.Serializable;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class HostMacros implements Serializable {

	private static final long serialVersionUID = 1L;
	@NotBlank(message = "macro.required")
	private String macro;
	@NotBlank(message = "macro_value.required")
	private String value;

}
