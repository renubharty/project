package com.intcomcorp.intcomcorpApplication.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.intcomcorp.intcomcorpApplication.dto.request.HostGroup;
import com.intcomcorp.intcomcorpApplication.model.User;
import com.intcomcorp.intcomcorpApplication.service.UserService;
import com.intcomcorp.intcomcorpApplication.utils.Constants;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MainController {
	@Autowired
	private MessageSource messageSource;
	@Autowired
	private UserService userService;

	/*
	 * @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN','ROLE_SUPERADMIN')")
	 * 
	 * @GetMapping("/") public String root(Model model, Principal principal) {
	 * 
	 * User user = userService.findByEmail(principal.getName());
	 * 
	 * model.addAttribute("user", user); model.addAttribute("user.role",
	 * user.getRoles()); // log.info(messageSource.getMessage(Constants.NEW_REQ, new
	 * Object[] { request.getRequestURI() }, Locale.US)); return "home/home"; }
	 */

	@GetMapping("/hostgroups")
	public Model hostgroups(Model model, HttpServletRequest request) {
		List<HostGroup> hostGroups = new ArrayList<>();
		HostGroup g = new HostGroup();
		// Testing code 
//		g.setGroupid("1");
//		g.setName("test1");
//		hostGroups.add(g);
//		g = new HostGroup();
//		g.setGroupid("2");
//		g.setName("test2");
//		hostGroups.add(g);
		model.addAttribute("hostGroups", hostGroups);

		log.info(messageSource.getMessage(Constants.NEW_REQ, new Object[] { request.getRequestURI() }, Locale.US));
		return model;
	}

	@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN','ROLE_SUPERADMIN')")
	@GetMapping("/login")
	public String login(Model model, HttpServletRequest request) {
		log.info(messageSource.getMessage(Constants.NEW_REQ, new Object[] { request.getRequestURI() }, Locale.US));
		return "login/login";
	}

	@GetMapping("/monitoring")
	public String monitoring(Model model, HttpServletRequest request) {
		log.info(messageSource.getMessage(Constants.NEW_REQ, new Object[] { request.getRequestURI() }, Locale.US));
		return "monitoring/monitoring";
	}
	
	@GetMapping("/administration")
	public String administration(Model model, HttpServletRequest request) {
		log.info(messageSource.getMessage(Constants.NEW_REQ, new Object[] { request.getRequestURI() }, Locale.US));
		return "administration/administration";
	}
	

	@RequestMapping("/problems")
	public String problems(Model model, HttpServletRequest request) {
		log.info(messageSource.getMessage(Constants.NEW_REQ, new Object[] { request.getRequestURI() }, Locale.US));
		return "monitoring/problems";
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SUPERADMIN')")
	@GetMapping("/configuration")
	public String configuration(Model model, HttpServletRequest request) {
		log.info(messageSource.getMessage(Constants.NEW_REQ, new Object[] { request.getRequestURI() }, Locale.US));
		return "configuration/configuration";
	}

	@GetMapping(value = "/usermacro")
	public String getUserMacro(Model model, HttpServletRequest request) {
		log.info(messageSource.getMessage(Constants.NEW_REQ, new Object[] { request.getRequestURI() }, Locale.US));
		return "zabbix/usermacro/usermacroget";
	}

	@GetMapping(value = "/trigger")
	public String getTrigger(Model model, HttpServletRequest request) {
		log.info(messageSource.getMessage(Constants.NEW_REQ, new Object[] { request.getRequestURI() }, Locale.US));
		return "zabbix/trigger/triggerget";
	}



	@GetMapping(value = "/hostsget")
	public String getHostsDetails(Model model, HttpServletRequest request) {
		log.info(messageSource.getMessage(Constants.NEW_REQ, new Object[] { request.getRequestURI() }, Locale.US));
		return "hosts/hostsget";
	}

}