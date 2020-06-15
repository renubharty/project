package com.intcomcorp.intcomcorpApplication.dto.request;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResellerInventoryRequest {

	
	private Long resId;
	private List<Long> inventoryIds = new ArrayList<>();
}
