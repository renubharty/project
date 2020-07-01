package com.intcomcorp.intcomcorpApplication.iccn.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.intcomcorp.intcomcorpApplication.dto.TemplatesDto;
import com.intcomcorp.intcomcorpApplication.model.Reseller;
@Repository
public interface ResellerRepository extends JpaRepository<Reseller, Long> {
	
	@Modifying
	@Query(value = "INSERT INTO org_reseller (reseller_id,org_id) VALUES (:resId,:orgId)"
			,nativeQuery = true)
	public void inserResOrgTab(@Param("resId") Long resId,@Param("orgId") Long orgId);
	
	
	@Modifying
	@Query(value = "DELETE FROM org_reseller where reseller_id = :resId"
			,nativeQuery = true)
	public void deleteFromOrgResTab(@Param("resId") Long resId );
	
	
	@Modifying
	@Query(value = "DELETE FROM reseller where id = :resId"
			,nativeQuery = true)
	public void deleteFromResTab(@Param("resId") Long resId);
	
	
	
	
	
	@Modifying
	@Query(value = "UPDATE  reseller SET active = false where id = :resId"
			,nativeQuery = true)
	public void softDelete(@Param("resId") Long resId);
	
	
	@Modifying
	@Query(value = "delete from  users_reseller where  user_id not in (:userIds);"
			,nativeQuery = true)
	public void removeUser(@Param("userIds") List<Long> resId);
	
	
	public Reseller findByEmail(String email);
	
	
	
	
	
	

}
