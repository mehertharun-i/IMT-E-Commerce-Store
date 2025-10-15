package com.E_CommerceOrderManagementProject.service.implementation;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.E_CommerceOrderManagementProject.service.MailService;

@Service
public class MailServiceImplementation implements MailService{
	
	@Autowired
	JavaMailSender javaMailSender;

	@Override
	public ResponseEntity<String> mailSend(String mailTo) {

		SecureRandom otp = new SecureRandom();
		StringBuilder randoms = new StringBuilder();
		int length = 6;
		
		for(int i = 0 ; i < length; i++) {
			randoms.append(otp.nextInt(10));
		}
		
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setFrom("mehertharun10@gmail.com");
		mail.setTo(mailTo);
		mail.setSubject("Gentle Reminder, What is the Progress the Today's Task has it Completed or Not, any latest updates");
		mail.setText("Dear, "+ mailTo.replaceAll("((@.*)|[^a-zA-Z])+", " ").trim()+"\n\n\nReminder-1, Checking for the latest updates on Tasks assigned to you \n\n\nOTP : "+randoms.toString()+" \n\n\nFrom:\nMeher Tharun Irukoti \nIMT Store \nHyderabad");
//		mail.setText("Reminder, Checking for the latest updates on Tasks assigned to you");
//		mail.setText("From:");
//		mail.setText("Meher tharun irukoti");
//		mail.setText("IMT Store");
//		mail.setText("Hyderabad");
		
		javaMailSender.send(mail);
		return ResponseEntity.status(HttpStatus.OK).body("Mail Sent Successfully");
	}

}
