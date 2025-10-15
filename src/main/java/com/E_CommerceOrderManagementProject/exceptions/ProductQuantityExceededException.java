package com.E_CommerceOrderManagementProject.exceptions;

public class ProductQuantityExceededException extends RuntimeException {

	public ProductQuantityExceededException(String exceptionMessage) {
		super(exceptionMessage);
	}
}
