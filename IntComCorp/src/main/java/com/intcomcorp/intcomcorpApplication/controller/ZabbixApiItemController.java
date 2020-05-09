package com.intcomcorp.intcomcorpApplication.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.intcomcorp.intcomcorpApplication.dto.request.CreatingAnItem;
import com.intcomcorp.intcomcorpApplication.dto.request.ItemCreate;
import com.intcomcorp.intcomcorpApplication.dto.request.ItemGet;
import com.intcomcorp.intcomcorpApplication.dto.response.ApiResponse;
import com.intcomcorp.intcomcorpApplication.service.impl.ZabbixApiItemService;
import com.intcomcorp.intcomcorpApplication.utils.Constants;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "zabbixapi/item", produces = "application/json")
@Api(value = "ZABBIX ITEM",tags = { "ZABBIX ITEM" })
public class ZabbixApiItemController {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private ZabbixApiItemService zabbixApiItemService;

	/**
	 * 
	 * @param itemCreate
	 * @param request
	 * @return
	 */
	@PostMapping(value = "/create")
	public ResponseEntity<ApiResponse> itemCreate(@RequestBody @Valid ItemCreate itemCreate,
			HttpServletRequest request) {
		log.info(messageSource.getMessage(Constants.NEW_REQ, new Object[] { request.getRequestURI() }, Locale.US));
		return zabbixApiItemService.create(itemCreate);
	}

	/**
	 * 
	 * @param itemGet
	 * @param request
	 * @return
	 */

	@PostMapping(value = "/get")
	public ResponseEntity<ApiResponse> itemGet(@RequestBody @Valid ItemGet itemGet, HttpServletRequest request) {
		log.info(messageSource.getMessage(Constants.NEW_REQ, new Object[] { request.getRequestURI() }, Locale.US));
		return zabbixApiItemService.get(itemGet);
	}

	@PostMapping(value = "/createItem")
	public ResponseEntity<ApiResponse> creatingAnItem(@RequestBody @Valid CreatingAnItem creatingAnItem,
			HttpServletRequest request) {
		log.info(messageSource.getMessage(Constants.NEW_REQ, new Object[] { request.getRequestURI() }, Locale.US));
		return zabbixApiItemService.createItem(creatingAnItem);
	}

}
