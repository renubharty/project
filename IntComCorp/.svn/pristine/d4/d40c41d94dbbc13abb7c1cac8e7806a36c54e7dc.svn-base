package com.intcomcorp.intcomcorpApplication.config;
import java.util.HashMap;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactory", basePackages = {
		"com.intcomcorp.intcomcorpApplication.iccn.repo" })
public class IccnDBConfig {
	
	@Primary
	@Bean(name = "dataSource")
	@ConfigurationProperties(prefix = "spring.iccn.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

	@Primary
	@Bean(name = "entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("dataSource") DataSource dataSource) {
		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", "update");
		//properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL57Dialect");
		return builder.dataSource(dataSource).properties(properties)
				.packages("com.intcomcorp.intcomcorpApplication.model").build();
	}

	@Primary
	@Bean(name = "transactionManager")
	public PlatformTransactionManager transactionManager(
			@Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}

	
	/**
	 * 
	 * @param dataSource
	 * @return
	 */
	

	/*
	 * @Bean(name = "zabbixdataSource")
	 * 
	 * @ConfigurationProperties(prefix = "spring.zabbix.datasource") public
	 * DataSource getZabbixDataSource() { return DataSourceBuilder.create().build();
	 * }
	 * 
	 * 
	 * @Bean(name = "zabbixentityManagerFactory") public
	 * LocalContainerEntityManagerFactoryBean
	 * getZabbixEntityManagerFactory(EntityManagerFactoryBuilder builder,
	 * 
	 * @Qualifier("zabbixdataSource") DataSource dataSource) { HashMap<String,
	 * Object> properties = new HashMap<>();
	 * properties.put("hibernate.hbm2ddl.auto", "update");
	 * properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
	 * return builder.dataSource(dataSource).properties(properties)
	 * .packages("com.intcomcorp.intcomcorpApplication.zabbix.model").build(); }
	 * 
	 * 
	 * @Bean(name = "zabbixtransactionManager") public PlatformTransactionManager
	 * getTransactionManager(@Qualifier("zabbixentityManagerFactory")
	 * EntityManagerFactory entityManagerFactory) { return new
	 * JpaTransactionManager(entityManagerFactory); }
	 */
	
	@Bean
    public DataSourceInitializer dataSourceInitializer(@Qualifier("dataSource") final DataSource dataSource) {
        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
        resourceDatabasePopulator.addScript(new ClassPathResource("/db/data.sql"));
        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
        dataSourceInitializer.setDataSource(dataSource);
        dataSourceInitializer.setDatabasePopulator(resourceDatabasePopulator);
        return dataSourceInitializer;
    }
	
	
}