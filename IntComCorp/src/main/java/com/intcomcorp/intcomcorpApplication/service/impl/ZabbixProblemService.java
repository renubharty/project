package com.intcomcorp.intcomcorpApplication.service.impl;

import java.util.List;

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

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ZabbixProblemService {
	
	@Autowired
	private ZabbixInitializerService initializerService;

	@Autowired
	private MessageSource messageSource;
// this method commented temporarty
	/*
	 * public ResponseEntity<?> problemByHost(List<String> hostids) {
	 * 
	 * JSONObject response = null; if (initializerService.loginValidate()) { Request
	 * request = RequestBuilder.newBuilder()
	 * .method("problem.get").paramEntry("output", "extend") .paramEntry("hostids",
	 * hostids) .paramEntry("recent", true).build(); response =
	 * initializerService.zabbixApi.call(request); return new ResponseEntity<>(new
	 * ZabbixApiResponse(response), HttpStatus.OK); } else { return new
	 * ResponseEntity<>(new ErrorResponse("zabbix server is unavailable"),
	 * HttpStatus.BAD_REQUEST); }
	 * 
	 * }
	 */
	
	public ResponseEntity<?> problemByHost(String hostids) {

		JSONObject response = null;
		if (initializerService.loginValidate()) {
			Request request = RequestBuilder.newBuilder()
					.method("problem.get").paramEntry("output", "extend")
					.paramEntry("hostids", hostids)
			        .paramEntry("recent", true).build();
			response = initializerService.zabbixApi.call(request);
			return new ResponseEntity<>(new ZabbixApiResponse(response), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ErrorResponse("zabbix server is unavailable"), HttpStatus.BAD_REQUEST);
		}

	}

	
	
	
	
}
