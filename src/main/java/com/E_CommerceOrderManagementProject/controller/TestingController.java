package com.E_CommerceOrderManagementProject.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.E_CommerceOrderManagementProject.model.UserDetails;

@RestController
@RequestMapping("/test")
public class TestingController {

	
	private final UserDetails userDetails;
	
	public TestingController(UserDetails userDetails) {
		this.userDetails = userDetails;
	}
	
	@GetMapping("/getuserdetails")
	public UserDetails getUserDetails() {
		return userDetails;
	}
}
