package com.E_CommerceOrderManagementProject.dto;


import java.util.List;

import com.E_CommerceOrderManagementProject.model.Authorities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserClassResponseDto {
	
	private String userFirstName;
	
	private String userLastName;
	
	private String userEmail;
	
	private String userPhoneNumber;
	
	private List<AddressClassResponseDto> addressClass;
	
	private List<Authorities> authorities;

}
