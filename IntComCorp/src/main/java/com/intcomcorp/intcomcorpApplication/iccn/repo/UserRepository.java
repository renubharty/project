package com.intcomcorp.intcomcorpApplication.iccn.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.intcomcorp.intcomcorpApplication.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmail(String email);

	@Modifying
	@Transactional
	@Query("UPDATE User u set u.password = :password WHERE u.id = :id")
	void updatePassword(@Param("password") String password, @Param("id") Long id);
	@Transactional
	void deleteByEmail(String email);
	
	
	
}