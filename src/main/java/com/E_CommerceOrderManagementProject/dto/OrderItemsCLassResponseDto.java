package com.E_CommerceOrderManagementProject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemsCLassResponseDto {
	
	private String productName;
	
	private int Quantity;
	
	private double ProductPrice;
	
	private double totalQuantityProductPrice;
	
}
