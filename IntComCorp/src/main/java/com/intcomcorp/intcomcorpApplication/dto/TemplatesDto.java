package com.intcomcorp.intcomcorpApplication.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TemplatesDto {
	
	private String templateId;
	private String  templateName;
	
	public TemplatesDto(String templateId,String templateName) {
		this.templateId = templateId;
		this.templateName = templateName;
	}

}
