package com.intcomcorp.intcomcorpApplication.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class CreatingAnItem {

	@JsonProperty("name")
	private String name;

	@JsonProperty("key_")
	private String key;

	@JsonProperty("hostid")
	@NotBlank(message = "hostId.required")
	@Pattern(regexp = "[0-9]+|^$", message = "host_ids.must.valid")
	private String hostid;

	@JsonProperty("type")
	private long type;

	@JsonProperty("value_type")
	private long valueType;

	@JsonProperty("interfaceid")
	private String interfaceid;

	@JsonProperty("applications")
	private Set<String> application;

	@JsonProperty("delay")
	private String delay;
	
	
}
