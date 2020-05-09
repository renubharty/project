package com.intcomcorp.intcomcorpApplication.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.intcomcorp.intcomcorpApplication.dto.request.TemplateCreate;
import com.intcomcorp.intcomcorpApplication.dto.request.TemplateDelete;
import com.intcomcorp.intcomcorpApplication.dto.request.TemplateUpdate;
import com.intcomcorp.intcomcorpApplication.dto.response.ApiResponse;
import com.intcomcorp.intcomcorpApplication.service.impl.ZabbixApiTemplateService;
import com.intcomcorp.intcomcorpApplication.utils.Constants;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "zabbixapi/templates", produces = "application/json")
@Api(value = "ZABBIX TEMPLATE", tags = { "ZABBIX TEMPLATE" })
public class ZabbixApiTemplateController {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private ZabbixApiTemplateService zabbixApiTemplateService;

	@GetMapping
	public ResponseEntity<ApiResponse> getTemplates(@RequestParam("output") String extend,
			@RequestParam("filter") String[] host, HttpServletRequest request) {
		log.info(messageSource.getMessage(Constants.NEW_REQ, new Object[] { request.getRequestURI() }, Locale.US));
		return zabbixApiTemplateService.getTemplates(extend, host);
	}

	@PostMapping
	public ResponseEntity<ApiResponse> templateCreate(@RequestBody TemplateCreate templateCreate,
			HttpServletRequest request) {
		log.info(messageSource.getMessage(Constants.NEW_REQ, new Object[] { request.getRequestURI() }, Locale.US));
		return zabbixApiTemplateService.templateCreate(templateCreate);
	}

	@PutMapping("{name}")
	public ResponseEntity<ApiResponse> templateUpdate(@RequestBody TemplateUpdate templateUpdate,
			HttpServletRequest request) {
		log.info(messageSource.getMessage(Constants.NEW_REQ, new Object[] { request.getRequestURI() }, Locale.US));
		return zabbixApiTemplateService.templateUpdate(templateUpdate);
	}

	@DeleteMapping
	public ResponseEntity<ApiResponse> templateDelete(@RequestBody TemplateDelete templateDelete,
			HttpServletRequest request) {
		log.info(messageSource.getMessage(Constants.NEW_REQ, new Object[] { request.getRequestURI() }, Locale.US));
		return zabbixApiTemplateService.templateDelete(templateDelete);
	}

}
