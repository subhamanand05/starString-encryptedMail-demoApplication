package com.assigment.email.util;

import java.io.File;
import java.io.FileInputStream;
import java.security.Security;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;

import org.bouncycastle.cms.CMSAlgorithm;
import org.bouncycastle.cms.jcajce.JceCMSContentEncryptorBuilder;
import org.bouncycastle.cms.jcajce.JceKeyTransRecipientInfoGenerator;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.mail.smime.SMIMEEnvelopedGenerator;


public class MailEncryptionUtil {


	public static MimeMessage encryptMessageWithDES(MimeMessage message) throws Exception  {
		
		Security.addProvider(new BouncyCastleProvider());
		
        // create the generator for creating an smime/encrypted message
        SMIMEEnvelopedGenerator  gen = new SMIMEEnvelopedGenerator();
        
        X509Certificate recipientCert = getRecipientPublicCertificate(message);
        
        gen.addRecipientInfoGenerator(new JceKeyTransRecipientInfoGenerator(recipientCert).setProvider("BC"));
		
		MimeBodyPart msg = new MimeBodyPart();
		msg.setContent(message.getContent(), message.getContentType());

		MimeBodyPart mp = gen.generate(msg, new JceCMSContentEncryptorBuilder(CMSAlgorithm.DES_CBC).setProvider("BC").build());
		message.setContent(mp.getContent(), mp.getContentType());
		message.saveChanges();
		
		return message;
	}

	public static MimeMessage encryptMessageWithAES(MimeMessage message) throws Exception  {

		Security.addProvider(new BouncyCastleProvider());

		// create the generator for creating an smime/encrypted message
		SMIMEEnvelopedGenerator  gen = new SMIMEEnvelopedGenerator();

		X509Certificate recipientCert = getRecipientPublicCertificate(message);

		gen.addRecipientInfoGenerator(new JceKeyTransRecipientInfoGenerator(recipientCert).setProvider("BC"));

		MimeBodyPart msg = new MimeBodyPart();
		msg.setContent(message.getContent(), message.getContentType());

		MimeBodyPart mp = gen.generate(msg, new JceCMSContentEncryptorBuilder(CMSAlgorithm.AES128_CBC).setProvider("BC").build());
		message.setContent(mp.getContent(), mp.getContentType());
		message.saveChanges();

		return message;
	}
	
	private static X509Certificate getRecipientPublicCertificate(MimeMessage message) throws Exception {
		
		CertificateFactory fact = CertificateFactory.getInstance("X.509");
		ClassLoader classLoader = MailEncryptionUtil.class.getClassLoader();
		File file = new File(classLoader.getResource("rsa-demoapp@testassignment.com.cer").getFile());
        FileInputStream is = new FileInputStream (file);
        X509Certificate recipientCert = (X509Certificate) fact.generateCertificate(is);
        return recipientCert;
	}
		

}
