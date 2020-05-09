package com.intcomcorp.intcomcorpApplication.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.intcomcorp.intcomcorpApplication.service.impl.ZabbixApiApplicationService;
import com.intcomcorp.intcomcorpApplication.utils.Constants;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "zabbixapi/applications", produces = "application/json")
@Api(value = "ZABBIX APPLICATION", tags = { "ZABBIX APPLICATION" })
public class ZabbixApiApplicationController {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private ZabbixApiApplicationService zabbixApiApplicationService;

	/**
	 * 
	 * @param applicationGet
	 * @return
	 */
	@GetMapping
	public ResponseEntity<?> getApplication(@RequestParam("output") String extend,
			@RequestParam("hostids") String hostids, @RequestParam("sortfield") String name,
			HttpServletRequest request) {
		log.info(messageSource.getMessage(Constants.NEW_REQ, new Object[] { request.getRequestURI() }, Locale.US));
		return zabbixApiApplicationService.application(extend, hostids, name);

	}
}
