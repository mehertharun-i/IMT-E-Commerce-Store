package com.E_CommerceOrderManagementProject.service.implementation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.E_CommerceOrderManagementProject.dao.ProductRepository;
import com.E_CommerceOrderManagementProject.dto.ProductRequestDto;
import com.E_CommerceOrderManagementProject.dto.ProductResponseDto;
import com.E_CommerceOrderManagementProject.exceptions.ProductIdNotFoundException;
import com.E_CommerceOrderManagementProject.model.ProductsClass;
import com.E_CommerceOrderManagementProject.service.ProductService;


@Service
public class ProductServiceImplementation implements ProductService{

	private final ProductRepository productRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImplementation.class);
	
	public ProductServiceImplementation(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	
	@Override
	public ProductResponseDto saveProduct(ProductRequestDto productRequestDto) {
		LOGGER.debug("saveProduct method called");
		LOGGER.trace("ProductRequestDto Value : {}", productRequestDto);
		ProductsClass productsClass = new ProductsClass();
		productsClass.setProductName(productRequestDto.getProductName());
		productsClass.setProductPrice(productRequestDto.getProductPrice());
		productsClass.setProductDiscount(productRequestDto.getProductDiscount());
		if(productRequestDto.getProductStock() > 0) {
			productsClass.setProductIsAvailable(true);
		}else {
			LOGGER.warn("ProductStock is Zero or Lesser : {}", productRequestDto.getProductStock());
			productsClass.setProductIsAvailable(false);
		}
		productsClass.setProductRating(0);
		productsClass.setProductStock(productRequestDto.getProductStock());
		productsClass.setProductCreatedDate(LocalDateTime.now());
		
		ProductsClass savedProduct = productRepository.save(productsClass);
		
		ProductResponseDto productResponseDto = new ProductResponseDto();
		
		BeanUtils.copyProperties(savedProduct, productResponseDto);
		LOGGER.debug("Returning from SaveProduct : {}", productResponseDto);
		return productResponseDto;
	}


	@Override
	public ResponseEntity<ProductResponseDto> getProductById(long id) {
		LOGGER.debug("GetProductByID method is called : id -> {}",id);
		ProductsClass productsClass1 = productRepository.findById(id).orElseThrow(() -> {
			LOGGER.error("Exception occured for ProductIdNotFoundException : {} ", id);
			return new ProductIdNotFoundException("Invalid Product Id Not Found :" +id);
			});
		ProductResponseDto productResponseDto = new ProductResponseDto();
		BeanUtils.copyProperties(productsClass1, productResponseDto);
		LOGGER.debug("Returning from GetProductById : {}", productResponseDto);
		return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
	}
	
	@Override
	public ResponseEntity<List<ProductResponseDto>> getAllProducts(){
		LOGGER.debug("GetAllProducts method is called");
		List<ProductsClass> productList = productRepository.findAll();
		
		List<ProductResponseDto> copyingProductsToProductsList = copyingProductsToProductsList(productList);
		LOGGER.debug("Returning from GetAllProducts : {}", copyingProductsToProductsList);
		return ResponseEntity.status(HttpStatus.OK).body(copyingProductsToProductsList);		
	}

	@Override
	public List<ProductResponseDto> findByProductPriceBetween(double min, double max) {
		LOGGER.debug("FindByProductPriceBetween method is called");
		List<ProductsClass> productList = productRepository.findByProductPriceBetween(min, max);
		
		List<ProductResponseDto> copyingProductsToProductsList = copyingProductsToProductsList(productList);
		LOGGER.debug("Returning from FindByProductPriceBetween : {}", copyingProductsToProductsList);
		return copyingProductsToProductsList;
	}
	
	@Override
	public List<ProductResponseDto> findProductsByNameContaining(String productName) {
		LOGGER.debug("FindProductsByNameContaining method is called : ProductName -> {}", productName);
		List<ProductsClass> productList = productRepository.findByProductNameContaining(productName);
		
		List<ProductResponseDto> copyingProductsToProductsList = copyingProductsToProductsList(productList);
		LOGGER.debug("Returning from FindProductsByNameContaining : {}", copyingProductsToProductsList);
		return copyingProductsToProductsList;
	}
	
	@Override
	public ProductResponseDto updateRating(long id, double rating) {
		LOGGER.debug("UpdateRating method is called : if -> {}, rating -> {}", id , rating);
		
		ProductsClass productId = productRepository.findById(id).orElseThrow( () -> {
			LOGGER.error("Exception occured for ProductIdNotFoundException : {}", id);
			return new ProductIdNotFoundException("Invalid Product Id : "+id);
		});
		
		productId.setProductRating(rating);
		ProductsClass save = productRepository.save(productId);
		ProductResponseDto productResponseDto = new ProductResponseDto();
		BeanUtils.copyProperties(save, productResponseDto);
		
		LOGGER.debug("Returning from UpdateRating : {}", productResponseDto);
		return productResponseDto;
	}
	
	@Override
	public String deleteProductById(long id) {
		LOGGER.debug("DeleteProductById method called : id -> {}", id);
		ProductsClass productsClass = productRepository.findById(id).orElseThrow( () -> {
			LOGGER.error("Exception Occured for ProductIdNotFoundException : {}", id);
			return new ProductIdNotFoundException("Invalid Product Id : "+id);
		});
		
		productRepository.delete(productsClass);
		LOGGER.debug("Returning from DeleteProductById : {}", productsClass.getProductName());
		return productsClass.getProductName();
	}
	

	@Override
	public List<ProductResponseDto> saveAll(List<ProductRequestDto> productRequestDtoList) {
		LOGGER.debug("SaveAll method called : List of Products {}", productRequestDtoList);
		
		List<ProductsClass> productsClassList = new ArrayList<ProductsClass>();
		
		for(ProductRequestDto productRequestDto : productRequestDtoList) {
			ProductsClass productsClass = new ProductsClass();
			BeanUtils.copyProperties(productRequestDto, productsClass);
			if(productRequestDto.getProductStock() > 0) {
				productsClass.setProductIsAvailable(true);
			}else {
				LOGGER.warn("Product Stock is zero or Lesser : {}", productRequestDto.getProductStock());
				productsClass.setProductIsAvailable(false);
			}
			productsClass.setProductCreatedDate(LocalDateTime.now());
			productsClassList.add(productsClass);
		}
		
		List<ProductsClass> saveAll = productRepository.saveAll(productsClassList);
		
		List<ProductResponseDto> copyingProductsToProductsList = copyingProductsToProductsList(saveAll);
		LOGGER.debug("Returning from SaveAll : {}", copyingProductsToProductsList);
		return copyingProductsToProductsList;
	}
	
	@Override
	public ProductResponseDto updateIsAvailableAndProductStock(long id, boolean isAvailable, int productStock) {
		LOGGER.debug("UpdateIsAvailableAndProductStock method is called : id -> {}, isAvailable -> {}, productStock -> {}", id, isAvailable, productStock);
		ProductsClass productsClass = productRepository.findById(id).orElseThrow( () -> { 
			LOGGER.error("Exception Occured for ProductIdNotFoundException : {}", id);
			return new ProductIdNotFoundException("Invalid Product Id : "+id);
		});
		productsClass.setProductIsAvailable(isAvailable);
		productsClass.setProductStock(productStock);
		
		ProductsClass save = productRepository.save(productsClass);
		ProductResponseDto productResponseDto = new ProductResponseDto();
		
		BeanUtils.copyProperties(save, productResponseDto);
		LOGGER.debug("Returning from UpdateIsAvailableAndProductStock : {}", productResponseDto);
		return productResponseDto;
	}
	
	

	@Override
	public List<ProductResponseDto> getProductsIsAvailable() {
		LOGGER.debug("GetProductsIsAvailable method called");
		List<ProductsClass> productsList = productRepository.findAll();
		
		List<ProductsClass> isAvailableList = new ArrayList<ProductsClass>();
		
		for(ProductsClass productsClass : productsList) {
			if(productsClass.isProductIsAvailable() == true) {
				isAvailableList.add(productsClass);
			}
		}
		List<ProductResponseDto> copyingProductsToProductsList = copyingProductsToProductsList(isAvailableList);
		LOGGER.debug("Returning from GetProductIsAvailable : {}", copyingProductsToProductsList);
		return copyingProductsToProductsList;
	}
	
	
	@Override
	public List<ProductResponseDto> getRatingGreaterThan(double rating) {
		LOGGER.debug("GetRatingGreaterThan method called : Rating -> {}", rating);
		List<ProductsClass> byRatingGreaterThan = productRepository.findByProductRatingGreaterThan(rating);
		
		List<ProductResponseDto> copyingProductsToProductsList = copyingProductsToProductsList(byRatingGreaterThan);
		LOGGER.debug("Returning from GetRatingGreaterThan : {}", copyingProductsToProductsList);
		return copyingProductsToProductsList;
	}
	

	
	@Override
	public List<ProductResponseDto> productRatingGreaterThanAvailableStock(double rating, boolean isAvailable) {
		LOGGER.debug("ProductRatingGreaterThanAvailableStock method called : Rating -> {}, isAvailable -> {}", rating, isAvailable);
		List<ProductsClass> byProductRatingGreaterThan = productRepository.findByProductRatingGreaterThanEqualAndProductIsAvailableEquals(rating, isAvailable);
		
		List<ProductResponseDto> copyingProductsToProductsList = copyingProductsToProductsList(byProductRatingGreaterThan);
		LOGGER.debug("Returning from ProductRatingGreaterThanAvailableStock : {}", copyingProductsToProductsList);
		return copyingProductsToProductsList;
	}
	
	
	//Copying to ProductResponseDTO For-Each Loop Method
	
	private List<ProductResponseDto> copyingProductsToProductsList(List<ProductsClass> productList) {
		LOGGER.debug("CopyingProductToProductList method called : {}", productList);
		List<ProductResponseDto> productsList = new ArrayList<ProductResponseDto>();
		for(ProductsClass productsClass : productList) {
			ProductResponseDto productsResponseDto = new ProductResponseDto();
			BeanUtils.copyProperties(productsClass, productsResponseDto);
			productsList.add(productsResponseDto);
		}
		LOGGER.debug("Returning from CopyingProductsToProductList");
		return productsList;
	}


	
	
}
