package com.E_CommerceOrderManagementProject.model;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemsClass {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long orderItemId;
	
	@Column(nullable = false)
	private int quantity;
	
	@ManyToOne
	@JoinColumn(name = "orderClass_id", nullable = false )
	private OrderClass orderClass;
	
	@ManyToOne
	@JoinColumn(name = "productsClass_id", nullable = false)
	private ProductsClass products;

	public OrderItemsClass(int quantity, OrderClass orderClass, ProductsClass products) {
		super();
		this.quantity = quantity;
		this.orderClass = orderClass;
		this.products = products;
	}
	
}
