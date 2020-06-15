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
public class TemplateCreate {
	private String host;

	@JsonProperty("groups")
	private Group group;

	
	private Set<Hosts> hosts;


	private Set<HostTag> tags;

	@Data
	private class Group {
		private int groupid;
	}

	@Data
	private class Hosts {
		private String hostid;
	}

}
