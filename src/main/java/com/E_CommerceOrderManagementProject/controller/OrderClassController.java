package com.E_CommerceOrderManagementProject.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.E_CommerceOrderManagementProject.dto.OrderClassRequestDto;
import com.E_CommerceOrderManagementProject.dto.OrderClassResponseDto;
import com.E_CommerceOrderManagementProject.service.OrderClassService;

@RestController
@RequestMapping("/order")
public class OrderClassController {

	private final OrderClassService orderClassService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderClassController.class);
	
	public OrderClassController(OrderClassService orderClassService) {
		this.orderClassService = orderClassService;
	}
	
	@PostMapping("/orderproduct")
	public ResponseEntity<OrderClassResponseDto> orderProduct(@RequestBody OrderClassRequestDto orderClassRequestDto) {
		LOGGER.debug("Entered into OrderProduct method with API Endpoint of (/orderproduct) : {}", orderClassRequestDto);
		ResponseEntity<OrderClassResponseDto> orderProduct = orderClassService.orderProduct(orderClassRequestDto);
		LOGGER.debug("Exiting from OrderProduct and returning a response as : {}", orderProduct);		
		return orderProduct;
	}
	
	@DeleteMapping("/deletesingleorderitem/{id}")
	public ResponseEntity<String> deleteSingleOrderItem(@PathVariable(name = "id") long id) {
		LOGGER.debug("Entered into deleteSingleOrderItem method with API Endpoint of (/deletesingleorderitem/{})", id);
		ResponseEntity<String> orderItem = orderClassService.deleteSingleOrderItem(id);
		LOGGER.debug("Exiting from deleteSingleOrderItem and returning a response as : {}", orderItem);
		return orderItem;
	}
	
	@GetMapping("/getorderdetails")
	public OrderClassResponseDto getOrderItemsWithId(@RequestParam(name = "id") long id) {
		LOGGER.debug("Entered into getOrderItemsWithId method with API Endpoint of (/getorderitemdetails/{})", id);
		OrderClassResponseDto orderItemsWithId = orderClassService.getOrderItemsWithId(id);
		LOGGER.debug("Exiting from getOrderItemsWithId and returning a response as : {}", orderItemsWithId);		
		return orderItemsWithId;
	}
}
