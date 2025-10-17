package com.E_CommerceOrderManagementProject.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
//import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class OrderPaymentModeTypeClass {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long orderPaymentModeTypeId;
	
	@Column(nullable = false)
	private String orderPaymentModeTypeName;

}
