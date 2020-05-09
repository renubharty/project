package com.intcomcorp.intcomcorpApplication.iccn.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.intcomcorp.intcomcorpApplication.model.Organization;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long>{

}
