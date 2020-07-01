package com.intcomcorp.intcomcorpApplication.zabbix.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intcomcorp.intcomcorpApplication.zabbix.model.Groups;
import com.intcomcorp.intcomcorpApplication.zabbix.repo.GroupsRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class GroupsService {
	
	@Autowired
	private GroupsRepository groupRepository;
	
	public List<Groups> findAllGroups(){
		log.info(" findAllGroups method started");
		List<Groups> groupsList = null;
		try {
			groupsList = groupRepository.getAllGroups();
		} catch (Exception e) {
			log.error("Error while loading Groups" ,e);
		}
		log.info(" findAllGroups method finished");
		return groupsList;
	}

}
