package com.intcomcorp.intcomcorpApplication.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.intcomcorp.intcomcorpApplication.dto.request.UserUpdate;
import com.intcomcorp.intcomcorpApplication.dto.response.ApiResponse;
import com.intcomcorp.intcomcorpApplication.service.impl.ZabbixApiUserService;
import com.intcomcorp.intcomcorpApplication.utils.Constants;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "zabbixapi/users")
@Api(value = "ZABBIX USER", tags = { "ZABBIX USER" })
public class ZabbixApiUserController {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private ZabbixApiUserService zabbixApiUserService;

	@PreAuthorize("hasAnyRole('USER', 'ADMIN','CUSTOMER')")
	@GetMapping
	public ResponseEntity<ApiResponse> getUserDetails(@PathVariable String extend, HttpServletRequest request) {
		log.info(messageSource.getMessage(Constants.NEW_REQ, new Object[] { request.getRequestURI() }, Locale.US));
		return zabbixApiUserService.userDetails(extend);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("{byName}")
	public ResponseEntity<ApiResponse> userUpdate(@RequestBody @Valid UserUpdate userUpdate,
			HttpServletRequest request) {
		log.info(messageSource.getMessage(Constants.NEW_REQ, new Object[] { request.getRequestURI() }, Locale.US));
		return zabbixApiUserService.update(userUpdate);
	}

}
