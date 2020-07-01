package com.intcomcorp.intcomcorpApplication.dto;

import java.util.Map;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EmailDto {
	
	String from;
	String to;
	String subject;
	String body;
	Map<String, Object> model;

}
