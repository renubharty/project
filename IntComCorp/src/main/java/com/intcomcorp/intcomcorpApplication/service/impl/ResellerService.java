package com.intcomcorp.intcomcorpApplication.service.impl;

import java.util.ArrayList;
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

			resellerList = resellerList.stream().filter(res -> res.isActive()).collect(Collectors.toList());

		} catch (Exception e) {
			log.error("Exception occuer while saving Reseller", e);
		}

		log.info("ResellerService getAll completed");
		return resellerList;

	}

	public Reseller findById(Long id) {
		log.info("ResellerService findById begin");
		Reseller reseller = resellerRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Reseller Id:" + id));
		log.info("ResellerService findById completed");
		return reseller;
	}

	/*
	 * public boolean updateReseller(Reseller reseller) {
	 * log.info("ResellerService updateReseller begin"); boolean isUpdated = false;
	 * // Remove Reseller's User if Any try { if (!reseller.getUserIds().isEmpty())
	 * removeUser(reseller.getUserIds()); resellerRepository.save(reseller);
	 * isUpdated = true; } catch (Exception e) { isUpdated = false; log.
	 * error("Error in ResellerService method updateReseller while Updating Reseller"
	 * , e); }
	 * log.info("ResellerService updateReseller finish, Updated successfully");
	 * return isUpdated;
	 * 
	 * }
	 */
	
	public boolean updateReseller(Reseller reseller) {
		log.info("ResellerService updateReseller begin");
		boolean isUpdated = false;
		List<Long> existingUserIds = new ArrayList<>();
		// Remove Reseller's User if Any
		try {
			Reseller res = findById(reseller.getId());
			 res.getUserList().forEach(uid ->{
				existingUserIds.add(uid.getId());
			});
			List<Long> newUserids = reseller.getUserIds();
			existingUserIds.removeAll(newUserids);
			if (!existingUserIds.isEmpty())
				removeUser(existingUserIds);
			resellerRepository.save(reseller);
			isUpdated = true;
		} catch (Exception e) {
			isUpdated = false;
			log.error("Error in ResellerService method updateReseller while Updating Reseller", e);
		}
		log.info("ResellerService updateReseller finish, Updated successfully");
		return isUpdated;

	}

	public void deleteReseller(Long resellerId) {
		// resellerRepository.deleteById(resellerId);

		resellerRepository.deleteFromResTab(resellerId);
		resellerRepository.deleteFromOrgResTab(resellerId);

	}

	public void deleteResById(Long resellerId) {
		resellerRepository.deleteById(resellerId);
		//resellerRepository.softDelete(resellerId);

	}

	private boolean removeUser(List<Long> usersId) {
		log.info("ResellerService removeUser begin");
		boolean isRemoved = false;
		try {
			resellerRepository.removeUser(usersId);
			isRemoved = true;
		} catch (Exception e) {
			log.error("Error while removing Reseller users," + e);
			isRemoved = false;
		}
		log.info("ResellerService removeUser finish");
		return isRemoved;
	}
	
	public boolean findByEmail(String email) {
	log.info("ResellerService findByEmail begin");
	boolean isAvaiable = false;
		try {
			Reseller exist = resellerRepository.findByEmail(email);
			if (exist !=null)
			isAvaiable = true;
		} catch (Exception e) {
			log.error("Error occured while finding Reseller email", e);
			isAvaiable = false;
		}
		
		log.info("ResellerService findByEmail finish");
		return isAvaiable;
	}
	
	public Reseller findByEmailId(String email) {
		log.info("ResellerService findByEmail begin");
		
		Reseller exist = null;
			try {
				 exist = resellerRepository.findByEmail(email);
			} catch (Exception e) {
				log.error("Error occured while finding Reseller email", e);
				
			}
			
			log.info("ResellerService findByEmail finish");
			return exist;
		}

}
