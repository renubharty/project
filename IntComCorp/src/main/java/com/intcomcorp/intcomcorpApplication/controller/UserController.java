package com.intcomcorp.intcomcorpApplication.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.intcomcorp.intcomcorpApplication.dto.EmailDto;
import com.intcomcorp.intcomcorpApplication.dto.PasswordResetDto;
import com.intcomcorp.intcomcorpApplication.dto.UserRegistrationDto;
import com.intcomcorp.intcomcorpApplication.model.Reseller;
import com.intcomcorp.intcomcorpApplication.model.User;
import com.intcomcorp.intcomcorpApplication.service.EmailService;
import com.intcomcorp.intcomcorpApplication.service.UserService;
import com.intcomcorp.intcomcorpApplication.service.impl.ResellerService;
import com.intcomcorp.intcomcorpApplication.utils.Constants;
import com.intcomcorp.intcomcorpApplication.utils.RandomUniquePassword;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private MessageSource messageSource;
	@Autowired
	private UserService userService;
	@Autowired
	private ResellerService resellerService;
	@Autowired
	EmailService emailService;
	@Value("${email.from}")
	private String fromEmail;

	@Value("${email.subject}")
	private String subject;

	@ModelAttribute("user")
	public UserRegistrationDto userRegistrationDto(HttpServletRequest request) {
		log.info(messageSource.getMessage(Constants.NEW_REQ, new Object[] { request.getRequestURI() }, Locale.US));
		return new UserRegistrationDto();
	}

	@GetMapping("/registration")
	public String showRegistrationForm(Model model, HttpServletRequest request) {
		log.info(messageSource.getMessage(Constants.NEW_REQ, new Object[] { request.getRequestURI() }, Locale.US));
		List<Reseller> resellerList = resellerService.getAll();
		model.addAttribute("resellerList", resellerList);
		model.addAttribute("user",new UserRegistrationDto());
		return "registration/registration";
	}

	/**
	 * 
	 * @param userDto
	 * @param result
	 * @param request
	 * @return
	 */
	@PostMapping("/registration")
	public String registerUserAccount(@ModelAttribute("user") @Valid UserRegistrationDto userDto, BindingResult result,
			HttpServletRequest request, Model model) {
		log.info(messageSource.getMessage(Constants.NEW_REQ, new Object[] { request.getRequestURI() }, Locale.US));

		User existing = userService.findByEmail(userDto.getEmail());
		if (existing != null) {
			result.rejectValue("email", null, "There is already an account registered with that email");
			model.addAttribute("resellerList", resellerService.getAll());
		}

		if (result.hasErrors()) {
			List<Reseller> resellerList = resellerService.getAll();
			model.addAttribute("resellerList", resellerList);
			return "registration/registration";
		}

		Reseller res = resellerService.findById(Long.parseLong(userDto.getResId()));
		userDto.setReseller(res);
		User user = userService.save(userDto);
		userDto.setPassword(RandomUniquePassword.generateRandomPassword(8));
		EmailDto emailDto = new EmailDto();
		PasswordResetDto passwordResetDto = new PasswordResetDto();
		passwordResetDto.setUser(user);
		if (emailService.createPasswordResetToken(passwordResetDto)) {
			// Send password reset link to Reseller email id
			emailDto.setFrom(fromEmail);
			emailDto.setTo(userDto.getEmail());
			emailDto.setSubject(subject);
			Map<String, Object> emailModel = new HashMap<>();
			emailModel.put("token", passwordResetDto.getToken());
			emailModel.put("user", user);
			emailModel.put("userPassword", userDto.getPassword());
			emailModel.put("signature", "http://intcomcorp.com");
			final String URL = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/intcomcorpApplication";
			emailModel.put("resetUrl", URL + "/reset-password?token=" + passwordResetDto.getToken());
			emailDto.setModel(emailModel);
			emailService.sendMail(emailDto);
		}

		model.addAttribute("successMsg", "User Created Successfully");
		return showRegistrationForm(model, request);
	}

	@GetMapping(value = { "/access_denied" })
	public String accessDenied(HttpServletRequest request) {
		log.info(messageSource.getMessage(Constants.NEW_REQ, new Object[] { request.getRequestURI() }, Locale.US));
		return "errors/access_denied";
	}

}
