package com.intcomcorp.intcomcorpApplication.zabbix.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.intcomcorp.intcomcorpApplication.zabbix.model.Groups;
import com.intcomcorp.intcomcorpApplication.zabbix.model.Templates;

public interface GroupsRepository extends JpaRepository<Groups, Long> {

	@Query(value = "SELECT  new com.intcomcorp.intcomcorpApplication.zabbix.model.Groups(g.groupid,g.name) FROM Groups g")
	public List<Groups> getAllGroups();
}
