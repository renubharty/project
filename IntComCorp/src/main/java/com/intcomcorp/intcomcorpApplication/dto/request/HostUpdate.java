package com.intcomcorp.intcomcorpApplication.dto.request;

import java.io.Serializable;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
public class HostUpdate implements Serializable {

	private static final long serialVersionUID = 1L;
	@NotBlank(message = "hostId.required")
	@Pattern(regexp = "[0-9]+|^$", message = "host_ids.must.valid")
	private String hostid;
	@NotNull(message = "host_status.required")
	private int status;

}
