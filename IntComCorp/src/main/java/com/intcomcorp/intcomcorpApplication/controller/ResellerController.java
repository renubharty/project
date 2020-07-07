package com.intcomcorp.intcomcorpApplication.controller;

import static com.intcomcorp.intcomcorpApplication.utils.Util.currentUserName;
import static com.intcomcorp.intcomcorpApplication.utils.Util.hasRole;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.intcomcorp.intcomcorpApplication.dto.EmailDto;
import com.intcomcorp.intcomcorpApplication.dto.PasswordResetDto;
import com.intcomcorp.intcomcorpApplication.model.Reseller;
import com.intcomcorp.intcomcorpApplication.model.User;
import com.intcomcorp.intcomcorpApplication.service.EmailService;
import com.intcomcorp.intcomcorpApplication.service.UserService;
import com.intcomcorp.intcomcorpApplication.service.impl.ProblemService;
import com.intcomcorp.intcomcorpApplication.service.impl.ResellerService;
import com.intcomcorp.intcomcorpApplication.utils.Constants;
import com.intcomcorp.intcomcorpApplication.utils.RandomUniquePassword;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/reseller")
@Controller
public class ResellerController {
	@Autowired
	private MessageSource messageSource;
	@Autowired
	private ResellerService resellerService;
	@Autowired
	EmailService emailService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private ProblemService problemService;
	
	@Value("${email.from}")
	private String fromEmail;
	
	@Value("${email.subject}")
	private String subject;
	
	@ModelAttribute("reseller")
	public Reseller reseller(HttpServletRequest request) {
		log.info(messageSource.getMessage(Constants.NEW_REQ, new Object[] { request.getRequestURI() }, Locale.US));
		return new Reseller();
	}

	@GetMapping
	public String showreseller(Model model, HttpServletRequest request) {
		log.info(messageSource.getMessage(Constants.NEW_REQ, new Object[] { request.getRequestURI() }, Locale.US));
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			String username = ((UserDetails) principal).getUsername();
			((UserDetails) principal).getAuthorities().forEach(role -> {
				String loginUserRole = role.getAuthority();
			});
			
		} else {
			
		}
		return "administration/reseller";
	}

	@PostMapping("add")
	public String createReseller(Model model, @ModelAttribute("reseller") @Valid Reseller reseller,
			BindingResult result, HttpServletRequest request) {
		log.info(messageSource.getMessage(Constants.NEW_REQ, new Object[] { request.getRequestURI() }, Locale.US));

		
		if (resellerService.findByEmail(reseller.getEmail())) {
			result.rejectValue("email", null, "There is already an account registered with that email");
		}
		if (result.hasErrors()) {
			return showreseller(model, request);
		}
		resellerService.createReseller(reseller);
		reseller.setPassword(RandomUniquePassword.generateRandomPassword(8));
		User user = userService.saveReseller(reseller);
		EmailDto emailDto = new EmailDto();
		PasswordResetDto passwordResetDto = new PasswordResetDto();
		passwordResetDto.setUser(user);
		if (emailService.createPasswordResetToken(passwordResetDto)) {
			// Send password reset link to Reseller email id
			emailDto.setFrom(fromEmail);
			emailDto.setTo(reseller.getEmail());
			emailDto.setSubject(subject);
			Map<String, Object> emailModel = new HashMap<>();
			emailModel.put("token", passwordResetDto.getToken());
			emailModel.put("user", user);
			emailModel.put("userPassword", reseller.getPassword());
			emailModel.put("signature", "http://intcomcorp.com");
			final String URL = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/intcomcorpApplication";
			emailModel.put("resetUrl", URL + "/reset-password?token=" + passwordResetDto.getToken());
			emailDto.setModel(emailModel);
			emailService.sendMail(emailDto);
		}

		List<Reseller> resList = resellerService.getAll();
		model.addAttribute("reseller", new Reseller());
		model.addAttribute("resellerList", resList);
		model.addAttribute("successMsg", "Reseller Created Successfully");
		return "administration/reseller";
	}

	@GetMapping("/get")
	public String getReseller(Model model, HttpServletRequest request) {
		log.info(messageSource.getMessage(Constants.NEW_REQ, new Object[] { request.getRequestURI() }, Locale.US));
		if (hasRole("ROLE_RESELLER")) {
			model.addAttribute("resellerList",
					resellerService.findByEmailId(currentUserName(request.getUserPrincipal())));
			System.out.println("ROLE_RESELLER" + resellerService.findByEmail(currentUserName(request.getUserPrincipal())));
		} else {
			List<Reseller> resList = resellerService.getAll();
			model.addAttribute("resellerList", resList);
		}
		log.info("ResellerController getReseller method finished");
		return "administration/reseller-list";
	}

	@GetMapping("edit/{id}")
	public String showUpdateReseller(Model model, @PathVariable("id") Long id, HttpServletRequest request) {
		log.info(messageSource.getMessage(Constants.NEW_REQ, new Object[] { request.getRequestURI() }, Locale.US));
		Reseller reseller = resellerService.findById(id);
		model.addAttribute("reseller", reseller);
		reseller.getUserList().forEach(user -> {
			reseller.getUserIds().add(user.getId());
		});
		model.addAttribute("userList", reseller.getUserList());
		return "administration/reseller-update";
	}
    
	@PostMapping("/update/{id}")
	public String updateReseller(Model model,@ModelAttribute("reseller")  @Valid Reseller reseller, BindingResult result,@PathVariable("id") Long id,
			 HttpServletRequest request) {
		log.info(messageSource.getMessage(Constants.NEW_REQ, new Object[] { request.getRequestURI() }, Locale.US));
		if (result.hasErrors()) {
			/*
			 * Reseller reseller1 = resellerService.findById(id);
			 * model.addAttribute("reseller", reseller1);
			 * reseller.getUserList().forEach(user -> {
			 * reseller.getUserIds().add(user.getId()); }); model.addAttribute("userList",
			 * reseller.getUserList());
			 */
			return showUpdateReseller(model,id,request);
		}
		reseller.setId(id);
		if (!resellerService.updateReseller(reseller)) {
			return showUpdateReseller(model, id, request);
		}
		return "redirect:/reseller/get";
	}
 
	@GetMapping("/delete")
	public String deleteReseller(@RequestParam("id") Long id,@RequestParam("email") String email, Model model, HttpServletRequest request) {
		log.info(messageSource.getMessage(Constants.NEW_REQ, new Object[] { request.getRequestURI() }, Locale.US));
		if (userService.deleteByEmail(email))
		resellerService.deleteResById(id);
		List<Reseller> resList = resellerService.getAll();
		model.addAttribute("resellerList", resList);
		return "redirect:/reseller/get";

	}

}
