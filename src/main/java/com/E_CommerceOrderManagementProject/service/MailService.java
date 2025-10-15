package com.E_CommerceOrderManagementProject.service;

import org.springframework.http.ResponseEntity;

public interface MailService {

	public ResponseEntity<String> mailSend(String mailTo);
	
}
