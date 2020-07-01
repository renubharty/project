package com.intcomcorp.intcomcorpApplication.zabbix.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.intcomcorp.intcomcorpApplication.zabbix.model.Templates;

public interface TemplatesRepository extends JpaRepository<Templates, Long>{
     
	@Query(value = "SELECT  new com.intcomcorp.intcomcorpApplication.zabbix.model.Templates(t.name,t.hostid) FROM Templates t where  t.status = '3'")
	   public List<Templates>  getAllTemplates();
	
}
