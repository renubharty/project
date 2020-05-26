package com.intcomcorp.intcomcorpApplication.dto.request;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResellerInventoryRequest {

	
	private Long resId;
	private List<Long> inventoryIds = new ArrayList<>();
}
