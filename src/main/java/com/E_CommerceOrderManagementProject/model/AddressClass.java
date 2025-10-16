package com.E_CommerceOrderManagementProject.model;

import java.util.List;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressClass {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userAddressId;
	
	@Column(nullable = false)
	private String userHouseNumber;
	
	private String userStreetName;
	
	@Column(nullable = false)
	private String userAreaName;
	
	private String userLandMarkName;
	
	@Column(nullable = false)
	private String userDistrictName;
	
	@Column(nullable = false)
	private String userStateName;
	
	@Column(nullable = false)
	private String userCountryName;
	
	private int userPinCodeNumber;
	
	@ManyToOne
	@JoinColumn(name = "userClass_id", nullable = false)
	@ToString.Exclude
	private UserClass userClass;
	
	@OneToMany(mappedBy = "addressClass", cascade = CascadeType.ALL, orphanRemoval = true)
	@ToString.Exclude
	private List<OrderClass> orderClass;

	public AddressClass(String userHouseNumber, String userStreetName, String userAreaName, String userLandMarkName,
			String userDistrictName, String userStateName, String userCountryName, int userPinCodeNumber, UserClass userClass,
			List<OrderClass> orderClass) {
		super();
		this.userHouseNumber = userHouseNumber;
		this.userStreetName = userStreetName;
		this.userAreaName = userAreaName;
		this.userLandMarkName = userLandMarkName;
		this.userDistrictName = userDistrictName;
		this.userStateName = userStateName;
		this.userCountryName = userCountryName;
		this.userPinCodeNumber = userPinCodeNumber;
		this.userClass = userClass;
		this.orderClass = orderClass;
	}
}
