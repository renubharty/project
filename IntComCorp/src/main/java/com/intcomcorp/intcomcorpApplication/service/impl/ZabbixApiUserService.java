package com.intcomcorp.intcomcorpApplication.service.impl;

import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.intcomcorp.intcomcorpApplication.dto.Request;
import com.intcomcorp.intcomcorpApplication.dto.request.UserCreate;
import com.intcomcorp.intcomcorpApplication.dto.request.UserUpdate;
import com.intcomcorp.intcomcorpApplication.dto.response.ApiResponse;
import com.intcomcorp.intcomcorpApplication.dto.response.ErrorResponse;
import com.intcomcorp.intcomcorpApplication.dto.response.ZabbixApiResponse;
import com.intcomcorp.intcomcorpApplication.utils.RequestBuilder;

@Service
public class ZabbixApiUserService {

	@Autowired
	private ZabbixInitializerService initializerService;

	@Autowired
	private MessageSource messageSource;

	/**
	 * 
	 * @return
	 * @throws URISyntaxException
	 */
	public ResponseEntity<ApiResponse> login() throws URISyntaxException {
		JSONObject response = null;
		if (initializerService.loginValidate()) {
			Request request = RequestBuilder.newBuilder().method("user.get").paramEntry("output", "extend").build();
			response = initializerService.zabbixApi.call(request);
			return new ResponseEntity<>(new ZabbixApiResponse(response), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ErrorResponse("zabbix server is unavailable"), HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * 
	 * @return
	 */
	public ResponseEntity<ApiResponse> logout() {
		JSONObject response = null;
		if (initializerService.loginValidate()) {
			Request request = RequestBuilder.newBuilder().method("user.logout").build();
			response = initializerService.zabbixApi.call(request);
			return new ResponseEntity<>(new ZabbixApiResponse(response), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ErrorResponse("zabbix server is unavailable"), HttpStatus.BAD_REQUEST);
		}

	}

	/**
	 * 
	 * @return
	 */
	public ResponseEntity<ApiResponse> userDetails(String extend) {
		JSONObject response = null;
		if (initializerService.loginValidate()) {
			Request request = RequestBuilder.newBuilder().method("user.get").paramEntry("output", extend).build();
			response = initializerService.zabbixApi.call(request);
			return new ResponseEntity<>(new ZabbixApiResponse(response), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ErrorResponse("zabbix server is unavailable"), HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * 
	 * @param userCreate
	 * @return
	 */
	public ResponseEntity<ApiResponse> create(UserCreate userCreate) {
		JSONObject response = null;
		if (initializerService.loginValidate()) {
			Request request = RequestBuilder.newBuilder().method("user.create")
					.paramEntry("alias", userCreate.getAlias()).paramEntry("passwd", userCreate.getPasswd())
					.paramEntry("usrgrps", userCreate.getUserGroups())
					.paramEntry("user_medias", userCreate.getUserMedias()).build();
			response = initializerService.zabbixApi.call(request);
			return new ResponseEntity<>(new ZabbixApiResponse(response), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ErrorResponse("zabbix server is unavailable"), HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * 
	 * @param userUpdate
	 * @return
	 */
	public ResponseEntity<ApiResponse> update(UserUpdate userUpdate) {
		JSONObject response = null;
		if (initializerService.loginValidate()) {
			Request request = RequestBuilder.newBuilder().method("user.update")
					.paramEntry("userid", userUpdate.getUserid()).paramEntry("name", userUpdate.getName())
					.paramEntry("surname", userUpdate.getSurname()).build();
			response = initializerService.zabbixApi.call(request);
			return new ResponseEntity<>(new ZabbixApiResponse(response), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ErrorResponse("zabbix server is unavailable"), HttpStatus.BAD_REQUEST);
		}

	}
}
