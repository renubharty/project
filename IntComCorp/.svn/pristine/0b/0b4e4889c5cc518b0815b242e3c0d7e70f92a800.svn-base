package com.intcomcorp.intcomcorpApplication.service.impl;

import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.Properties;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import com.intcomcorp.intcomcorpApplication.dto.Mail;

@Service
public class EmailService {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private SpringTemplateEngine templateEngine;

	public void sendEmail(Mail mail) {

		try {

			Properties props = new Properties();
			props.put("mail.smtp.host", messageSource.getMessage("spring.mail.host", null, Locale.US));
			props.put("mail.smtp.port", messageSource.getMessage("spring.mail.port", null, Locale.US));
			props.put("mail.smtp.auth", messageSource.getMessage("spring.mail.smtp.auth", null, Locale.US));
			props.put("mail.smtp.starttls.enable",  messageSource.getMessage("spring.mail.smtp.starttls.enable", null, Locale.US));
		
			Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {

					return new PasswordAuthentication("PUT THE MAIL SENDER HERE !",
							"PUT THE PASSWORD OF THE MAIL SENDER HERE !");
				}
			});

			MimeMessage message = new MimeMessage(session);
			MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
					StandardCharsets.UTF_8.name());

			Context context = new Context();
			context.setVariables(mail.getModel());
			String html = templateEngine.process("email/email-template", context);

			helper.setTo(mail.getTo());
			helper.setText(html, true);
			helper.setSubject(mail.getSubject());
			helper.setFrom(mail.getFrom());

			Transport transport = session.getTransport("smtp");
			transport.connect(mail.getFrom(), messageSource.getMessage("spring.mail.password", null, Locale.US));
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
