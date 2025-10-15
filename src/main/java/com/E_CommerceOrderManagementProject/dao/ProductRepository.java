package com.E_CommerceOrderManagementProject.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.E_CommerceOrderManagementProject.model.ProductsClass;

import jakarta.transaction.Transactional;


public interface ProductRepository extends JpaRepository<ProductsClass, Long>{
	
	List<ProductsClass> findByProductPriceBetween(double min, double max);
	
	List<ProductsClass> findByProductNameContaining(String productName);
	
	List<ProductsClass> findByProductRatingGreaterThan(double rating);
	
	List<ProductsClass> findByProductPriceBetween(double min, double max, Pageable page);
	
	List<ProductsClass> findByProductRatingGreaterThanEqualAndProductIsAvailableEquals(double rating, boolean isAvailable);

	@Query("update ProductsClass pc set pc.productStock = :stock where pc.productId = :id")
	@Modifying
	@Transactional
	int updateProductStockDetails(@Param("stock") int stock, @Param("id") long id);
}
