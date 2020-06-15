package com.intcomcorp.intcomcorpApplication.dto.request;

import java.io.Serializable;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserUpdate implements Serializable {
	private static final long serialVersionUID = 4371250338032173267L;

	private String userid;
	private String name;
	private String surname;

}
