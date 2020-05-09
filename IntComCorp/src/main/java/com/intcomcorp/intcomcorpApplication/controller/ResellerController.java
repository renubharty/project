package com.intcomcorp.intcomcorpApplication.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.support.BindingAwareConcurrentModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.intcomcorp.intcomcorpApplication.model.Reseller;
import com.intcomcorp.intcomcorpApplication.service.impl.OrganizationServiceimpl;
import com.intcomcorp.intcomcorpApplication.service.impl.ResellerService;
import com.intcomcorp.intcomcorpApplication.utils.Constants;

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
	private OrganizationServiceimpl organizationServiceimpl;

	@ModelAttribute("reseller")
	public Reseller reseller(HttpServletRequest request) {
		log.info(messageSource.getMessage(Constants.NEW_REQ, new Object[] { request.getRequestURI() }, Locale.US));
		return new Reseller();
	}

	@GetMapping
	public String showreseller(Model model, HttpServletRequest request) {
		log.info(messageSource.getMessage(Constants.NEW_REQ, new Object[] { request.getRequestURI() }, Locale.US));
		//teest code
		List<Reseller> resList = resellerService.getAll();
		
		resList.forEach(res -> {
			
			res.getOrgSet().forEach(org ->{
				if (org!=null) {
					System.out.println(">>>>>>>>>>> ORG ID " +org.getId() + "NAME "  +org.getName() +
							"RES ID "+ res.getId()
							
							);
				}
			});
		});
		model.addAttribute("orgList", organizationServiceimpl.getAllOrg());
		return "administration/reseller";
	} 

	@PostMapping
	public String saveReseller(Model model,@ModelAttribute("reseller") @Valid Reseller reseller, BindingResult result,
			HttpServletRequest request) {
		log.info("Reseller controller method saveReseller begin");
		log.info(messageSource.getMessage(Constants.NEW_REQ, new Object[] { request.getRequestURI() }, Locale.US));

		if (result.hasErrors()) {
			return showreseller(new BindingAwareConcurrentModel(), request);
		}
		Reseller res = resellerService.createReseller(reseller);
		resellerService.insertResOrgTab(res.getId(), Long.parseLong(reseller.getOrganization()));
		List<Reseller> resList = resellerService.getAll();
		model.addAttribute("reseller", new Reseller());
		model.addAttribute("successMsg", "Rseller Created Successfully");
		model.addAttribute("orgList", organizationServiceimpl.getAllOrg());
		return "/administration/reseller";
	}

}
