package com.intcomcorp.intcomcorpApplication.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.intcomcorp.intcomcorpApplication.model.Organization;
import com.intcomcorp.intcomcorpApplication.service.impl.OrganizationServiceimpl;
import com.intcomcorp.intcomcorpApplication.utils.Constants;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/organization")
public class OrganizationController {
	@Autowired
	private MessageSource messageSource;
	@Autowired
	private OrganizationServiceimpl organizationServiceimpl;

	@ModelAttribute("organizationDto")
	public Organization organizationDto(HttpServletRequest request) {
		log.info(messageSource.getMessage(Constants.NEW_REQ, new Object[] { request.getRequestURI() }, Locale.US));
		return new Organization();
	}

	@GetMapping
	public String showorganization(HttpServletRequest request) {
		log.info(messageSource.getMessage(Constants.NEW_REQ, new Object[] { request.getRequestURI() }, Locale.US));
		return "administration/organization";
	}

	/**
	 * 
	 * @param userDto
	 * @param result
	 * @param request
	 * @return
	 */
	@PostMapping
	public String saveOrganization(Model model,@ModelAttribute("organizationDto") @Valid Organization organizationDto,
			BindingResult result, HttpServletRequest request) {
		log.info("Organization controller method saveOrganization begin");
		log.info(messageSource.getMessage(Constants.NEW_REQ, new Object[] { request.getRequestURI() }, Locale.US));

		if (result.hasErrors()) {
			return showorganization(request); 
		}
    	organizationServiceimpl.save(organizationDto);
    	model.addAttribute("successMsg","Organization Created Successfully!");
    	model.addAttribute("organizationDto",new Organization());
		return "/administration/organization";
	}

	@GetMapping(value = { "/access_denied" })
	public String accessDenied(HttpServletRequest request) {
		log.info(messageSource.getMessage(Constants.NEW_REQ, new Object[] { request.getRequestURI() }, Locale.US));
		return "errors/access_denied";
	}

}
