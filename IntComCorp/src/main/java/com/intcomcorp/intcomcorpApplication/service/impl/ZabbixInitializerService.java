package com.intcomcorp.intcomcorpApplication.service.impl;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.intcomcorp.intcomcorpApplication.service.ZabbixApi;
import com.intcomcorp.intcomcorpApplication.utils.Constants;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ZabbixInitializerService {

	public ZabbixApi zabbixApi;

	@Autowired
	private MessageSource messageSource;

	@PostConstruct
	public void zabbixURL() throws NoSuchAlgorithmException, KeyManagementException {
		String url = messageSource.getMessage("zabbix.server.url", null, Locale.US);
		zabbixApi = new DefaultZabbixApi(url);
		zabbixApi.init();
	}

	public boolean loginValidate() {
		boolean login = zabbixApi.login(messageSource.getMessage("zabbix.username", null, Locale.US),
				messageSource.getMessage("zabbix.password", null, Locale.US));
		log.info(messageSource.getMessage(Constants.LOGIN_VALIDATE, new Object[] { login }, Locale.US));
		return login;
	}

}
