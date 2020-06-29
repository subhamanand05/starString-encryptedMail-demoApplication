package com.assigment.email.service;

import javax.mail.internet.MimeMessage;

import com.assigment.email.model.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.assigment.email.logging.ApplicationLogger;
import com.assigment.email.util.MailEncryptionUtil;
import org.springframework.stereotype.Service;


@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private JavaMailSender mailSender;

	private static final ApplicationLogger logger = ApplicationLogger.getInstance();

	@Value("${assignment.mailRetry}")
	private int retryCount;
	
	public void send(Email email) {
		int i = 0;
		boolean anyError = false;
		do {
			try {
				i++;
				MimeMessage message = mailSender.createMimeMessage();
				MimeMessageHelper helper = new MimeMessageHelper(message);
				helper.setTo(email.getTo().toArray(new String[email.getTo().size()]));
				helper.setReplyTo(email.getFrom());
				helper.setFrom(email.getFrom());
				helper.setSubject(email.getSubject());
				if (email.isHtml()) {
					helper.setText(email.getMessage(), true);
				} else {
					helper.setText(email.getMessage());
				}
				if (email.isDESEncrypted()) {
					message = MailEncryptionUtil.encryptMessageWithDES(message);
				}
				if (email.isAESEncrypted()) {
					message = MailEncryptionUtil.encryptMessageWithAES(message);
				}
				mailSender.send(message);
			} catch (Exception e) {
				anyError = true;
				logger.error("Could not send email to : {} Error = {} in attempt = {}", email.getToAsList(), e.getMessage(), i);
			}
		} while (anyError && email.retryEnabled() && i < retryCount);

		
	}

}
