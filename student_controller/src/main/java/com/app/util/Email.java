package com.app.util;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.app.pojos.EmailData;




public class Email {
		
	
	public static void sendMail(EmailData emailData,String recipient) throws AddressException, MessagingException {
		Properties propertie = new Properties();
		propertie.put("mail.smtp.auth", true);
		propertie.put("mail.smtp.starttls.enable", true);
	    propertie.put("mail.smtp.host", "smtp.gmail.com");
	    propertie.put("mail.smtp.port", 587);
	    String myaccountEmail = emailData.getEmail();
	    String pass = emailData.getPassword();
	    
	    Session session = Session.getInstance(propertie, new Authenticator() {
	    	@Override
	    	protected PasswordAuthentication getPasswordAuthentication() {
	    		return new PasswordAuthentication(myaccountEmail, pass);
	    	}
	    });
	    
	    Message message = prepareMessage(session, emailData,recipient); 
	    Transport.send(message);
	    System.out.println("message sent");
	}
	
	public static Message prepareMessage(Session session, EmailData emailData, String to ) throws AddressException, MessagingException {
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(emailData.getEmail()));
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
		message.setSubject(emailData.getSubject());
		message.setText("messageDetail :{name: "+emailData.getName()+", email: "+emailData.getEmail()+", messageBody: "+emailData.getBody());
		return message;
	}
}
