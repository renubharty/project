package com.intcomcorp.intcomcorpApplication.zabbix.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intcomcorp.intcomcorpApplication.zabbix.model.Templates;
import com.intcomcorp.intcomcorpApplication.zabbix.repo.TemplatesRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TemplateService {
	
	@Autowired
	TemplatesRepository templatesRepository;
	
	public List<Templates> findAllTemplates(){
		log.info(" findAllTemplates method started");
		List<Templates> templatesList = null;
		try {
			 templatesList = templatesRepository.getAllTemplates();
		} catch (Exception e) {
			log.error("Error while loading templates" ,e);
		}
		log.info(" findAllTemplates method finished");
		return templatesList;
	}
	

}
