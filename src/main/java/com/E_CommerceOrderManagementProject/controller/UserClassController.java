package com.E_CommerceOrderManagementProject.controller;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	private static final Logger LOGGER = LoggerFactory.getLogger(UserClassController.class);
	
	public UserClassController(UserClassService userClassService) {
		this.userClassService = userClassService;
	}
	
	@PostMapping("/insertuserdetails")
	public ResponseEntity<UserClassResponseDto> addUserDetailsIntoDB(@RequestBody UserClassRequestDto userClassRequestDto){
		LOGGER.debug("Entered into AddUserDetailsIntoDB method with API Endpoint of (/insertuserdetails) : {}", userClassRequestDto);
		ResponseEntity<UserClassResponseDto> userDetailsIntoDB = userClassService.addUserDetailsIntoDB(userClassRequestDto);
		LOGGER.debug("Exiting from AddUserDetailsIntoDB and returning a response as : {}", userDetailsIntoDB);
		return userDetailsIntoDB;
	}
	
	@PostMapping("/insertallusersdetails")
	public ResponseEntity<List<UserClassResponseDto>> addAllUserDetailsIntoDB(@RequestBody List<UserClassRequestDto> userClassRequestDto){
		LOGGER.debug("Entered into AddAllUserDetailsIntoDB method with API Endpoint of (/insertalluserdetails) : {}", userClassRequestDto);
		ResponseEntity<List<UserClassResponseDto>> allUserDetailsIntoDB = userClassService.addAllUserDetailsIntoDB(userClassRequestDto);
		LOGGER.debug("Exiting from AddUserDetailsIntoDB and returning a response as : {}", allUserDetailsIntoDB);
		return allUserDetailsIntoDB;
	}
	
	@DeleteMapping("/deleteuserdetails")
	public ResponseEntity<Void> deleteUserDetails(@RequestParam long id) {
		LOGGER.debug("Entered into DeleteUserDetails method with API Endpoint of (/deleteuserdetails) : {}", id);
		ResponseEntity<Void> deleteUserDetails = userClassService.deleteUserDetails(id);
		LOGGER.debug("Exiting from DeleteUserDetails and returning a response as : {}", deleteUserDetails);
		return deleteUserDetails;
		
	}
	
	@DeleteMapping("/deleteuseraddressdetails")
	public ResponseEntity<Void> deleteUserAddressDetails(@RequestParam int id) {
		LOGGER.debug("Entered into DeleteUserAddressDetails method with API Endpoint of (/deleteuseraddressdetails) : {}", id);
		ResponseEntity<Void> deleteUserAddressDetails = userClassService.deleteUserAddressDetails(id);
		LOGGER.debug("Exiting from DeleteUserAddressDetails and returning a response as : {}", deleteUserAddressDetails);
		return deleteUserAddressDetails;
	}
	
	@GetMapping("/getAllUserOrders/{userId}")
	public ResponseEntity<List<OrderClassResponseDto>> userOrdersDetials(@PathVariable(name = "userId") long id){
		LOGGER.debug("Entered into UserOrdersDetails method with API Endpoint of (/getAllUserOrders/{})", id);
		ResponseEntity<List<OrderClassResponseDto>> userOrderDetails = userClassService.userOrderDetails(id);
		LOGGER.debug("Exiting from UserOrderDetails and returning a response as : {}", userOrderDetails);
		return userOrderDetails;
	}
	
	
	
}
