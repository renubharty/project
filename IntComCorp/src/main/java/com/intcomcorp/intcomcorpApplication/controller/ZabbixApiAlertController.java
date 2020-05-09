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

import com.intcomcorp.intcomcorpApplication.service.impl.ZabbixApiAlertService;
import com.intcomcorp.intcomcorpApplication.utils.Constants;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "zabbixapi/alert", produces = "application/json")
@Api(value = "ZABBIX ALERT", tags = { "ZABBIX ALERT" })
public class ZabbixApiAlertController {
	@Autowired
	private MessageSource messageSource;
	@Autowired
	private ZabbixApiAlertService zabbixApiAlertService;

	@GetMapping
	public ResponseEntity<?> getAlert(@RequestParam("output") String extend,
			@RequestParam("actionids") String actionids, HttpServletRequest request) {
		log.info(messageSource.getMessage(Constants.NEW_REQ, new Object[] { request.getRequestURI() }, Locale.US));
		return zabbixApiAlertService.alert(extend, actionids);

	}
}
