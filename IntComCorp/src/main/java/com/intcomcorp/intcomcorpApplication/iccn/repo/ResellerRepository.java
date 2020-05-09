package com.intcomcorp.intcomcorpApplication.iccn.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.intcomcorp.intcomcorpApplication.model.Reseller;

public interface ResellerRepository extends JpaRepository<Reseller, Long> {
	
	@Modifying
	@Query(value = "INSERT INTO org_reseller (reseller_id,org_id) VALUES (:resId,:orgId)"
			,nativeQuery = true)
	public void inserResOrgTab(@Param("resId") Long resId,@Param("orgId") Long orgId);

}
