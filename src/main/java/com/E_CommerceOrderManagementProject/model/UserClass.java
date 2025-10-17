package com.E_CommerceOrderManagementProject.model;

import java.sql.Date;
import java.util.List;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserClass {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long userId;
	
	@Column(nullable = false)
	private String userFirstName;
	
	@Column(nullable = false)
	private String userLastName;
	
	@Column(nullable = false, unique = true)
	private String userEmail;
	
	@Column(nullable = false, unique = true)
	private String userPhoneNumber;
	
	private Date userDateOfBirth;
	
	@OneToMany(mappedBy = "userClass", cascade = CascadeType.ALL, orphanRemoval = true)
	@ToString.Exclude
	private List<AddressClass> addressClass;
	
	@Column(nullable = false, unique = true)
	private String userLoginId;
	
	@Column(nullable = false)
	private String userPassword;
	
	@OneToMany(mappedBy = "userDetails", cascade = CascadeType.ALL, orphanRemoval = true)
	@ToString.Exclude
	private List<OrderClass> userOrdersClass;

	public UserClass(String userFirstName, String userLastName, String userEmail, String userPhoneNumber,
			Date userDateOfBirth, List<AddressClass> addressClass, String userLoginId, String userPassword,
			List<OrderClass> userOrdersClass) {
		super();
		this.userFirstName = userFirstName;
		this.userLastName = userLastName;
		this.userEmail = userEmail;
		this.userPhoneNumber = userPhoneNumber;
		this.userDateOfBirth = userDateOfBirth;
		this.addressClass = addressClass;
		this.userLoginId = userLoginId;
		this.userPassword = userPassword;
		this.userOrdersClass = userOrdersClass;
	}
	
	

}
