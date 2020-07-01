package com.intcomcorp.intcomcorpApplication.dto.request;

import java.io.Serializable;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserCreate implements Serializable {

	private static final long serialVersionUID = 7053433022075009960L;

	private String alias;

	private String passwd;

	@JsonProperty("usrgrps")
	private Set<UserGroups> userGroups;

	@JsonProperty("user_medias")
	private Set<UserMedias> userMedias;

	@Data
	private class UserGroups {
		private String usrgrpid;
	}

	@Data
	private class UserMedias {

		private String mediatypeid;
		private String[] sendto;
		private int active;
		private int severity;
		private String period;
	}

}
