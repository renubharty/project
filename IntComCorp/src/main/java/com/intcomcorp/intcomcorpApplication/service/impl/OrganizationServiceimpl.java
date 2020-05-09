package com.intcomcorp.intcomcorpApplication.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intcomcorp.intcomcorpApplication.iccn.repo.OrganizationRepository;
import com.intcomcorp.intcomcorpApplication.model.Organization;

@Service
public class OrganizationServiceimpl  {
	
	@Autowired
	OrganizationRepository organizationRepository;
	
	
	public Organization save(Organization entity) {
		return organizationRepository.save(entity);
	}
	
	public List<Organization> getAllOrg() {
		
		return  organizationRepository.findAll();
		
	}
	
	public Organization  findById(Long orgId) {
		
	Optional<Organization> org = 	organizationRepository.findById(orgId);
		
		return org.map(Organization :: new).get();
	}



}
