package com.intcomcorp.intcomcorpApplication.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.intcomcorp.intcomcorpApplication.dto.request.TriggerGet;
import com.intcomcorp.intcomcorpApplication.service.impl.ZabbixApiTriggerService;
import com.intcomcorp.intcomcorpApplication.utils.Constants;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "zabbixapi/triggers", produces = "application/json")
@Api(value = "ZABBIX TRIGGER", tags = { "ZABBIX TRIGGER" })
public class ZabbixApiTriggerController {
	@Autowired
	private MessageSource messageSource;

	@Autowired
	private ZabbixApiTriggerService zabbixApiTriggerService;

	@PostMapping(value = "/get")
	public ResponseEntity<?> triggerGet(@RequestBody TriggerGet triggerGet, HttpServletRequest request) {
		log.info(messageSource.getMessage(Constants.NEW_REQ, new Object[] { request.getRequestURI() }, Locale.US));
		return zabbixApiTriggerService.trigger(triggerGet);
	}

}
