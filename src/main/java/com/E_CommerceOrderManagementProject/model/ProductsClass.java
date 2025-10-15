package com.E_CommerceOrderManagementProject.model;

import java.time.LocalDateTime;
import java.util.List;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Product")
public class ProductsClass {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long productId;
	
	@Column(nullable = false, unique = true)
	private String productName;
	
	@Column(nullable = false)
	private double productPrice;
	
	@Column(nullable = false)
	private double productDiscount;
	
	private double productRating;
	
	@Column(nullable = false)
	private int productStock;
	
	@Column(nullable = false)
	private boolean productIsAvailable;
	
	@Column(nullable = false)
	private LocalDateTime productCreatedDate;
	
	@OneToMany(mappedBy = "products", cascade = CascadeType.ALL)
	private List<OrderItemsClass> orderItemsClass;

	public ProductsClass(String productName, double productPrice, double productDiscount, double productRating,
			int productStock, boolean productIsAvailable, LocalDateTime productCreatedDate,
			List<OrderItemsClass> orderItemsClass) {
		super();
		this.productName = productName;
		this.productPrice = productPrice;
		this.productDiscount = productDiscount;
		this.productRating = productRating;
		this.productStock = productStock;
		this.productIsAvailable = productIsAvailable;
		this.productCreatedDate = productCreatedDate;
		this.orderItemsClass = orderItemsClass;
	}
	
	
	

}
