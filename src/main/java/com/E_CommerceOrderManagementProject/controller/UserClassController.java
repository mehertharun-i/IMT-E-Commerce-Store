package com.E_CommerceOrderManagementProject.controller;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.E_CommerceOrderManagementProject.dto.OrderClassResponseDto;
import com.E_CommerceOrderManagementProject.dto.UserClassRequestDto;
import com.E_CommerceOrderManagementProject.dto.UserClassResponseDto;
import com.E_CommerceOrderManagementProject.service.UserClassService;

@RestController
@RequestMapping("/user")
public class UserClassController {
	
	private final UserClassService userClassService;
	
	public UserClassController(UserClassService userClassService) {
		this.userClassService = userClassService;
	}
	
	@PostMapping("/insertuserdetails")
	public ResponseEntity<UserClassResponseDto> addUserDetailsIntoDB(@RequestBody UserClassRequestDto userClassRequestDto){
		ResponseEntity<UserClassResponseDto> userDetailsIntoDB = userClassService.addUserDetailsIntoDB(userClassRequestDto);
		return userDetailsIntoDB;
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
	
	@GetMapping("/getAllUserOrders/{userId}")
	public ResponseEntity<List<OrderClassResponseDto>> userOrdersDetials(@PathVariable(name = "userId") long id){
		ResponseEntity<List<OrderClassResponseDto>> userOrderDetails = userClassService.userOrderDetails(id);
		return userOrderDetails;
	}
	
	
	
}
