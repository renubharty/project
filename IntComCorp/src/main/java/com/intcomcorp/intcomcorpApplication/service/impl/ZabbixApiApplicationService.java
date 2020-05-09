package com.intcomcorp.intcomcorpApplication.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.intcomcorp.intcomcorpApplication.dto.Request;
import com.intcomcorp.intcomcorpApplication.dto.response.ErrorResponse;
import com.intcomcorp.intcomcorpApplication.dto.response.ZabbixApiResponse;
import com.intcomcorp.intcomcorpApplication.utils.RequestBuilder;


@Service
public class ZabbixApiApplicationService {

	@Autowired
	private ZabbixInitializerService initializerService;

	@Autowired
	private MessageSource messageSource;

	/**
	 * 
	 * @param applicationGet
	 * @return
	 */
	public ResponseEntity<?> application(String extend, String hostids, String name) {

		JSONObject response = null;
		if (initializerService.loginValidate()) {
			Request request = RequestBuilder.newBuilder().method("application.get").paramEntry("output", extend)
					.paramEntry("hostids", hostids).paramEntry("sortfield", name).build();
			response = initializerService.zabbixApi.call(request);

			return new ResponseEntity<>(new ZabbixApiResponse(response), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ErrorResponse("zabbix server is unavailable"), HttpStatus.BAD_REQUEST);
		}

	}
}
