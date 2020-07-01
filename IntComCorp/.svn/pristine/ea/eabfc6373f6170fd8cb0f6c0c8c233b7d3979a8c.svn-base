package com.intcomcorp.intcomcorpApplication.iccn.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.intcomcorp.intcomcorpApplication.model.Host;

public interface HostRepository extends JpaRepository<Host, Long>{
	
	@Modifying
	@Query(value = "INSERT INTO users_hosts (user_id,host_id) VALUES (:userId,:hostId)"
			,nativeQuery = true)
	public void inserUsersHost(@Param("userId") Long userId,@Param("hostId") Long hostId);
     
	public Host findByHostId(int hostId) ;
	@Modifying
	@Query(value = "DELETE FROM users_hosts where user_id = :userId"
			,nativeQuery = true)
	public int deleteAllUserHosts(@Param("userId") Long userId );
	
	
}
