package com.intcomcorp.intcomcorpApplication.dto;

import java.util.ArrayList;
import java.util.List;

import com.intcomcorp.intcomcorpApplication.zabbix.model.Groups;
import com.intcomcorp.intcomcorpApplication.zabbix.model.Templates;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DeviceDto {
	
	private List<Templates> templatesList = new ArrayList<>();
    
	private List<Groups> groupsList = new ArrayList<>();
	
	private String groupId;
	
	private String tmpId;
	
	private String port;
}
