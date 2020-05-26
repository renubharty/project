package com.intcomcorp.intcomcorpApplication.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.intcomcorp.intcomcorpApplication.iccn.repo.ResellerRepository;
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

	@Autowired
	private ResellerRepository resellerRepository;

	@ModelAttribute("reseller")
	public Reseller reseller(HttpServletRequest request) {
		log.info(messageSource.getMessage(Constants.NEW_REQ, new Object[] { request.getRequestURI() }, Locale.US));
		return new Reseller();
	}

	@GetMapping
	public String showreseller(Model model, HttpServletRequest request) {
		log.info(messageSource.getMessage(Constants.NEW_REQ, new Object[] { request.getRequestURI() }, Locale.US));

		model.addAttribute("orgList", organizationServiceimpl.getAllOrg());
		return "administration/reseller";
	}

	@PostMapping("add")
	public String saveReseller(Model model, @ModelAttribute("reseller") @Valid Reseller reseller, BindingResult result,
			HttpServletRequest request) {
		log.info("Reseller controller method saveReseller begin");
		log.info(messageSource.getMessage(Constants.NEW_REQ, new Object[] { request.getRequestURI() }, Locale.US));

		if (result.hasErrors()) {
			return showreseller(new BindingAwareConcurrentModel(), request);
		}
		Reseller res = resellerService.createReseller(reseller);
		res.getOrgIds().forEach(orgId -> {
			resellerService.insertResOrgTab(res.getId(), Long.parseLong(orgId));
		});
		// resellerService.insertResOrgTab(res.getId(),
		// Long.parseLong(reseller.getOrganization()));
		model.addAttribute("reseller", new Reseller());
		model.addAttribute("successMsg", "Reseller Created Successfully");
		model.addAttribute("orgList", organizationServiceimpl.getAllOrg());
		return "administration/reseller";
	}

	@GetMapping("/get")
	public String getReseller(Model model) {
		log.info("fesellerController getReseller method begin");
		List<Reseller> resList = resellerService.getAll();
		model.addAttribute("resellerList", resList);
		log.info("esellerController getReseller method finished");
		return "administration/reseller-list";
	}

	@GetMapping("edit/{id}")
	public String showUpdateReseller(Model model, @PathVariable("id") Long id) {
		Reseller reseller = resellerService.findById(id);
		List<String> orgIds = new ArrayList<String>();
		reseller.getOrgSet().forEach(org -> {
			orgIds.add(Long.toString(org.getId()));

			reseller.setOrgIds(orgIds);

		});
		model.addAttribute("reseller", reseller);
		model.addAttribute("orgList", organizationServiceimpl.getAllOrg());
		return "administration/reseller-update";
	}

	@PostMapping("/update/{id}")
	public String updateReseller(@Valid @ModelAttribute("reseller") Reseller reseller, @PathVariable("id") Long id,
			Model model, BindingResult result) {
		if (result.hasErrors()) {

			return "redirect:/reseller/edit/{id}";
		}
		reseller.setId(id);
		reseller.setOrgSet(organizationServiceimpl.getOrgListByIds(reseller.getOrgIds()));
		resellerService.updateReseller(reseller);
		model.addAttribute("reseller", resellerRepository.findAll());
//		return getReseller(new BindingAwareConcurrentModel());
		return "redirect:/reseller/get";
	}

	@GetMapping("/delete/{id}")
	public String deleteReseller(@PathVariable("id") Long id, Model model) {
		
		//resellerService.deleteReseller(id);
		
		resellerService.deleteResById(id);
		
		List<Reseller> resList = resellerService.getAll();
		model.addAttribute("resellerList", resList);
		return "redirect:/reseller/get";

	}

}
