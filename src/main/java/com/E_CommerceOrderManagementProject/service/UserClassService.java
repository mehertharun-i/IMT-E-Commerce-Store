package com.E_CommerceOrderManagementProject.service;


import java.util.List;

import org.springframework.http.ResponseEntity;

import com.E_CommerceOrderManagementProject.dto.OrderClassResponseDto;
import com.E_CommerceOrderManagementProject.dto.UserClassRequestDto;
import com.E_CommerceOrderManagementProject.dto.UserClassResponseDto;


public interface UserClassService {

	public ResponseEntity<UserClassResponseDto> addUserDetailsIntoDB(UserClassRequestDto userClassRequestDto);

	public ResponseEntity<Void> deleteUserDetails(long id);
	
	public ResponseEntity<Void> deleteUserAddressDetails(int id);

	public ResponseEntity<List<UserClassResponseDto>> addAllUserDetailsIntoDB(List<UserClassRequestDto> userClassRequestDto);

	public ResponseEntity<List<OrderClassResponseDto>> userOrderDetails(long id);

	
	
}
