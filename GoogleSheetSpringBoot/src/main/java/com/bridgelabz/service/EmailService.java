package com.bridgelabz.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService 
{
	 private JavaMailSender javaMailSender;
	 public EmailService(JavaMailSender javaMailSender) {
	        this.javaMailSender = javaMailSender;
	    }
	public void sendmail(String emailId, String subject, String message)
	{
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setTo(emailId);
		System.out.println("Mailid set"+emailId);
		simpleMailMessage.setSubject(subject);
		simpleMailMessage.setText(message);
		simpleMailMessage.setFrom("aheteshams007@gmail.com");
		javaMailSender.send(simpleMailMessage);
		System.out.println("Mail is Sent via simple mail sender from");
	}
	
}
