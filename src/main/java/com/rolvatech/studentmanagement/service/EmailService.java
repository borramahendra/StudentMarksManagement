package com.rolvatech.studentmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.rolvatech.studentmanagement.model.EmailModel;
import com.rolvatech.studentmanagement.repo.EmailRepo;

@Service
public class EmailService {
	@Autowired
	EmailRepo emailRepo;
	@Autowired
	JavaMailSender mailSender;
	
	public void sendEmail(EmailModel em) {
		SimpleMailMessage message=new SimpleMailMessage();
		
		message.setTo(em.getToEmail());
		message.setSubject(em.getSubject());
		message.setText(em.getText());
		
		mailSender.send(message);
		
		
		
	}

}
