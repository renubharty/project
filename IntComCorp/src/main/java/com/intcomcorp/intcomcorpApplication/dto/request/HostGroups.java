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
public class HostGroups  implements Serializable {

	private static final long serialVersionUID = 5111964034237205466L;

	@NotBlank(message = "groupid.required")
	private String groupid;

	



}
