package com.E_CommerceOrderManagementProject.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.E_CommerceOrderManagementProject.service.MailService;

@RestController
@RequestMapping("/mail")
public class MailSenderController {
	
	private final MailService mailService;
	
	public MailSenderController(MailService mailService) {
		this.mailService = mailService;
	}
	
	@PostMapping("/send")
	public ResponseEntity<String> mailSender(@RequestParam(name="mailTo")String mailTo){
		ResponseEntity<String> mailSend = mailService.mailSend(mailTo);
		return mailSend;
	}
}
