package com.E_CommerceOrderManagementProject.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
	
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	@PostMapping("/saveproduct")
	public ProductResponseDto saveProduct(@RequestBody ProductRequestDto productRequestDto) {
		LOGGER.debug("Entered into SaveProduct method with API Endpoint of (/product/saveproduct) : ({})", productRequestDto);
		ProductResponseDto saveProduct = productService.saveProduct(productRequestDto);
		LOGGER.debug("Exiting from SaveProduct and returning a response as : ({})", saveProduct);
		return saveProduct;
	}
	
	@GetMapping("/getproductbyid")
	public ResponseEntity<ProductResponseDto> getProductById(@RequestParam(name = "id") long id) {
		LOGGER.debug("Entered into getProductById method with API Endpoint of (/getproductbyid) : ({})", id);
		ResponseEntity<ProductResponseDto> productById = productService.getProductById(id);
		LOGGER.debug("Exiting from getProductById and returning a response as : ({})", productById);
		return productById;
	}
	
	@GetMapping("getallproducts")
	public ResponseEntity<List<ProductResponseDto>> getAllProducts(){
		LOGGER.debug("Entered into getAllProducts method with API Endpoint of (/getallproducts)");
		ResponseEntity<List<ProductResponseDto>> allProducts = productService.getAllProducts();
		LOGGER.debug("Exiting from getAllProducts and returning a response as : ({})", allProducts);
		return allProducts;
	}
	
	@GetMapping("/getproductbypricebetween")
	public List<ProductResponseDto> findProductByPriceBetween(@RequestParam(name = "min") double min, @RequestParam(name = "max") double max){
		LOGGER.debug("Entered into findProductByPriceBetween method with API Endpoint of (/getpricebetween) : min -> ({}) , max -> ({})", min, max);
		List<ProductResponseDto> productPriceBetween = productService.findByProductPriceBetween(min, max);
		LOGGER.debug("Exiting from findProductByPriceBetween and returning a response as : ({})", productPriceBetween);
		return productPriceBetween;
	}
	
	@GetMapping("/name/{productName}")
	public List<ProductResponseDto> findProductsByNameContaining(@PathVariable(name = "productName") String productName){
		LOGGER.debug("Entered into findProductByNameContaining method with API Endpoint of (/orderproduct/{productName}) : ({})", productName);
		List<ProductResponseDto> productsByNameContaining = productService.findProductsByNameContaining(productName);
		LOGGER.debug("Exiting from findProductsByNameContaining and returning a response as : ({})", productsByNameContaining);
		return productsByNameContaining;
	}
	
	@PutMapping("/updaterating")
	public ProductResponseDto updateRating(@RequestParam (name = "id") long id, @RequestParam(name = "rating") double rating) {
		LOGGER.debug("Entered into updateRating method with API Endpoint of (/updaterating) : id -> ({}) , rating -> ({})", id, rating);
		ProductResponseDto updateRating = productService.updateRating(id, rating);
		LOGGER.debug("Exiting from updateRating and returning a response as : ({})", updateRating);
		return updateRating;
	}
	
	@DeleteMapping("/deleteproductById")
	public String deleteProductById(@RequestParam (name = "id") long id) {
		LOGGER.debug("Entered into deleteColumn method with API Endpoint of (/deletecolumn) : ({})", id);
		String deleteColumn = productService.deleteProductById(id);
		LOGGER.debug("Exiting from deleteColumn and returning a response as : ({})", deleteColumn);
		return deleteColumn;
	}
	
	@PostMapping("/saveallproducts")
	public List<ProductResponseDto> saveAll(@RequestBody List<ProductRequestDto> productRequestDto){
		LOGGER.debug("Entered into saveall method with API Endpoint of (/saveallproducts) : ({})", productRequestDto);
		List<ProductResponseDto> saveAll = productService.saveAll(productRequestDto);
		LOGGER.debug("Exiting from saveAll and returning a response as : ({})", saveAll);
		return saveAll;
	}
	
	@PutMapping("/updateisavailableandstock")
	public ProductResponseDto updateIsAvailableAndProductStock(@RequestParam(name = "id") long id, @RequestParam(name="isAvailable") boolean isAvailable, @RequestParam(name = "productStock") int productStock) {
		LOGGER.debug("Entered into OrderProduct method with API Endpoint of (/orderproduct) : Id -> ({}), isAvailable -> ({}), productStock -> ({})", id, isAvailable, productStock);
		ProductResponseDto updateIsAvailableAndProductStock = productService.updateIsAvailableAndProductStock(id, isAvailable, productStock);
		LOGGER.debug("Exiting from updateIsAvailableAndProductStock and returning a response as : ({})", updateIsAvailableAndProductStock);
		return updateIsAvailableAndProductStock;
	}
	
	@GetMapping("/getproductsisavailable")
	public List<ProductResponseDto> getProductsIsAvailable(){
		LOGGER.debug("Entered into getProductsIsAvailable method with API Endpoint of (/getproductsisavailable) ");
		List<ProductResponseDto> productsIsAvailable = productService.getProductsIsAvailable();
		LOGGER.debug("Exiting from getProductsIsAvailable and returning a response as : ({})", productsIsAvailable);
		return productsIsAvailable;
	}
	
	@GetMapping("/getratinggreaterthan")
	public List<ProductResponseDto> getRatingGreaterThan(@RequestParam(name = "rating") double rating){
		LOGGER.debug("Entered into getRatingGreaterThan method with API Endpoint of (/getratinggreaterthan) : ({})", rating);
		List<ProductResponseDto> ratingGreaterThan = productService.getRatingGreaterThan(rating);
		LOGGER.debug("Exiting from getRatingGreaterThan and returning a response as : ({})", ratingGreaterThan);
		return ratingGreaterThan;
	}
	
	@GetMapping("/getproductratinggreaterthanandstockavailable")
	public List<ProductResponseDto> productRatingGreaterThanAndAvailableStock(@RequestParam(name = "greaterthan") double rating, @RequestParam(name = "available") boolean isAvailable){
		LOGGER.debug("Entered into productRatingGreaterThanAndAvailableStock method with API Endpoint of (/productratinggreaterthanandavailablestock) : rating -> ({}), isAvailable -> ({})", rating, isAvailable);
		List<ProductResponseDto> productRatingGreaterThanAvailableStock = productService.productRatingGreaterThanAvailableStock(rating, isAvailable);
		LOGGER.debug("Exiting from productRatingGreaterThanAndAvailableStock and returning a response as : ({})", productRatingGreaterThanAvailableStock);
		return productRatingGreaterThanAvailableStock;
	}
	
	
}