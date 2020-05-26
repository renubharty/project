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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.intcomcorp.intcomcorpApplication.dto.request.ResellerInventoryRequest;
import com.intcomcorp.intcomcorpApplication.model.Inventory;
import com.intcomcorp.intcomcorpApplication.model.Organization;
import com.intcomcorp.intcomcorpApplication.model.Reseller;
import com.intcomcorp.intcomcorpApplication.service.impl.InventoryServicce;
import com.intcomcorp.intcomcorpApplication.service.impl.OrganizationServiceimpl;
import com.intcomcorp.intcomcorpApplication.service.impl.ResellerService;
import com.intcomcorp.intcomcorpApplication.utils.Constants;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/inventory")
@Controller
public class InventoryController {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private InventoryServicce inventoryServicce;

	@Autowired
	private ResellerService resellerService;
	
	@Autowired
	private OrganizationServiceimpl  orgService;

	@ModelAttribute("inventory")
	public Inventory inventory(HttpServletRequest request) {
		log.info(messageSource.getMessage(Constants.NEW_REQ, new Object[] { request.getRequestURI() }, Locale.US));
		return new Inventory();
	}

	@ModelAttribute("resellerInventoryRequest")
	public ResellerInventoryRequest resellerInventoryRequest(HttpServletRequest request) {
		log.info(messageSource.getMessage(Constants.NEW_REQ, new Object[] { request.getRequestURI() }, Locale.US));
		return new ResellerInventoryRequest();
	}

	@GetMapping
	public String showinventory(Model model, HttpServletRequest request) {
		log.info(messageSource.getMessage(Constants.NEW_REQ, new Object[] { request.getRequestURI() }, Locale.US));
		List<Organization> orgList = orgService.getAllOrg();
		model.addAttribute("orgList", orgList);
		return "inventory/inventory";
	}

	@PostMapping("add")
	public String saveInventory(Model model, @ModelAttribute("inventory") @Valid Inventory inventory,
			BindingResult result, HttpServletRequest request) {
		log.info("InventoryController  method saveInventory begin");
		log.info(messageSource.getMessage(Constants.NEW_REQ, new Object[] { request.getRequestURI() }, Locale.US));

		if (result.hasErrors()) {
			return showinventory(new BindingAwareConcurrentModel(), request);
		}
		Organization org = orgService.findById(inventory.getDistId());
	    Inventory inv =	inventoryServicce.createInventory(inventory);
		inventoryServicce.saveInvWithDistributor(org.getId(),inv.getId());
		model.addAttribute("inventory", new Inventory());
		model.addAttribute("successMsg", "Inventory Created Successfully");
		return "inventory/inventory";
	}

	@GetMapping("assign")
	public String showInventoryAssign(Model model) {
		List<Reseller> resList = resellerService.getAll();
		List<Inventory> invList = inventoryServicce.getAll();

		model.addAttribute("resList", resList);
		model.addAttribute("invList", invList);
		return "inventory/assign-inventory";
	}
	
	
	
	@PostMapping("assignTo/reseller")
	public String assignToReseller(Model model, @ModelAttribute("resellerInventoryRequest") @Valid ResellerInventoryRequest resInvReq,
			BindingResult result, HttpServletRequest request) {
		log.info("InventoryController  method assignToReseller begin");
		log.info(messageSource.getMessage(Constants.NEW_REQ, new Object[] { request.getRequestURI() }, Locale.US));

		if (result.hasErrors()) {
			return showinventory(new BindingAwareConcurrentModel(), request);
		}
		inventoryServicce.assignHost(resInvReq.getResId(), resInvReq.getInventoryIds());
		model.addAttribute("resellerInventoryRequest", new ResellerInventoryRequest());
		model.addAttribute("successMsg", "Hosts assigned Successfully");
		return "inventory/assign-inventory";
	}
	
	@GetMapping("/get")
	public String getAllCustomer(Model model) {
		
		model.addAttribute("invList", inventoryServicce.getAll());
		
		return "inventory/inventory-list";
	}
}
