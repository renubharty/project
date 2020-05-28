package com.intcomcorp.intcomcorpApplication.iccn.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.intcomcorp.intcomcorpApplication.model.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long>{

	
	
	@Modifying
	@Query(value = "INSERT INTO inventory_reseller (reseller_id,inventory_id) VALUES (:resId,:invId)"
			,nativeQuery = true)
	public void inserInvResTab(@Param("resId") Long resId,@Param("invId") Long invId);
	
	@Modifying
	@Query(value = "INSERT INTO distributor_inventory (distributor_id,inventory_id ) VALUES (:distId,:invId)"
			,nativeQuery = true)
	public void inserInvDistTab(@Param("distId") Long distId,@Param("invId") Long invId);
    public Inventory getInventoryByid(Long id);
	
}
