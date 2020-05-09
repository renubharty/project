package com.intcomcorp.intcomcorpApplication.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class AppConfig {

	@Value("classpath:application-${spring.profiles.active}")
	private String activeProfile;

	@Bean(name = "messageSource")
	public MessageSource devmessageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasenames(activeProfile, "classpath:messages","classpath:validation", "classpath:mail");
		messageSource.setCacheSeconds(10);
		return messageSource;
	}
	
	 

}
