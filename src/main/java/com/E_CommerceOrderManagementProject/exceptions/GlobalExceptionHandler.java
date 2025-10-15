package com.E_CommerceOrderManagementProject.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(OrderItemIdNotFound.class)
	public ResponseEntity<String> handleOrderItemIdNotFound(OrderItemIdNotFound exceptionMessage) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionMessage.getMessage());
	}
	
	@ExceptionHandler(ProductIdNotFoundException.class)
	public ResponseEntity<String> handleProductIdNotFoundException(ProductIdNotFoundException exceptionMessage){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionMessage.getMessage());
	}
	
	@ExceptionHandler(OrderPaymentModeTypeClassIdNotFoundException.class)
	public ResponseEntity<String> handleOrderPaymentModeTypeClassIdNotFOundException(OrderPaymentModeTypeClassIdNotFoundException exceptionMessage){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionMessage.getMessage());
	}
	
	@ExceptionHandler(UserClassIdNotFoundException.class)
	public ResponseEntity<String> handleUserClassIdNotFoundException(UserClassIdNotFoundException exceptionMessage){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionMessage.getMessage());
	}
	
	@ExceptionHandler(AddressClassIdNotFoundException.class)
	public ResponseEntity<String> handleAddressClassIdNotFoundException(AddressClassIdNotFoundException exceptionMessage){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionMessage.getMessage());
	}
	
	@ExceptionHandler(ProductQuantityExceededException.class)
	public ResponseEntity<String> handleProductQuantityExceededException(ProductQuantityExceededException exceptionMessage){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionMessage.getMessage());
	}
	
	@ExceptionHandler(OrderClassIdNotFoundException.class)
	public ResponseEntity<String> handleOrderClassIdNotFoundException(OrderClassIdNotFoundException exceptionMessage){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionMessage.getMessage());
	}
	
}
