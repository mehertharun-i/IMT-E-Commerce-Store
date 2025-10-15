package com.E_CommerceOrderManagementProject.dto;


import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderClassRequestDto {
	
	private List<OrderItemsClassRequestDto> orderItemsList;
	
	private long userClassId;
	
	private long orderPaymentModeCode;
	
	private int address;

}
