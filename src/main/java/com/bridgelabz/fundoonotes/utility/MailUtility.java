package com.bridgelabz.fundoonotes.utility;

import java.io.Serializable;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.bridgelabz.fundoonotes.dto.MailDto;
@Component
public class MailUtility implements Serializable 
{
	@Autowired
	JavaMailSender mailsender;
	
	public void sendMail(MailDto maildto)
	{
		SimpleMailMessage message=new SimpleMailMessage();
		message.setTo(maildto.getEmail());
		message.setSubject(maildto.getSubject());
		message.setText(maildto.getResponse());
		mailsender.send(message);
	}

}
