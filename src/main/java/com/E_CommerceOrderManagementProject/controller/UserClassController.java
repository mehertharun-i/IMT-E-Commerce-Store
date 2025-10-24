package com.E_CommerceOrderManagementProject.controller;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.E_CommerceOrderManagementProject.dto.LoginDetailsRequestDto;
import com.E_CommerceOrderManagementProject.dto.OrderClassResponseDto;
import com.E_CommerceOrderManagementProject.dto.UserClassRequestDto;
import com.E_CommerceOrderManagementProject.dto.UserClassResponseDto;
import com.E_CommerceOrderManagementProject.service.UserClassService;

@RestController
@RequestMapping("/user")
public class UserClassController {
	
	private final UserClassService userClassService;
	
	private final PasswordEncoder passwordEncoder;
	
	public UserClassController(UserClassService userClassService, PasswordEncoder passwordEncoder) {
		this.userClassService = userClassService;
		this.passwordEncoder = passwordEncoder;
	}
	
	@PostMapping("/insertuserdetails")
	public ResponseEntity<UserClassResponseDto> addUserDetailsIntoDB(@RequestBody UserClassRequestDto userClassRequestDto){
		String encodedPassword = passwordEncoder.encode(userClassRequestDto.getUserPassword());
		userClassRequestDto.setUserPassword(encodedPassword);
		ResponseEntity<UserClassResponseDto> userDetailsIntoDB = userClassService.addUserDetailsIntoDB(userClassRequestDto);
		return userDetailsIntoDB;
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody LoginDetailsRequestDto loginDetails){
		return ResponseEntity.ok().body("You are Successfully Logged in!");
	}
	
	@PostMapping("/insertallusersdetails")
	public ResponseEntity<List<UserClassResponseDto>> addAllUserDetailsIntoDB(@RequestBody List<UserClassRequestDto> userClassRequestDto){
		ResponseEntity<List<UserClassResponseDto>> allUserDetailsIntoDB = userClassService.addAllUserDetailsIntoDB(userClassRequestDto);
		return allUserDetailsIntoDB;
	}
	
	@DeleteMapping("/deleteuserdetails")
	public ResponseEntity<Void> deleteUserDetails(@RequestParam long id) {
		ResponseEntity<Void> deleteUserDetails = userClassService.deleteUserDetails(id);
		return deleteUserDetails;
		
	}
	
	@DeleteMapping("/deleteuseraddressdetails")
	public ResponseEntity<Void> deleteUserAddressDetails(@RequestParam int id) {
		ResponseEntity<Void> deleteUserAddressDetails = userClassService.deleteUserAddressDetails(id);
		return deleteUserAddressDetails;
	}
	
	@GetMapping("/getuserorderdetails/{userId}")
	public ResponseEntity<List<OrderClassResponseDto>> userOrdersDetials(@PathVariable(name = "userId") long id){
		ResponseEntity<List<OrderClassResponseDto>> userOrderDetails = userClassService.userOrderDetails(id);
		return userOrderDetails;
	}
	
	
	
}
