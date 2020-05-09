package com.intcomcorp.intcomcorpApplication.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.intcomcorp.intcomcorpApplication.dto.Request;
import com.intcomcorp.intcomcorpApplication.dto.ZabbixDeleteRequest;
import com.intcomcorp.intcomcorpApplication.dto.request.TemplateCreate;
import com.intcomcorp.intcomcorpApplication.dto.request.TemplateDelete;
import com.intcomcorp.intcomcorpApplication.dto.request.TemplateUpdate;
import com.intcomcorp.intcomcorpApplication.dto.response.ApiResponse;
import com.intcomcorp.intcomcorpApplication.dto.response.ErrorResponse;
import com.intcomcorp.intcomcorpApplication.dto.response.ZabbixApiResponse;
import com.intcomcorp.intcomcorpApplication.utils.RequestBuilder;

@Service
public class ZabbixApiTemplateService {
	@Autowired
	private ZabbixInitializerService initializerService;

	public ResponseEntity<ApiResponse> getTemplates(String extend, String[] host) {
		JSONObject response = null;
		if (initializerService.loginValidate()) {
			Request request = RequestBuilder.newBuilder().method("template.get")
					.paramEntry("output", extend).paramEntry("filter", host)
					.build();
			response = initializerService.zabbixApi.call(request);

			return new ResponseEntity<>(new ZabbixApiResponse(response), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ErrorResponse("zabbix server is unavailable"), HttpStatus.BAD_REQUEST);
		}
	}

	public ResponseEntity<ApiResponse> templateCreate(TemplateCreate templateCreate) {
		JSONObject response = null;
		if (initializerService.loginValidate()) {
			Request request = RequestBuilder.newBuilder().method("template.create")
					.paramEntry("host", templateCreate.getHost()).paramEntry("groups", templateCreate.getGroup())
					.paramEntry("hosts", templateCreate.getHosts()).paramEntry("tags", templateCreate.getTags())

					.build();
			response = initializerService.zabbixApi.call(request);

			return new ResponseEntity<>(new ZabbixApiResponse(response), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ErrorResponse("zabbix server is unavailable"), HttpStatus.BAD_REQUEST);

		}
	}

	public ResponseEntity<ApiResponse> templateUpdate(TemplateUpdate templateUpdate) {

		JSONObject response = null;
		if (initializerService.loginValidate()) {
			Request request = RequestBuilder.newBuilder().method("template.update")
					.paramEntry("templateid", templateUpdate.getTemplateid())
					.paramEntry("name", templateUpdate.getName()).build();
			response = initializerService.zabbixApi.call(request);

			return new ResponseEntity<>(new ZabbixApiResponse(response), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ErrorResponse("zabbix server is unavailable"), HttpStatus.BAD_REQUEST);
		}
	}

	public ResponseEntity<ApiResponse> templateDelete(TemplateDelete templateDelete) {
		JSONObject response = null;
		if (initializerService.loginValidate()) {
			ZabbixDeleteRequest deleteRequest = new ZabbixDeleteRequest();
			deleteRequest.setParams(templateDelete.getTemplateids());
			deleteRequest.setMethod("template.delete");
			response = initializerService.zabbixApi.call(deleteRequest);
			return new ResponseEntity<>(new ZabbixApiResponse(response), HttpStatus.OK);

		} else {
			return new ResponseEntity<>(new ErrorResponse("zabbix server is unavailable"), HttpStatus.BAD_REQUEST);
		}

	}
}
