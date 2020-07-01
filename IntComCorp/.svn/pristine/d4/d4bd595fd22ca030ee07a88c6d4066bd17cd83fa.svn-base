package com.intcomcorp.intcomcorpApplication.dto.request;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
public class ApplicationGet {

	private String output;

	@NotBlank(message = "hostId.required")
	@Pattern(regexp = "[0-9]+|^$", message = "host_ids.must.valid")
	private String hostids;

	private String sortfield;

}
