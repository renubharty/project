package com.intcomcorp.intcomcorpApplication.iccn.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.intcomcorp.intcomcorpApplication.model.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long>{

	
	@Modifying
	@Query(value = "INSERT INTO  reseller_inventory (reseller_id,inventory_id) VALUES (:resId,:invId)"
			,nativeQuery = true)
	public void insertInInvResTab(@Param("resId") Long resId,@Param("invId") Long invId);
	
	@Modifying
	@Query(value = "INSERT INTO distributor_inventory (distributor_id,inventory_id ) VALUES (:distId,:invId)"
			,nativeQuery = true)
	public void inserInvDistTab(@Param("distId") Long distId,@Param("invId") Long invId);
    
	public Inventory getInventoryByid(Long id);
	
	@Modifying
	@Query(value = "UPDATE  inventory SET isDeleted  = true where id = :invId"
			,nativeQuery = true)
	public void softDelete(@Param("invId") Long invId);
	
	@Modifying
	@Query(value = "delete from  reseller_inventory where inventory_id = :invId "
			,nativeQuery = true)
	public void deleteInvRes(@Param("invId") Long invId);
	
	
}
