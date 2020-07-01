package com.intcomcorp.intcomcorpApplication.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProblemDto {
	
	private String description;
	private String severity;
	private String clock;
	private String objectId;
	private String eventId;
	private String acknowledge;
	private String hostName;
	private String hostId;
	private String period;

}
