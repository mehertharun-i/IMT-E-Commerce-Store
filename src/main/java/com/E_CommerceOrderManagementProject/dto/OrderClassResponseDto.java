package com.E_CommerceOrderManagementProject.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderClassResponseDto {

	private long OrderId;
	
	private String Status;

	private double totalPrice;
	
	private String paymentStatus;
	
	private String paymentMode;
	
	private LocalDateTime paymentDateAndTime;
	
	private List<OrderItemsCLassResponseDto> orderItems;
	

}
