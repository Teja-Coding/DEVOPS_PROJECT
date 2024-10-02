package com.mini3.utils;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.internet.MimeMessage;

@Component
public class EmailUtils 
{
	private JavaMailSender mailSender;

	public EmailUtils(JavaMailSender mailSender)
	{
		this.mailSender = mailSender;
	}
	
	public boolean sendEmail(String to,String subject,String body)
	{
		boolean isMailSend=false;
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper=new MimeMessageHelper(mimeMessage);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(body,true);
			mailSender.send(mimeMessage);
			isMailSend=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isMailSend;
	}
	
}