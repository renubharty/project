
package com.intcomcorp.intcomcorpApplication.controller;
import java.security.Principal;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.intcomcorp.intcomcorpApplication.model.User;
import com.intcomcorp.intcomcorpApplication.service.UserService;
import com.intcomcorp.intcomcorpApplication.service.impl.ProblemService;
import com.intcomcorp.intcomcorpApplication.utils.Constants;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ProblemController {
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private ProblemService problemService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public String showDashboard(Model model, HttpServletRequest request,Principal principal) {
		log.info(messageSource.getMessage(Constants.NEW_REQ, new Object[] { request.getRequestURI() }, Locale.US));
		  User user = userService.findByEmail(principal.getName());
		  model.addAttribute("user", user); model.addAttribute("user.role",
		  user.getRoles());
		  model.addAttribute("problemList", problemService.getAllHostProblem(principal));
		return "home/home";
	}
	
}
