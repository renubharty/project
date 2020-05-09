package com.intcomcorp.intcomcorpApplication.dto.request;

import java.io.Serializable;

import lombok.Data;

@Data
public class UserMedias implements Serializable {
	private static final long serialVersionUID = -7920426386290650006L;
	private String mediatypeid;
	private String[] sendto;
	private int active;
	private int severity;
	private String period;

}
