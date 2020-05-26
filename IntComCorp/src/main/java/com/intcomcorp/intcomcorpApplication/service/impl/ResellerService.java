package com.intcomcorp.intcomcorpApplication.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.intcomcorp.intcomcorpApplication.iccn.repo.ResellerRepository;
import com.intcomcorp.intcomcorpApplication.model.Reseller;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class ResellerService {

	@Autowired
	private ResellerRepository resellerRepository;

	public Reseller createReseller(Reseller reseller) {
		log.info("ResellerService createReseller begin");
		Reseller res = null;
		try {
			res = resellerRepository.save(reseller);
		} catch (Exception e) {
			log.error("Exception occuer while saving Reseller", e);

		}
		log.info("ResellerService createReseller finished");
		return res;
	}

	public void insertResOrgTab(Long resId, Long orgId) {
		resellerRepository.inserResOrgTab(resId, orgId);

	}

	public List<Reseller> getAll() {
		log.info("ResellerService getAll begin");
		List<Reseller> resellerList = null;
		
		try {
			resellerList = resellerRepository.findAll();
			
			resellerList  =  resellerList.stream().filter(res -> res.isActive()).collect(Collectors.toList()); 
			
		} catch (Exception e) {
			log.error("Exception occuer while saving Reseller", e);
		}

		log.info("ResellerService getAll completed");
		return resellerList;

	}

	public Reseller findById(Long id) {
		log.info("ResellerService findById begin");
		Reseller reseller = resellerRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + id));
		log.info("ResellerService findById completed");
		return reseller;
	}

	public void updateReseller(Reseller reseller) {
		resellerRepository.save(reseller);

	}

	
	public void deleteReseller(Long resellerId) {
		// resellerRepository.deleteById(resellerId);
		
		resellerRepository.deleteFromResTab(resellerId);
		resellerRepository.deleteFromOrgResTab(resellerId);
		 
	}
	
	public void deleteResById(Long resellerId) {
		resellerRepository.softDelete(resellerId);
		
	}
	
	
	
}
