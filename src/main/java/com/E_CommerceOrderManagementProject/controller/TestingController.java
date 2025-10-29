package com.E_CommerceOrderManagementProject.controller;

import javax.crypto.SecretKey;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.E_CommerceOrderManagementProject.model.UserDetails;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Encoders;

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
	
	@GetMapping("/hi")
	public String hi() {
		return "Hi Meher";
	}
	
	@GetMapping("/encode")
	public String Base64Encoder() {
   	 	SecretKey key = Jwts.SIG.HS256.key().build();
   	 	String base64Key = Encoders.BASE64.encode(key.getEncoded());
   	 	return key +"   <->    "+ base64Key;
	}
}
