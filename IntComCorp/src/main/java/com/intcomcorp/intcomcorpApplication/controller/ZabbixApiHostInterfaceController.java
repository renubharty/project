package com.intcomcorp.intcomcorpApplication.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.intcomcorp.intcomcorpApplication.dto.request.HostInterfaceCreate;
import com.intcomcorp.intcomcorpApplication.dto.response.ApiResponse;
import com.intcomcorp.intcomcorpApplication.service.impl.ZabbixApiHostInterfaceService;
import com.intcomcorp.intcomcorpApplication.utils.Constants;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "zabbixapi/hostinterfaces")
@Api(value = "ZABBIX HOSTINTERFACE",  tags = { "ZABBIX HOSTINTERFACE" })
public class ZabbixApiHostInterfaceController {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private ZabbixApiHostInterfaceService zabbixApiHostInterfaceService;

	/**
	 * Retrieve all data about the interfaces used by host
	 * 
	 * @param hostInterfaceGet
	 * @param request
	 * @return
	 */
	@GetMapping
	public ResponseEntity<?> getHostInterfaces(@RequestParam("output") String extend,
			@RequestParam("hostids") String hostids, HttpServletRequest request) {
		log.info(messageSource.getMessage(Constants.NEW_REQ, new Object[] { request.getRequestURI() }, Locale.US));
		return zabbixApiHostInterfaceService.hostInterfaces(extend, hostids);

	}

	/**
	 * 
	 * @param hostInterfaceCreate
	 * @param request
	 * @return
	 */
	@PostMapping
	public ResponseEntity<ApiResponse> hostInterfaceCreate(@RequestBody HostInterfaceCreate hostInterfaceCreate,
			HttpServletRequest request) {
		log.info(messageSource.getMessage(Constants.NEW_REQ, new Object[] { request.getRequestURI() }, Locale.US));
		return zabbixApiHostInterfaceService.hostInterfaceCreate(hostInterfaceCreate);
	}
}
