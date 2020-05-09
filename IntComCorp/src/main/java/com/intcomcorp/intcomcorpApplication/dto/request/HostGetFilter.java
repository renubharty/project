package com.intcomcorp.intcomcorpApplication.dto.request;

import java.io.Serializable;
import java.util.Set;

import lombok.Data;

@Data
public class HostGetFilter implements Serializable {

	private static final long serialVersionUID = -8882247662361210137L;
	private Set<String> host;

}
