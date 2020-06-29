package com.assigment;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.assigment.email.model.Email;
import com.assigment.email.service.EmailService;
import com.assigment.email.template.EmailTemplate;
import com.assigment.email.logging.ApplicationLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties
@SpringBootApplication
public class EncryptedMailApplication implements ApplicationRunner {
	
	@Autowired
	EmailService emailService;

	private static final ApplicationLogger logger = ApplicationLogger.getInstance();
	
	public static void main(String[] args) {SpringApplication.run(EncryptedMailApplication.class, args); }

	@Override
	public void run(ApplicationArguments arg0) throws Exception {
		logger.info("EncryptedMail Demo Application Started !!");
		// Scenario 1
		emailService.send(sendPlainTextEmailExternalWithDisclaimer());
		// Scenario 2
		emailService.send(sendHtmlEmailInternalWithDESEncryption());
		// Scenario 3
		emailService.send(sendHtmlEmailExternalWithAESEncryption());
		// Scenario 4
		emailService.send(sendPlainTextEmailExternalWithBothEncryption());

        logger.info("EncryptedMail Demo Application Ended !!");
	}


	// Scenario 1
	private Email sendPlainTextEmailExternalWithDisclaimer() {
		logger.info("Sending Plain text uncrypted email with disclaimer to outside resource !!");
		String from = "demoapp@testassignment.com";
		String to = "demouser@outside.com";
		String subject = "Plain Text Mail - External";

		EmailTemplate template = new EmailTemplate("assignment-plain-disclaimer.txt");

		Map<String, String> replacements = new HashMap<>();
		replacements.put("today", String.valueOf(new Date()));

		String message = template.getTemplate(replacements);

		return new Email(from, to, subject, message);
	}

	// Scenario 2
	private Email sendHtmlEmailInternalWithDESEncryption() {
        logger.info("Sending HTML DES encrypted email without disclaimer to internal resource !!");
		String from = "demoapp@testassignment.com";
		String to = "demouser@testassignment.com";
		String subject = "Encrypted: HTML Mail - Internal";

		EmailTemplate template = new EmailTemplate("assignment-html.html");

		Map<String, String> replacements = new HashMap<>();
		replacements.put("today", String.valueOf(new Date()));

		String message = template.getTemplate(replacements);

		return new Email(from, to, subject, message, true, false, true, true, false);
	}

	// Scenario 3
	private Email sendHtmlEmailExternalWithAESEncryption() {
        logger.info("Sending HTML AES encrypted email with disclaimer to external resource !!");
		String from = "demoapp@testassignment.com";
		String to = "demouser@outside.com";
		String subject = "Encrypted: HTML Mail - External";

		EmailTemplate template = new EmailTemplate("assignment-html-disclaimer.html");

		Map<String, String> replacements = new HashMap<>();
		replacements.put("today", String.valueOf(new Date()));

		String message = template.getTemplate(replacements);

		return new Email(from, to, subject, message, true, true, true, false, true);
	}

	// Scenario 4
	private Email sendPlainTextEmailExternalWithBothEncryption() {
        logger.info("Sending Plain Text DES and then AES encrypted email with disclaimer to external resource !!");
		String from = "demoapp@testassignment.com";
		String to = "demouser@outside.com";
		String subject = "Encrypted: Plain Text Mail - External";

		EmailTemplate template = new EmailTemplate("assignment-plain-disclaimer.txt");

		Map<String, String> replacements = new HashMap<>();
		replacements.put("today", String.valueOf(new Date()));

		String message = template.getTemplate(replacements);

		return new Email(from, to, subject, message, false, true, true, true, true);
	}
}


