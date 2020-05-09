package com.intcomcorp.intcomcorpApplication.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.intcomcorp.intcomcorpApplication.dto.Request;
import com.intcomcorp.intcomcorpApplication.dto.request.HostInterfaceCreate;
import com.intcomcorp.intcomcorpApplication.dto.response.ApiResponse;
import com.intcomcorp.intcomcorpApplication.dto.response.ErrorResponse;
import com.intcomcorp.intcomcorpApplication.dto.response.ZabbixApiResponse;
import com.intcomcorp.intcomcorpApplication.utils.RequestBuilder;


@Service
public class ZabbixApiHostInterfaceService {

	@Autowired
	private ZabbixInitializerService initializerService;

	/**
	 * 
	 * @param hostInterfaceGet
	 * @return
	 */
	public ResponseEntity<?> hostInterfaces(String extend, String hostids) {
		JSONObject response = null;
		if (initializerService.loginValidate()) {
			Request request = RequestBuilder.newBuilder().method("hostinterface.get")
					.paramEntry("output", extend)
					.paramEntry("hostids", hostids).build();
			response = initializerService.zabbixApi.call(request);

			return new ResponseEntity<>(new ZabbixApiResponse(response), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ErrorResponse("zabbix server is unavailable"), HttpStatus.BAD_REQUEST);
		}

	}

	public ResponseEntity<ApiResponse> hostInterfaceCreate(HostInterfaceCreate hostInterfaceCreate) {
		JSONObject response = null;
		if (initializerService.loginValidate()) {
			Request request = RequestBuilder.newBuilder().method("hostinterface.create")
					.paramEntry("hostid", hostInterfaceCreate.getHostid())
					.paramEntry("dns", hostInterfaceCreate.getDns()).paramEntry("ip", hostInterfaceCreate.getIp())
					.paramEntry("main", hostInterfaceCreate.getMain()).paramEntry("port", hostInterfaceCreate.getPort())
					.paramEntry("type", hostInterfaceCreate.getType())
					.paramEntry("useip", hostInterfaceCreate.getUseip())

					.build();
			response = initializerService.zabbixApi.call(request);

			return new ResponseEntity<>(new ZabbixApiResponse(response), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ErrorResponse("zabbix server is unavailable"), HttpStatus.BAD_REQUEST);

		}
	}
}
