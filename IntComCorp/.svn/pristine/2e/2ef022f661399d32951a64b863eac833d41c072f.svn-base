package com.intcomcorp.intcomcorpApplication.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.intcomcorp.intcomcorpApplication.dto.Request;
import com.intcomcorp.intcomcorpApplication.dto.ZabbixDeleteRequest;
import com.intcomcorp.intcomcorpApplication.dto.request.HostCreate;
import com.intcomcorp.intcomcorpApplication.dto.request.HostDelete;
import com.intcomcorp.intcomcorpApplication.dto.request.HostUpdate;
import com.intcomcorp.intcomcorpApplication.dto.response.ApiResponse;
import com.intcomcorp.intcomcorpApplication.dto.response.ErrorResponse;
import com.intcomcorp.intcomcorpApplication.dto.response.ZabbixApiResponse;
import com.intcomcorp.intcomcorpApplication.utils.RequestBuilder;

@Service
public class ZabbixApiHostService {

	@Autowired
	private ZabbixInitializerService initializerService;

	/**
	 * Retrieve all data about hosts by name
	 * 
	 * @param hostGet
	 * @return
	 */
	public ResponseEntity<?> hostsDetails(String[] hosts) {
		JSONObject response = null;
	if (initializerService.loginValidate()) {
			Request request = RequestBuilder.newBuilder().method("host.get")
					          .paramEntry("filter", hosts).build();
			response = initializerService.zabbixApi.call(request);
			return new ResponseEntity<>(new ZabbixApiResponse(response), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ErrorResponse("zabbix server is unavailable"), HttpStatus.BAD_REQUEST);
		}

	}

	/**
	 * Retrieve names of the groups host but no host details themselves
	 * 
	 * @param retrievingHostGroups
	 * @return
	 */
	public ResponseEntity<?> hostGroups(String[] hostid, String[] hosts, String extend) {
		JSONObject response = null;
		if (initializerService.loginValidate()) {
			Request request = RequestBuilder.newBuilder().method("host.get").paramEntry("output", hostid)
					.paramEntry("filter", hosts).paramEntry("selectGroups", extend).build();
			response = initializerService.zabbixApi.call(request);

			return new ResponseEntity<>(new ZabbixApiResponse(response), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ErrorResponse("zabbix server is unavailable"), HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * Create a host with an IP interface and tags, add it to a group, link a
	 * template to it and set the MAC addresses in the host inventory
	 * 
	 * @param hostCreate
	 * @return
	 */
	public ResponseEntity<ApiResponse> hostCreate(HostCreate hostCreate) {
		JSONObject response = null;
		if (initializerService.loginValidate()) {
			Request request = RequestBuilder.newBuilder().method("host.create").paramEntry("host", hostCreate.getHost())
					.paramEntry("interfaces", hostCreate.getHostInterface())
					.paramEntry("groups", hostCreate.getHostGroup())
					.paramEntry("tags", hostCreate.getHostTag())
					.paramEntry("templates", hostCreate.getTemplates())
					.paramEntry("macros", hostCreate.getHostMacro())
					.paramEntry("inventory_mode", hostCreate.getInventoryMode())
					.paramEntry("inventory", hostCreate.getHostInventory()).build();
			response = initializerService.zabbixApi.call(request);

			return new ResponseEntity<>(new ZabbixApiResponse(response), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ErrorResponse("zabbix server is unavailable"), HttpStatus.BAD_REQUEST);
		}

	}

	/**
	 * Update host status 0=enable or 1=disable
	 * 
	 * @param hostUpdate
	 * @return
	 */
	public ResponseEntity<?> update(HostUpdate hostUpdate) {
		JSONObject response = null;
		if (initializerService.loginValidate()) {
			Request request = RequestBuilder.newBuilder().method("host.update")
					.paramEntry("hostid", hostUpdate.getHostid()).paramEntry("status", hostUpdate.getStatus()).build();
			response = initializerService.zabbixApi.call(request);

			return new ResponseEntity<>(new ZabbixApiResponse(response), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ErrorResponse("zabbix server is unavailable"), HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * Delete multiple hosts
	 * 
	 * @param hostDelete
	 * @return
	 */
	public ResponseEntity<ApiResponse> delete(HostDelete hostDelete) {
		JSONObject response = null;
		if (initializerService.loginValidate()) {
			ZabbixDeleteRequest deleteRequest = new ZabbixDeleteRequest();
			deleteRequest.setParams(hostDelete.getHostids());
			deleteRequest.setMethod("host.delete");
			response = initializerService.zabbixApi.call(deleteRequest);
			return new ResponseEntity<>(new ZabbixApiResponse(response), HttpStatus.OK);

		} else {
			return new ResponseEntity<>(new ErrorResponse("zabbix server is unavailable"), HttpStatus.BAD_REQUEST);

		}
	}
}