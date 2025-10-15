package com.E_CommerceOrderManagementProject.service.implementation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
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

	public ProductServiceImplementation(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	
	@Override
	public ProductResponseDto saveProduct(ProductRequestDto productRequestDto) {
		ProductsClass productsClass = new ProductsClass();
		productsClass.setProductName(productRequestDto.getProductName());
		productsClass.setProductPrice(productRequestDto.getProductPrice());
		productsClass.setProductDiscount(productRequestDto.getProductDiscount());
		if(productRequestDto.getProductStock() > 0) {
			productsClass.setProductIsAvailable(true);
		}else {
			productsClass.setProductIsAvailable(false);
		}
		productsClass.setProductRating(0);
		productsClass.setProductStock(productRequestDto.getProductStock());
		productsClass.setProductCreatedDate(LocalDateTime.now());
		
		ProductsClass savedProduct = productRepository.save(productsClass);
		
		ProductResponseDto productResponseDto = new ProductResponseDto();
		
		BeanUtils.copyProperties(savedProduct, productResponseDto);
		
		return productResponseDto;
	}


	@Override
	public ResponseEntity<ProductResponseDto> getProductById(long id) {
		ProductsClass productsClass1 = productRepository.findById(id).orElseThrow(() -> new ProductIdNotFoundException("Invalid Product Id Not Found :" +id));
		ProductResponseDto productResponseDto = new ProductResponseDto();
		BeanUtils.copyProperties(productsClass1, productResponseDto);
		return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
	}
	
	@Override
	public ResponseEntity<List<ProductResponseDto>> getAllProducts(){
		List<ProductsClass> productList = productRepository.findAll();
		
		List<ProductResponseDto> copyingProductsToProductsList = copyingProductsToProductsList(productList);
		return ResponseEntity.status(HttpStatus.OK).body(copyingProductsToProductsList);		
	}

	@Override
	public List<ProductResponseDto> findByProductPriceBetween(double min, double max) {
		List<ProductsClass> productList = productRepository.findByProductPriceBetween(min, max);
				
		List<ProductResponseDto> copyingProductsToProductsList = copyingProductsToProductsList(productList);
		return copyingProductsToProductsList;
	}
	
	@Override
	public List<ProductResponseDto> findProductsByNameContaining(String productName) {
		List<ProductsClass> productList = productRepository.findByProductNameContaining(productName);
		
		List<ProductResponseDto> copyingProductsToProductsList = copyingProductsToProductsList(productList);
		
		return copyingProductsToProductsList;
	}
	
	@Override
	public ProductResponseDto updateRating(long id, double rating) {
		ProductsClass productId = productRepository.findById(id).orElseThrow( () -> new ProductIdNotFoundException("Invalid Product Id : "+id));
		productId.setProductRating(rating);
		ProductsClass save = productRepository.save(productId);
		
		ProductResponseDto productResponseDto = new ProductResponseDto();
		
		BeanUtils.copyProperties(save, productResponseDto);
		
		return productResponseDto;
	}
	
	@Override
	public String deleteColumn(long id) {
		ProductsClass productsClass = productRepository.findById(id).orElseThrow( () -> new ProductIdNotFoundException("Invalid Product Id : "+id));
		
		productRepository.delete(productsClass);
		
		return productsClass.getProductName();
	}
	

	@Override
	public List<ProductResponseDto> saveAll(List<ProductRequestDto> productRequestDto) {
		
		List<ProductsClass> productsClassList = new ArrayList<ProductsClass>();
		
		for(ProductRequestDto pr : productRequestDto) {
			ProductsClass productsClass = new ProductsClass();
			BeanUtils.copyProperties(pr, productsClass);
			if(pr.getProductStock() > 0) {
				productsClass.setProductIsAvailable(true);
			}else {
				productsClass.setProductIsAvailable(false);
			}
			productsClass.setProductCreatedDate(LocalDateTime.now());
			productsClassList.add(productsClass);
		}
		
		List<ProductsClass> saveAll = productRepository.saveAll(productsClassList);
		
		List<ProductResponseDto> copyingProductsToProductsList = copyingProductsToProductsList(saveAll);
		
		return copyingProductsToProductsList;
	}
	
	@Override
	public ProductResponseDto updateIsAvailableAndProductStock(long id, boolean isAvailable, int productStock) {
		ProductsClass productsClass = productRepository.findById(id).orElseThrow( () -> new ProductIdNotFoundException("Invalid Product Id : "+id));;
		productsClass.setProductIsAvailable(isAvailable);
		productsClass.setProductStock(productStock);
		
		ProductsClass save = productRepository.save(productsClass);
		ProductResponseDto productResponseDto = new ProductResponseDto();
		
		BeanUtils.copyProperties(save, productResponseDto);
		
		return productResponseDto;
	}
	
	

	@Override
	public List<ProductResponseDto> getProductsIsAvailable() {
		
		List<ProductsClass> productsList = productRepository.findAll();
		
		List<ProductsClass> isAvailableList = new ArrayList<ProductsClass>();
		
		for(ProductsClass productsClass : productsList) {
			if(productsClass.isProductIsAvailable() == true) {
				isAvailableList.add(productsClass);
			}
		}
		
		List<ProductResponseDto> copyingProductsToProductsList = copyingProductsToProductsList(isAvailableList);
		return copyingProductsToProductsList;
	}
	
	
	@Override
	public List<ProductResponseDto> getRatingGreaterThan(double rating) {
		List<ProductsClass> byRatingGreaterThan = productRepository.findByProductRatingGreaterThan(rating);
		
		List<ProductResponseDto> copyingProductsToProductsList = copyingProductsToProductsList(byRatingGreaterThan);
		
		return copyingProductsToProductsList;
	}
	
	@Override
	public List<ProductResponseDto> getProductsWithPriceRange(double min, double max, int pageNumber, int size) {
		PageRequest request = PageRequest.of(pageNumber, size);
		List<ProductsClass> byProductPriceBetween = productRepository.findByProductPriceBetween(min, max, request);
		
		List<ProductResponseDto> copyingProductsToProductsList = copyingProductsToProductsList(byProductPriceBetween);
		
		return copyingProductsToProductsList;
	}
	
	@Override
	public ProductResponseDto updateProductIsAvailableBaseOnStock(int stock, long id) {
		
		ProductsClass productsClass = productRepository.findById(id).orElseThrow( () -> new ProductIdNotFoundException("Invalid Product Id : "+id));;
		
		productsClass.setProductStock(productsClass.getProductStock() + stock);
		
		if(productsClass.isProductIsAvailable() == false) {
			productsClass.setProductIsAvailable(true);
		}
		
		ProductsClass save = productRepository.save(productsClass);
		
		ProductResponseDto productResponseDto = new ProductResponseDto();
		
		BeanUtils.copyProperties(save, productResponseDto);
		
		return productResponseDto;
	}
	
	
	@Override
	public List<ProductResponseDto> productRatingGreaterThanAvailableStock(double rating, boolean isAvailable) {
		
		List<ProductsClass> byProductRatingGreaterThan = productRepository.findByProductRatingGreaterThanEqualAndProductIsAvailableEquals(rating, isAvailable);
		
		List<ProductResponseDto> copyingProductsToProductsList = copyingProductsToProductsList(byProductRatingGreaterThan);
		
		return copyingProductsToProductsList;
	}
	
	
	//Copying to ProductResponseDTO For-Each Loop Method
	
	private List<ProductResponseDto> copyingProductsToProductsList(List<ProductsClass> productList) {
		List<ProductResponseDto> productsList = new ArrayList<ProductResponseDto>();
		for(ProductsClass productsClass : productList) {
			ProductResponseDto productsResponseDto = new ProductResponseDto();
			BeanUtils.copyProperties(productsClass, productsResponseDto);
			productsList.add(productsResponseDto);
		}
		return productsList;
	}


	
	
}
