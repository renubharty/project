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

import com.intcomcorp.intcomcorpApplication.dto.response.ApiResponse;
import com.intcomcorp.intcomcorpApplication.service.impl.ZabbixApiGraphService;
import com.intcomcorp.intcomcorpApplication.utils.Constants;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "zabbixapi/graphs", produces = "application/json")
@Api(value = "ZABBIX GRAPH",  tags = { "ZABBIX GRAPH" })
public class ZabbixApiGraphController {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private ZabbixApiGraphService zabbixApiGraphService;

	/**
	 * 
	 * @param extend
	 * @param hostids
	 * @param name
	 * @param request
	 * @return
	 */
	@GetMapping
	public ResponseEntity<ApiResponse> getgraph(@RequestParam("output") String extend,
			@RequestParam("hostids") Long hostids, @RequestParam("sortfield") String name, HttpServletRequest request) {
		log.info(messageSource.getMessage(Constants.NEW_REQ, new Object[] { request.getRequestURI() }, Locale.US));
		return zabbixApiGraphService.graph(extend, hostids, name);
	}
}
