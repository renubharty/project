package com.intcomcorp.intcomcorpApplication.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.intcomcorp.intcomcorpApplication.dto.request.HostDelete;
import com.intcomcorp.intcomcorpApplication.dto.request.HostUpdate;
import com.intcomcorp.intcomcorpApplication.dto.request.JsHostCreate;
import com.intcomcorp.intcomcorpApplication.service.impl.HostAdaptor;
import com.intcomcorp.intcomcorpApplication.service.impl.ZabbixApiHostService;
import com.intcomcorp.intcomcorpApplication.utils.Constants;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "zabbixapi/hosts/", produces = "application/json")
@Api(value = "ZABBIX HOST", tags = { "ZABBIX HOST" })
public class ZabbixApiHostController {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private ZabbixApiHostService zabbixApiHostService;

	@Autowired
	private HostAdaptor adaptor;

	// Retrieve all data about hosts by name
	/**
	 * 
	 * @param hostNames
	 * @param request
	 * @return
	 */
	@GetMapping("{hostNames}")
	public ResponseEntity<?> getHostDetails(@PathVariable String[] hostNames, HttpServletRequest request) {
		log.info(messageSource.getMessage(Constants.NEW_REQ, new Object[] { request.getRequestURI() }, Locale.US));
		return zabbixApiHostService.hostsDetails(hostNames);
	}

	// Retrieve names of the groups host but no host details themselves
	/**
	 * 
	 * @param hostid
	 * @param hosts
	 * @param extend
	 * @param request
	 * @return
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("groups")
	public ResponseEntity<?> getHostGroups(@RequestParam("output") String[] hostid,
			@RequestParam("filter") String[] hosts, @RequestParam("selectGroups") String extend,
			HttpServletRequest request) {
		log.info(messageSource.getMessage(Constants.NEW_REQ, new Object[] { request.getRequestURI() }, Locale.US));
		return zabbixApiHostService.hostGroups(hostid, hosts, extend);
	}

	/**
	 * Create a host with an IP interface and tags, add it to a group, link a
	 * template to it and set the MAC addresses in the host inventory
	 * 
	 * @param hostCreate
	 * @param request
	 * @return
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/createhost")
	public ResponseEntity<?> hostCreate(@RequestBody JsHostCreate createHostRequest, HttpServletRequest request) {
		log.info(messageSource.getMessage(Constants.NEW_REQ, new Object[] { request.getRequestURI() }, Locale.US));
				return zabbixApiHostService.hostCreate(adaptor.adapt(createHostRequest));

		/*
		 * log.info(hostCreate.toString()); return null
		 */
	}

	/**
	 * Update host status 0=enable or 1=disable
	 * 
	 * @param hostUpdate
	 * @param request
	 * @return
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("{hostids}")
	public ResponseEntity<?> hostUpdate(@RequestBody @Valid HostUpdate hostUpdate, HttpServletRequest request) {
		log.info(messageSource.getMessage(Constants.NEW_REQ, new Object[] { request.getRequestURI() }, Locale.US));
		return zabbixApiHostService.update(hostUpdate);
	}

	/**
	 * Delete multiple hosts
	 * 
	 * @param hostDelete
	 * @param request
	 * @return
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("{hostids}")
	public ResponseEntity<?> hostDelete(@RequestBody @Valid HostDelete hostDelete, HttpServletRequest request) {
		log.info(messageSource.getMessage(Constants.NEW_REQ, new Object[] { request.getRequestURI() }, Locale.US));
		return zabbixApiHostService.delete(hostDelete);
	}

}
