package com.intcomcorp.intcomcorpApplication.controller;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.intcomcorp.intcomcorpApplication.dto.Mail;
import com.intcomcorp.intcomcorpApplication.dto.PasswordForgotDto;
import com.intcomcorp.intcomcorpApplication.iccn.repo.PasswordResetTokenRepository;
import com.intcomcorp.intcomcorpApplication.model.PasswordResetToken;
import com.intcomcorp.intcomcorpApplication.model.User;
import com.intcomcorp.intcomcorpApplication.service.UserService;
import com.intcomcorp.intcomcorpApplication.service.impl.EmailService;
import com.intcomcorp.intcomcorpApplication.utils.Constants;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
@RequestMapping("/forgot-password")
public class PasswordForgotController {
	
	
	@Autowired
	private UserService userService;
	@Autowired
	private PasswordResetTokenRepository tokenRepository;
	@Autowired
	private EmailService emailService;

	@Autowired
	private MessageSource messageSource;

	@ModelAttribute("forgotPasswordForm")
	public PasswordForgotDto forgotPasswordDto(HttpServletRequest request) {
		log.info(messageSource.getMessage(Constants.NEW_REQ, new Object[] { request.getRequestURI() }, Locale.US));
		return new PasswordForgotDto();
	}

	@GetMapping
	public String displayForgotPasswordPage(HttpServletRequest request) {
		log.info(messageSource.getMessage(Constants.NEW_REQ, new Object[] { request.getRequestURI() }, Locale.US));
		return "email/forgot-password";
	}

	@PostMapping
	public String processForgotPasswordForm(@ModelAttribute("forgotPasswordForm") @Valid PasswordForgotDto form,
			BindingResult result, HttpServletRequest request) {

		if (result.hasErrors()) {
			return "email/forgot-password";
		}

		User user = userService.findByEmail(form.getEmail());
		if (user == null) {
			result.rejectValue("email", null, "We could not find an account for that e-mail address.");
			return "email/forgot-password";
		}

		PasswordResetToken token = new PasswordResetToken();
		token.setToken(UUID.randomUUID().toString());
		token.setUser(user);
		token.setExpiryDate(30);
		tokenRepository.save(token);



		Mail mail = new Mail();
		mail.setFrom(messageSource.getMessage("spring.mail.username", null, Locale.US));
		mail.setTo(user.getEmail());
		mail.setSubject("Password reset request");

		Map<String, Object> model = new HashMap<>();
		model.put("token", token);
		model.put("user", user);
		model.put("signature", "http://intcomcorp.com");
		String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
		model.put("resetUrl", url + "/reset-password?token=" + token.getToken());
		mail.setModel(model);
		emailService.sendEmail(mail);

		return "redirect:/forgot-password?success";

	}

}
