package com.intcomcorp.intcomcorpApplication.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
	
	public Set<Organization> getOrgListByIds(List<String> orgIds) {
		Set<Organization> orgList = new HashSet<Organization>();
		orgIds.forEach(id -> {
			orgList.add(findById(Long.parseLong(id)));
		});
		return orgList;
	}



}
