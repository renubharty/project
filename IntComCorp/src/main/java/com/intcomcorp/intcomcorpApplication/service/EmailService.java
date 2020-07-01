package com.intcomcorp.intcomcorpApplication.service;

import com.intcomcorp.intcomcorpApplication.dto.EmailDto;
import com.intcomcorp.intcomcorpApplication.dto.PasswordResetDto;


public interface EmailService {
	
	public void sendMail(EmailDto emailDto);
	public boolean createPasswordResetToken(PasswordResetDto passwordResetDto);

}
