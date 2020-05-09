package com.intcomcorp.intcomcorpApplication.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class ItemGet {

	private String output;
	@NotBlank(message = "hostId.required")
	@Pattern(regexp = "[0-9]+|^$", message = "host_ids.must.valid")
	private String hostids;
	private String sortfield;
	private Search search;

	@Data
	private class Search {
		@JsonProperty("key_")
		private String key;
	}

}
