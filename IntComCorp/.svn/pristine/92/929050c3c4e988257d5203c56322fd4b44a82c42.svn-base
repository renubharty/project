package com.intcomcorp.intcomcorpApplication.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.intcomcorp.intcomcorpApplication.dto.Request;
import com.intcomcorp.intcomcorpApplication.dto.request.CreatingAnItem;
import com.intcomcorp.intcomcorpApplication.dto.request.ItemCreate;
import com.intcomcorp.intcomcorpApplication.dto.request.ItemGet;
import com.intcomcorp.intcomcorpApplication.dto.response.ApiResponse;
import com.intcomcorp.intcomcorpApplication.dto.response.ErrorResponse;
import com.intcomcorp.intcomcorpApplication.dto.response.ZabbixApiResponse;
import com.intcomcorp.intcomcorpApplication.utils.RequestBuilder;

@Service
public class ZabbixApiItemService {
	@Autowired
	private ZabbixInitializerService initializerService;

	/**
	 * 
	 * @param itemCreate
	 * @return
	 */
	public ResponseEntity<ApiResponse> create(ItemCreate itemCreate) {
		JSONObject response = null;
		if (initializerService.loginValidate()) {
			Request request = RequestBuilder.newBuilder().method("item.create").paramEntry("name", itemCreate.getName())
					.paramEntry("key_", itemCreate.getKey()).paramEntry("hostid", itemCreate.getHostid())
					.paramEntry("type", itemCreate.getType())
					.paramEntry("snmp_community", itemCreate.getSnmpCommunity())
					.paramEntry("snmp_oid", itemCreate.getSnmpOid())
					.paramEntry("value_type", itemCreate.getType()).paramEntry("units", itemCreate.getUnits())
					.paramEntry("interfaceid", itemCreate.getInterfaceid())
					.paramEntry("preprocessing", itemCreate.getPreprocessings())
					.paramEntry("delay", itemCreate.getDelay()).build();
			response = initializerService.zabbixApi.call(request);
			return new ResponseEntity<>(new ZabbixApiResponse(response), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ErrorResponse("zabbix server is unavailable"), HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * 
	 * @param itemGet
	 * @return
	 */
	public ResponseEntity<ApiResponse> get(ItemGet itemGet) {
		JSONObject response = null;
		if (initializerService.loginValidate()) {
			Request request = RequestBuilder.newBuilder().method("item.get").paramEntry("output", itemGet.getOutput())
					.paramEntry("hostids", itemGet.getHostids()).paramEntry("search", itemGet.getSearch())
					.paramEntry("sortfield", itemGet.getSortfield()).build();
			response = initializerService.zabbixApi.call(request);
			return new ResponseEntity<>(new ZabbixApiResponse(response), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ErrorResponse("zabbix server is unavailable"), HttpStatus.BAD_REQUEST);
		}
	}

	public ResponseEntity<ApiResponse> createItem(CreatingAnItem creatingAnItem) {
		JSONObject response = null;
		if (initializerService.loginValidate()) {
			Request request = RequestBuilder.newBuilder().method("item.create")
					.paramEntry("name", creatingAnItem.getName()).paramEntry("key_", creatingAnItem.getKey())
					.paramEntry("hostid", creatingAnItem.getHostid()).paramEntry("type", creatingAnItem.getType())
					.paramEntry("value_type", creatingAnItem.getValueType())
					.paramEntry("interfaceid", creatingAnItem.getInterfaceid())
					.paramEntry("applications", creatingAnItem.getApplication())
					.paramEntry("delay", creatingAnItem.getDelay()).build();
			response = initializerService.zabbixApi.call(request);
			return new ResponseEntity<>(new ZabbixApiResponse(response), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ErrorResponse("zabbix server is unavailable"), HttpStatus.BAD_REQUEST);

		}
	}

}
