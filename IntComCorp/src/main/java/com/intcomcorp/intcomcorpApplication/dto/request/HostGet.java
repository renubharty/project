package com.intcomcorp.intcomcorpApplication.dto.request;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class HostGet {

	
	@JsonProperty(value = "filter")
	private HostGetFilter hostGetFilter;

	@Data
	private class HostGetFilter {

		private Set<String> host;

	}
}
