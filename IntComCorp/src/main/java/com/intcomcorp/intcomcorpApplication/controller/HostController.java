package com.intcomcorp.intcomcorpApplication.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.intcomcorp.intcomcorpApplication.dto.UserHostsDto;
import com.intcomcorp.intcomcorpApplication.iccn.repo.HostRepository;
import com.intcomcorp.intcomcorpApplication.model.Host;
import com.intcomcorp.intcomcorpApplication.model.User;
import com.intcomcorp.intcomcorpApplication.service.UserService;
import com.intcomcorp.intcomcorpApplication.service.impl.HostService;

@RequestMapping("/host")
@Controller
public class HostController {

	@Autowired
	UserService userService;

	@Autowired
	HostService hostService;

	Map<Integer, Host> zabbixHostMap;

	@PostConstruct
	public void init() {

	}

	@ModelAttribute("userHostDto")
	public UserHostsDto getUserHostsDto() {
		return new UserHostsDto();
	}

	@GetMapping("show")
	public String showHostAssign(Model model, Long id) {
		/**
		 * 1. Get All Customer 2. Get All hosts
		 */
		model.addAttribute("customerList", userService.getAllCustomer());
		/**
		 * Before add to dropdown removed assigned hosts from list Load Host from Host
		 * table (Local DB)
		 */
		zabbixHostMap = hostService.getAllZabbixHost(true);
		List<Host> filteredHosts = new ArrayList<>(zabbixHostMap.values());
		model.addAttribute("hostList", filteredHosts);

		if (id != null) {

			UserHostsDto dto = new UserHostsDto();
			User user = userService.getUserById(id);
			user.getUsersHostList().forEach(host -> {

				dto.getHostIds().add(String.valueOf(host.getId()));
			});

			dto.setCustomerId(id);
			model.addAttribute("customerList", user);
		}

		return "inventory/assign-hosts";
	}

	@PostMapping("/assign")
	public String hostAssign(@ModelAttribute("userHostDto") UserHostsDto userHostDto, Model m) {

		/**
		 * Get Customer Id and Host Id Get host from zabbixMap and save it to Host table
		 * Insert Host id and Customer Id in users_host table
		 */
		hostService.assignHost(zabbixHostMap, userHostDto);
		userHostDto.getHostIds().forEach(hid -> {
			zabbixHostMap.remove(hid);
		});
		m.addAttribute("userHostDto", new UserHostsDto());
		m.addAttribute("successMsg", "Hosts assigned successfully");
		return showHostAssign(m, null);

	}

	@GetMapping("/get")
	public String getHostByCustomer(Model model) {

		model.addAttribute("hostListByCustomer", userService.getAllCustomerByHost());
		return "hosts/host-list";
	}

	@GetMapping("/edit/{id}")
	public String showUpdateHost(@PathVariable("id") Long id, @ModelAttribute("userHostDto") UserHostsDto userHostDto,
			Model model) {
        
		zabbixHostMap = hostService.getAllZabbixHost(true);
		List<Host> filteredHosts = new ArrayList<>(zabbixHostMap.values());
		User user = userService.getUserById(id);
		List<String> hostIds = new ArrayList<String>();
               
		user.getUsersHostList().forEach(host -> {
			filteredHosts.add(host);
			hostIds.add(String.valueOf(host.getHostId()));
		});

		userHostDto.setCustomerId(id);
		userHostDto.setHostIds(hostIds);

		model.addAttribute("customerList", user);

		
		model.addAttribute("hostList", filteredHosts);
		return "hosts/update-hosts";
	}

	@PostMapping("/update/{customerId}")
	public String updateHost(@ModelAttribute("userHostDto") UserHostsDto userHostDto,
			@PathVariable("customerId") Long customerId, Model model) {
		User user = userService.getUserById(customerId);
		
		List<Host> hostList = new ArrayList<>();
		hostService.deleteAllUserHosts(customerId);
		userHostDto.getHostIds().forEach(hostId -> {
			// Host host = new Host();
			Host alreadyAssignedhost = hostService.findByHostId(Integer.parseInt(hostId));
			if (alreadyAssignedhost != null) {
				alreadyAssignedhost.setUser(user);
				hostList.add(alreadyAssignedhost);
			} else {
				zabbixHostMap = hostService.getAllZabbixHost(false);
				Host newZabbixHost = zabbixHostMap.get(Integer.parseInt(hostId));
				newZabbixHost.setUser(user);
				hostList.add(newZabbixHost);
			}

			// host.setHostId(Integer.parseInt(hostId));

		});

		if (!hostService.updateHost(hostList)) {
			model.addAttribute("successMsg", "Error While updating User Host");
			return "hosts/update-hosts";
		}

		return getHostByCustomer(model);

	}

}
