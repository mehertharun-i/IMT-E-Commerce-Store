package com.E_CommerceOrderManagementProject.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.E_CommerceOrderManagementProject.dto.ProductRequestDto;
import com.E_CommerceOrderManagementProject.dto.ProductResponseDto;
import com.E_CommerceOrderManagementProject.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	private final ProductService productService;
	
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	@PostMapping("/saveproduct")
	public ProductResponseDto saveProduct(@RequestBody ProductRequestDto productRequestDto) {
		ProductResponseDto saveProduct = productService.saveProduct(productRequestDto);
		return saveProduct;
	}
	
	@GetMapping("/getproductbyid")
	public ResponseEntity<ProductResponseDto> getProductById(@RequestParam(name = "id") long id) {
		ResponseEntity<ProductResponseDto> productById = productService.getProductById(id);
		return productById;
	}
	
	@GetMapping("getallproducts")
	public ResponseEntity<List<ProductResponseDto>> getAllProducts(){
		ResponseEntity<List<ProductResponseDto>> allProducts = productService.getAllProducts();
		return allProducts;
	}
	
	@GetMapping("/getpricebetween")
	public List<ProductResponseDto> findProductByPriceBetween(@RequestParam(name = "min") double min, @RequestParam(name = "max") double max){
		List<ProductResponseDto> productPriceBetween = productService.findByProductPriceBetween(min, max);
		return productPriceBetween;
	}
	
	@GetMapping("/name/{productName}")
	public List<ProductResponseDto> findProductsByNameContaining(@PathVariable(name = "productName") String productName){
		List<ProductResponseDto> productsByNameContaining = productService.findProductsByNameContaining(productName);
		return productsByNameContaining;
	}
	
	@PutMapping("/updaterating")
	public ProductResponseDto updateRating(@RequestParam (name = "id") long id, @RequestParam(name = "rating") double rating) {
		ProductResponseDto updateRating = productService.updateRating(id, rating);
		return updateRating;
	}
	
	@DeleteMapping("/deletecolumn")
	public String deleteColumn(@RequestParam (name = "id") long id) {
		String deleteColumn = productService.deleteColumn(id);
		return deleteColumn;
	}
	
	@PostMapping("/saveallproducts")
	public List<ProductResponseDto> saveAll(@RequestBody List<ProductRequestDto> productRequestDto){
		List<ProductResponseDto> saveAll = productService.saveAll(productRequestDto);
		return saveAll;
	}
	
	@PutMapping("/updateisavailableandstock")
	public ProductResponseDto updateIsAvailableAndProductStock(@RequestParam(name = "id") long id, @RequestParam(name="isAvailable") boolean isAvailable, @RequestParam(name = "productStock") int productStock) {
		ProductResponseDto updateIsAvailableAndProductStock = productService.updateIsAvailableAndProductStock(id, isAvailable, productStock);
		return updateIsAvailableAndProductStock;
	}
	
	@GetMapping("/getproductsisavailable")
	public List<ProductResponseDto> getProductsIsAvailable(){
		List<ProductResponseDto> productsIsAvailable = productService.getProductsIsAvailable();
		return productsIsAvailable;
	}
	
	@GetMapping("/getratinggreaterthan")
	public List<ProductResponseDto> getRatingGreaterThan(@RequestParam(name = "rating") double rating){
		List<ProductResponseDto> ratingGreaterThan = productService.getRatingGreaterThan(rating);
		return ratingGreaterThan;
	}
	
	@GetMapping("/getproductswithpricerange")
	public List<ProductResponseDto> getProductsWithPriceRange(@RequestParam(name = "min") double min, @RequestParam(name = "max") double max, @RequestParam(name = "pageNumber") int pageNumber, @RequestParam(name = "size") int size){
		List<ProductResponseDto> productsWithPriceRange = productService.getProductsWithPriceRange(min, max, pageNumber, size);
		return productsWithPriceRange;
	}
	
	@PutMapping("/updateproductisavailablebasedonstock")
	public ProductResponseDto updateProductIsAvailableBasedOnStock(@RequestParam(name = "stockupdate") int stock, @RequestParam(name = "id") long id){
		ProductResponseDto updateProductIsAvailableBaseOnStock = productService.updateProductIsAvailableBaseOnStock(stock, id);
		return updateProductIsAvailableBaseOnStock;
	}
	
	@GetMapping("/productratinggreaterthanandavailablestock")
	public List<ProductResponseDto> productRatingGreaterThanAndAvailableStock(@RequestParam(name = "greaterthan") double rating, @RequestParam(name = "available") boolean isAvailable){
		List<ProductResponseDto> productRatingGreaterThanAvailableStock = productService.productRatingGreaterThanAvailableStock(rating, isAvailable);
		return productRatingGreaterThanAvailableStock;
	}
	
	
}