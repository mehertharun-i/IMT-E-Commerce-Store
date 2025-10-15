package com.E_CommerceOrderManagementProject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemsClassRequestDto {

	private long productId;
	
	private int quantity;
	
}
