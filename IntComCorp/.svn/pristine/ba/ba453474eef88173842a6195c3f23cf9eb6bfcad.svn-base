package com.intcomcorp.intcomcorpApplication.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.intcomcorp.intcomcorpApplication.dto.Request;
import com.intcomcorp.intcomcorpApplication.dto.request.UserMacroGet;
import com.intcomcorp.intcomcorpApplication.dto.response.ErrorApiResponse;
import com.intcomcorp.intcomcorpApplication.dto.response.ServiceResponse;
import com.intcomcorp.intcomcorpApplication.utils.RequestBuilder;

@Service
public class ZabbixApiUserMacroService {
	@Autowired
	private ZabbixInitializerService initializerService;

	@Autowired
	private MessageSource messageSource;

	/**
	 * 
	 * @param userMacroGet
	 * @return
	 */
	public ResponseEntity<?> userMacro(UserMacroGet userMacroGet) {
		JSONObject response = null;
		if (initializerService.loginValidate()) {
			Request request = RequestBuilder.newBuilder().method("usermacro.get")
					.paramEntry("output", userMacroGet.getOutput())
					.paramEntry("hostids", userMacroGet.getHostids())
					.build();
			response = initializerService.zabbixApi.call(request);
			ServiceResponse<JSONObject> data = new ServiceResponse<JSONObject>("success", response);
			return new ResponseEntity<Object>(data, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ErrorApiResponse("zabbix server is unavailable"), HttpStatus.BAD_REQUEST);
		}

	}
}
