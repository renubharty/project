package com.intcomcorp.intcomcorpApplication.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.intcomcorp.intcomcorpApplication.dto.response.UserGet;
import com.intcomcorp.intcomcorpApplication.service.impl.ZabbixUserService;
import com.intcomcorp.intcomcorpApplication.utils.Constants;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value = "zabbix/users", produces = "application/json")
@Api(value = "ZABBIX USER",  tags = { "ZABBIX USER" })
public class ZabbixUserController {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private ZabbixUserService userService;

	@GetMapping(value = "/get")
	public String getUser(Model model, HttpServletRequest request) throws JSONException {
		log.info(messageSource.getMessage(Constants.NEW_REQ, new Object[] { request.getRequestURI() }, Locale.US));
		List<UserGet> userGet = userService.get();
		model.addAttribute("userList", userGet);
		return "zabbix/user/userget";
	}



	@PostMapping(value = "editUser/{userId}")
	public String updateUser(@PathVariable String userId, UserGet userGet, HttpServletRequest request)
			throws JSONException {
		log.info(messageSource.getMessage(Constants.NEW_REQ, new Object[] { request.getRequestURI() }, Locale.US));
		userService.updateUser(userGet);
		return "redirect:/zabbix/user/get";
	}
}
