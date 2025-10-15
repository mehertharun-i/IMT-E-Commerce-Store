package com.E_CommerceOrderManagementProject.dto;

import lombok.Data;

@Data
public class ProductRequestDto {

	private String productName;
	
	private double productPrice;
	
	private double productDiscount;
	
	private int productStock;
}
