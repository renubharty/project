package com.intcomcorp.intcomcorpApplication.dto.request;

import java.io.Serializable;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HostTags implements Serializable {

	private static final long serialVersionUID = 116258332881535555L;
	private String tag;
	private String value;

}
