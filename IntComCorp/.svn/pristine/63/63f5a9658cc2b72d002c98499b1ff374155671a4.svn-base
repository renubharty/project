package com.intcomcorp.intcomcorpApplication.dto.request;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class ItemCreate {

	private String name;

	@JsonProperty("key_")
	private String key;
	@NotBlank(message = "hostId.required")
	private String hostid;

	private int type;

	@JsonProperty("snmp_community")
	private String snmpCommunity;

	@JsonProperty("snmp_oid")
	private String snmpOid;

	@JsonProperty("value_type")
	private String valueType;

	private String delay;

	private String units;

	private String interfaceid;

	private Set<Preprocessing> preprocessings;

	@Data
	private class Preprocessing {

		private String type;

		private String params;

		@JsonProperty("error_handler")
		private String errorHandler;

		@JsonProperty("error_handler_params")
		private String errorHandlerParams;

	}

}
