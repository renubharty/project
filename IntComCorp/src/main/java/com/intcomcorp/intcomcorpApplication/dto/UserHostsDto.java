package com.intcomcorp.intcomcorpApplication.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserHostsDto implements Serializable {
  
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	  private Long hostId;
	  
	  private Long customerId;
	  
	  private List<String> hostIds = new ArrayList<>();
	  
	
}
