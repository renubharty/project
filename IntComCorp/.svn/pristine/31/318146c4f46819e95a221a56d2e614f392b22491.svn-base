package com.intcomcorp.intcomcorpApplication.dto.request;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HostGet {

	
	@JsonProperty(value = "filter")
	private HostGetFilter hostGetFilter;

	@Data
	private class HostGetFilter {

		private Set<String> host;

	}
}
