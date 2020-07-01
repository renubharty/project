package com.intcomcorp.intcomcorpApplication.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.intcomcorp.intcomcorpApplication.dto.UserHostsDto;
import com.intcomcorp.intcomcorpApplication.dto.response.ZabbixApiResponse;
import com.intcomcorp.intcomcorpApplication.iccn.repo.HostRepository;
import com.intcomcorp.intcomcorpApplication.model.Host;
import com.intcomcorp.intcomcorpApplication.model.User;

@Service
@Transactional
public class HostService {

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	private HostRepository hostRepository;
	
	@Autowired
	ZabbixApiHostService  zabbixApiHostService;
	
	   
	   
	public Map<Integer, Host> getAllZabbixHost(boolean isRemove) {
		
		JsonParser parser;
        JsonElement element;
        JsonObject json;
		
		ZabbixApiResponse zr =	(ZabbixApiResponse) zabbixApiHostService.hostsDetails(new String[] {"test"}).getBody();
	//	String res = zr.getJsonObject().getJSONArray("jsonObject").toJSONString();
		//gson.fromJson(zr.getJsonObject().toString(),HostDto.class);
		
		List<Host> hostList = new ArrayList<Host>();
		parser = new JsonParser();
		element= 	parser.parse(zr.getJsonObject().toString());
		json =  element.getAsJsonObject();
		JsonObject hostJSON = json.getAsJsonObject();
		 JsonArray collections = hostJSON.getAsJsonArray("result");
		  for (int i = 0, size = collections.size(); i < size; i++) {
			  Host host = new Host();
			  host.setName(collections.get(i).getAsJsonObject().get("host").getAsString());
			  host.setHostId(collections.get(i).getAsJsonObject().get("hostid").getAsInt());
			  hostList.add(host);
		  }
		
		List<Host> assignHost = hostRepository.findAll();// hostRepository.findAll();
		Map<Integer, Host> zabbixHostMap = hostList.stream()
				.collect(Collectors.toMap(Host::getHostId, Function.identity()));

	if (isRemove)	{
	assignHost.forEach(host -> {
			zabbixHostMap.remove(host.getHostId());
		});
		
	}	
		return zabbixHostMap;
	}

	public void saveHost(UserHostsDto dto) {

	}

	public void inserUsersHost(String customerId, List<String> hostIds) {

		hostIds.forEach(hid -> {
			hostRepository.inserUsersHost(Long.parseLong(customerId), Long.parseLong(hid));
		});

	}

	public void assignHost(Map<Integer, Host> zabbixHostMap, UserHostsDto userHostDto) {

		userHostDto.getHostIds().forEach(hid -> {
			Host host = hostRepository.save(zabbixHostMap.get(new Integer(hid)));
			hostRepository.inserUsersHost(userHostDto.getCustomerId(), host.getId());
		});

	}
	
	
	public Host hostById(Long id) {
		Host host = hostRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Host Id:" + id));;
	return	host;
		
	}
	
    public Host	findByHostId(int hostId) {
    	Host host = hostRepository.findByHostId(hostId);
    	return	host;
    }
	
	
	public boolean updateHost(List<Host> hosts) {

		try {
			hosts.forEach(host -> {
				 hostRepository.save(host);
			});

		} catch (Exception e) {
			return false;
		}

		return true;

	}
	 
   public int deleteAllUserHosts(Long userId) {
	   try {
		   hostRepository.deleteAllUserHosts(userId);
		   return 1;
	} catch (Exception e) {
		System.out.println(e.toString());
		return 0;
	}
	  
   }

}
