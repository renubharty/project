package com.intcomcorp.intcomcorpApplication.dto.request;

import java.io.Serializable;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class GraphGet implements Serializable {

	private static final long serialVersionUID = 5795222752176039559L;
	private String output;
	@NotBlank(message = "hostId.required")
	@Pattern(regexp = "[0-9]+|^$", message = "host_ids.must.valid")
	private Long hostids;
	private String sortfield;

}
