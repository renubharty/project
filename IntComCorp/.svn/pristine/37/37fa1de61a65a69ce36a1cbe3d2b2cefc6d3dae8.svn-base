package com.intcomcorp.intcomcorpApplication.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.intcomcorp.intcomcorpApplication.dto.Request;
import com.intcomcorp.intcomcorpApplication.dto.request.TriggerGet;
import com.intcomcorp.intcomcorpApplication.dto.response.ErrorResponse;
import com.intcomcorp.intcomcorpApplication.dto.response.ServiceResponse;
import com.intcomcorp.intcomcorpApplication.dto.response.ZabbixApiResponse;
import com.intcomcorp.intcomcorpApplication.utils.RequestBuilder;

@Service
public class ZabbixApiTriggerService {
	@Autowired
	private ZabbixInitializerService initializerService;
	@Autowired
	private MessageSource messageSource;

	public ResponseEntity<?> trigger(TriggerGet triggerGet) {
		JSONObject response = null;
		if (initializerService.loginValidate()) {
			Request request = RequestBuilder.newBuilder().method("trigger.get")
					.paramEntry("triggerids", triggerGet.getTriggerids())
					.paramEntry("output", triggerGet.getOutput())
					.paramEntry("selectFunctions", triggerGet.getSelectFunctions()).build();
			response = initializerService.zabbixApi.call(request);
			ServiceResponse<JSONObject> data = new ServiceResponse<JSONObject>("success", response);

			return new ResponseEntity<>(new ZabbixApiResponse(response), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ErrorResponse("zabbix server is unavailable"), HttpStatus.BAD_REQUEST);
		}

	}
}
