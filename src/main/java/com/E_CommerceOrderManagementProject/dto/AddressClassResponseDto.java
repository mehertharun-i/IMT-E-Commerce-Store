package com.E_CommerceOrderManagementProject.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressClassResponseDto {
	
	private String userHouseNumber;
	
	private String userStreetName;
	
	private String userAreaName;
	
	private String userLandMarkName;
	
	private String userDistrictName;
	
	private String userStateName;
	
	private String userCountryName;
	
	private int userPinCodeNumber;
	
}
