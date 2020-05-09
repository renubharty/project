package com.intcomcorp.intcomcorpApplication.service.impl;

import java.util.List;

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
			log.error("Exception occuer while saving Reseller",e );
			
		}
		log.info("ResellerService createReseller finished");
		return res;
	}
	
	public void insertResOrgTab(Long resId,Long orgId) {
		resellerRepository.inserResOrgTab(resId,orgId);
		
	}
	
	public List<Reseller> getAll(){
		log.info("ResellerService getAll begin");
		List<Reseller> resellerList = null;
		try {
			resellerList  =	 resellerRepository.findAll();
		} catch (Exception e) {
			log.error("Exception occuer while saving Reseller",e );
		}
		
		
		return resellerList;
		
	}
	

  

}
