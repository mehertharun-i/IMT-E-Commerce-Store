package com.E_CommerceOrderManagementProject.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.E_CommerceOrderManagementProject.dto.ProductRequestDto;
import com.E_CommerceOrderManagementProject.dto.ProductResponseDto;

public interface ProductService {

	public ProductResponseDto saveProduct(ProductRequestDto productRequestDto);
	
	public ResponseEntity<ProductResponseDto> getProductById(long id);
	
	public ResponseEntity<List<ProductResponseDto>> getAllProducts();
	
	public List<ProductResponseDto> findByProductPriceBetween(double min, double max);
	
	public List<ProductResponseDto> findProductsByNameContaining(String productName);
	
	public ProductResponseDto updateRating(long id, double rating);

	public String deleteProductById(long id);

	public List<ProductResponseDto> saveAll(List<ProductRequestDto>productRequestDto);

	public ProductResponseDto updateIsAvailableAndProductStock(long id, boolean isAvailable, int productStock);

	public List<ProductResponseDto> getProductsIsAvailable();

	public List<ProductResponseDto> getRatingGreaterThan(double rating);

	public List<ProductResponseDto> productRatingGreaterThanAvailableStock(double rating, boolean isAvailable);
	
}
