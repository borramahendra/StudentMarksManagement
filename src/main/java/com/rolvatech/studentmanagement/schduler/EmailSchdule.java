package com.rolvatech.studentmanagement.schduler;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.rolvatech.studentmanagement.model.EmailModel;
import com.rolvatech.studentmanagement.repo.EmailRepo;
import com.rolvatech.studentmanagement.service.EmailService;

@Component
public class EmailSchdule {
	
	@Autowired
	EmailRepo emailRepo;
	@Autowired
	EmailService emailService;
	
	@Scheduled(fixedRate = 60000)
	public void checkEmail() {
		
		LocalDateTime currentTime=LocalDateTime.now();
		
		List<EmailModel> emails=emailRepo.findAll();
		
		for(EmailModel email:emails) {
			
			if(!email.isStatus()&&email
					.getSchdule()!=null&&email.getSchdule()
					.isBefore(currentTime)) {
				
				emailService.sendEmail(email);
				email.setStatus(true);
				emailRepo.save(email);				
			}
			
		}
		
	}

}
