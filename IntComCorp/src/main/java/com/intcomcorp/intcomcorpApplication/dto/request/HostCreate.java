package com.intcomcorp.intcomcorpApplication.dto.request;

import java.io.Serializable;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class HostCreate implements Serializable{

	private static final long serialVersionUID = 1L;

	private String host;

	@JsonProperty("interfaces")
	private Set<HostInterface> hostInterface;

	@JsonProperty("groups")
	private Set<HostGroup> hostGroup;

	@JsonProperty("tags")
	private Set<HostTag> hostTag;

	@JsonProperty("templates")
	private Set<Template> templates;

	@JsonProperty("macros")
	private Set<HostMacro> hostMacro;

	@JsonProperty("inventory_mode")
	private int InventoryMode;
	
	@JsonProperty("inventory")
	private HostInventory hostInventory;

}
