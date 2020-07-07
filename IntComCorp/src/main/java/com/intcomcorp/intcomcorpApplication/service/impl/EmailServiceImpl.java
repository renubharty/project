
package com.intcomcorp.intcomcorpApplication.service.impl;

import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.Properties;
import java.util.UUID;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import com.intcomcorp.intcomcorpApplication.dto.EmailDto;
import com.intcomcorp.intcomcorpApplication.dto.PasswordResetDto;
import com.intcomcorp.intcomcorpApplication.iccn.repo.TokenRepository;
import com.intcomcorp.intcomcorpApplication.model.PasswordResetToken;
import com.intcomcorp.intcomcorpApplication.service.EmailService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmailServiceImpl implements EmailService {

	@Autowired
	public JavaMailSender emailSender;

	@Autowired
	TokenRepository tokenRepository;

	@Autowired
	private SpringTemplateEngine templateEngine;

	@Autowired
	private MessageSource messageSource;

	@Value("${spring.mail.host}")
	private String host;
	@Value("${spring.mail.port}")
	private String port;
	@Value("${spring.mail.properties.mail.smtp.auth}")
	private String auth;
	@Value("${spring.mail.properties.mail.smtp.starttls.enable}")
	private String enable;
	@Value("${spring.mail.password}")
	private String password;

	@Override
	@Async
	public void sendMail(EmailDto emailDto) {
		log.info("EmailServiceImpl method sendSimpleMail Start email sending to " + emailDto.getTo());

		try {
			Properties props = new Properties();
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.port", port);
			props.put("mail.smtp.auth", auth);
			props.put("mail.smtp.starttls.enable", enable);
			props.put("mail.debug", "true");
			props.put("mail.smtp.socketFactory.class", 
	                "javax.net.ssl.SSLSocketFactory");
			Session session = Session.getInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(emailDto.getFrom(), password);
				}
			});

			Context context = new Context();
			context.setVariables(emailDto.getModel());
			String html = templateEngine.process("email/email-template", context);

			MimeMessage mimeMessage = new MimeMessage(session);
			MimeMessageHelper message = new MimeMessageHelper(mimeMessage,
					MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
			message.setFrom(emailDto.getFrom());
			message.setTo(emailDto.getTo());
			message.setText(html, true);
			message.setSubject(emailDto.getSubject());
			Transport transport = session.getTransport("smtp");
			transport.connect(emailDto.getFrom(), messageSource.getMessage("spring.mail.password", null, Locale.US));
			transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
			transport.close();
			log.info("EmailServiceImpl method sendSimpleMail Email sent successfully to " + emailDto.getTo());
		} catch (MailException me) {
			log.error("Error while sending email to " + emailDto.getTo(), me);
		} catch (Exception e) {
			log.error("Error occured while sending eamil", e);
		}

	}

	@Override
	public boolean createPasswordResetToken(PasswordResetDto passwordResetDto) {
		log.info("method createPasswordResetToken begin");
		boolean isTokenCreated = false;
		PasswordResetToken passwordResetTokenEntity = new PasswordResetToken();
		passwordResetTokenEntity.setExpiryDate(24 * 60);
		passwordResetTokenEntity.setUser(passwordResetDto.getUser());
		passwordResetTokenEntity.setToken(UUID.randomUUID().toString());
		passwordResetDto.setToken(passwordResetTokenEntity.getToken());
		try {
			tokenRepository.save(passwordResetTokenEntity);
			isTokenCreated = true;
		} catch (Exception e) {
			isTokenCreated = false;
			log.error("Error occured while creating Password Reset Token", e);
		}
		log.info("method createPasswordResetToken finish");
		return isTokenCreated;
	}

}