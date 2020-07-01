package com.intcomcorp.intcomcorpApplication.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intcomcorp.intcomcorpApplication.iccn.repo.PasswordResetTokenRepository;
import com.intcomcorp.intcomcorpApplication.model.PasswordResetToken;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TokenService {

	@Autowired
	PasswordResetTokenRepository tokenRepository;

	public PasswordResetToken findByToken(String token) {
		PasswordResetToken tokken = null;
		try {
			tokken = tokenRepository.findByToken(token);
		} catch (Exception e) {

			log.error("Error occured whhile loaddddddddding token", e);
		}

		return tokken;
	}

	public boolean delete(PasswordResetToken token) {
		   boolean isDelete = false;
		try {
			tokenRepository.delete(token);
			isDelete = true;
		} catch (Exception e) {
			log.error("Error while deleting token",e);
			isDelete = false;
		}
		
		return isDelete;
		
	}

}
