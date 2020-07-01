package com.intcomcorp.intcomcorpApplication.service.impl;

import static com.intcomcorp.intcomcorpApplication.utils.Util.parseDate;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.intcomcorp.intcomcorpApplication.dto.ProblemDto;
import com.intcomcorp.intcomcorpApplication.dto.response.ZabbixApiResponse;
import com.intcomcorp.intcomcorpApplication.model.Host;
import com.intcomcorp.intcomcorpApplication.model.User;
import com.intcomcorp.intcomcorpApplication.service.UserService;
import com.intcomcorp.intcomcorpApplication.utils.Util;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProblemService {

	@Autowired
	private ZabbixProblemService zabbixProblemService;

	@Autowired
	private HostService hostService;

	@Autowired
	private UserService userService;

	public List<ProblemDto> getAllHostProblem(Principal principal) {
		log.info("getAllHostProblem begin");
		List<ProblemDto> problemList = new ArrayList<>();
	//	JsonParser parser;
	//	JsonElement element;
	//	JsonObject json;
		List<String> hostIdList = new ArrayList<String>();
		try {
			Map<Integer, Host> hostMap = hostService.getAllZabbixHost(false);
			
			
			if (Util.hasRole("ROLE_USER")) {
			//	Principal principal = (Principal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				Set<User> usersHost = userService.getLoggedInUserHost(principal);
				usersHost.forEach(user -> {
					user.getUsersHostList().forEach(host -> {
						hostIdList.add(String.valueOf(host.getHostId()));
					});

				});

			}else {
				hostMap.forEach((k, v) -> {
					hostIdList.add(String.valueOf(k));
				});
			}
			hostIdList.forEach(hostId ->{
				ZabbixApiResponse zr = (ZabbixApiResponse) zabbixProblemService.problemByHost(hostId).getBody();	
			
		//	ZabbixApiResponse zr = (ZabbixApiResponse) zabbixProblemService.problemByHost(hostIdList).getBody();
			JsonParser parser = new JsonParser();
			JsonElement element = parser.parse(zr.getJsonObject().toString());
			JsonObject json = element.getAsJsonObject();
			JsonObject problemJson = json.getAsJsonObject();
			JsonArray collections = problemJson.getAsJsonArray("result");
			for (int i = 0, size = collections.size(); i < size; i++) {
				ProblemDto problem = new ProblemDto();
				problem.setDescription(collections.get(i).getAsJsonObject().get("name").getAsString());
				problem.setSeverity(collections.get(i).getAsJsonObject().get("severity").getAsString());
				problem.setEventId(collections.get(i).getAsJsonObject().get("eventid").getAsString());
				problem.setObjectId(collections.get(i).getAsJsonObject().get("objectid").getAsString());
				problem.setAcknowledge(collections.get(i).getAsJsonObject().get("acknowledged").getAsString());
				problem.setClock(parseDate(collections.get(i).getAsJsonObject().get("clock").getAsString()));
				problem.setPeriod(Util.calculatePeriod(collections.get(i).getAsJsonObject().get("clock").getAsString()));
			    problem.setHostName(hostMap.get(Integer.parseInt(hostId)).getName());
				problemList.add(problem);
			}
			});
		} catch (Exception e) {
			log.error("Exception while fetching Host problem data ", e);
		}
		    log.info("getAllHostProblem finish");

		          return problemList;
	}

}