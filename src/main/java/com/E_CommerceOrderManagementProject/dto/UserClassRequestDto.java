package com.E_CommerceOrderManagementProject.dto;

import java.sql.Date;
import java.util.List;

import com.E_CommerceOrderManagementProject.model.AddressClass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserClassRequestDto {
	
	private String userFirstName;
	
	private String userLastName;
	
	private String userEmail;
	
	private String userPhoneNumber;
	
	private Date userDateOfBirth;
	
	private List<AddressClass> addressClass;
	
	private String userLoginId;
	
	private String userPassword;
	
}
