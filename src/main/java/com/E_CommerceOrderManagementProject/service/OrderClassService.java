package com.E_CommerceOrderManagementProject.service;


import org.springframework.http.ResponseEntity;

import com.E_CommerceOrderManagementProject.dto.OrderClassRequestDto;
import com.E_CommerceOrderManagementProject.dto.OrderClassResponseDto;

public interface OrderClassService {

	public ResponseEntity<OrderClassResponseDto> orderProduct(OrderClassRequestDto orderClassRequestDto);

	public ResponseEntity<String> deleteSingleOrderItem(long id);

	public OrderClassResponseDto getOrderItemsWithId(long id);


}
