package com.E_CommerceOrderManagementProject.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ProductResponseDto {
		
	private String productName;
	
	private double productPrice;
	
	private double productDiscount;
	
	private boolean productIsAvailable;
	
	private double productRating;
	
	private int productStock;
	
	private LocalDateTime productCreatedDate;

}
