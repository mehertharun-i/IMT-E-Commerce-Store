package com.E_CommerceOrderManagementProject.controller;


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
	
	public OrderClassController(OrderClassService orderClassService) {
		this.orderClassService = orderClassService;
	}
	
	@PostMapping("/orderproduct")
	public ResponseEntity<OrderClassResponseDto> orderProduct(@RequestBody OrderClassRequestDto orderClassRequestDto) {
		ResponseEntity<OrderClassResponseDto> orderProduct = orderClassService.orderProduct(orderClassRequestDto);
		return orderProduct;
	}
	
	@DeleteMapping("/deletesingleorderitem/{id}")
	public ResponseEntity<String> deleteSingleOrderItem(@PathVariable(name = "id") long id) {
		ResponseEntity<String> orderItem = orderClassService.deleteSingleOrderItem(id);
		System.out.println("orderItem : "+orderItem);
		return orderItem;
	}
	
	@GetMapping("/getorderitemdetails")
	public OrderClassResponseDto getOrderItemsWithId(@RequestParam(name = "id") long id) {
		OrderClassResponseDto orderItemsWithId = orderClassService.getOrderItemsWithId(id);
		return orderItemsWithId;
	}
}
