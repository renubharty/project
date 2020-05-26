package com.intcomcorp.intcomcorpApplication.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intcomcorp.intcomcorpApplication.iccn.repo.InventoryRepository;
import com.intcomcorp.intcomcorpApplication.model.Inventory;
import com.intcomcorp.intcomcorpApplication.model.Reseller;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class InventoryServicce {
	
	@Autowired
	private  InventoryRepository   inventoryRepository;
	
	
	

	public Inventory createInventory(Inventory inventory) {
		log.info("InventoryServicce createInventory begin");
		Inventory inv = null;
		try {
			inv = inventoryRepository.save(inventory);
		} catch (Exception e) {
			log.error("Exception occuer while saving inventory", e);

		}
		log.info("InventoryServicce inventory finished");
		return inv;
	}
	
	
	public List<Inventory> getAll() {
		log.info("InventoryServicce getAll begin");
		List<Inventory> inventoryList = null;
		
		try {
			inventoryList = inventoryRepository.findAll();
			
		//	inventoryList  =  inventoryList.stream().filter(inv -> !inv.isDeleted()).collect(Collectors.toList()); 
			
		} catch (Exception e) {
			log.error("Exception occuer while saving Reseller", e);
		}

		log.info("InventoryServicce getAll completed");
	
		return inventoryList;

	}
	
	
	public void assignHost(Long resId,List<Long> invIds)
	{   
		try {
		invIds.forEach(invId ->{
			inventoryRepository.inserInvResTab(resId, invId);
		});
		} catch(Exception e) {
			log.error("Exception occuer while assigning hosts", e);
		}
		log.info("InventoryServicce assignHost completed");
	}
	
	
	
	public void saveInvWithDistributor(Long distId,Long invId)
	{   
		try {
		
			inventoryRepository.inserInvDistTab(distId, invId);
	
		} catch(Exception e) {
			log.error("Exception occuer while assigning hosts", e);
		}
		log.info("InventoryServicce assignHost completed");
	}
	
 

}
