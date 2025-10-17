package com.E_CommerceOrderManagementProject.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderClass {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long orderId;
	
	@Column(nullable = false)
	private LocalDateTime orderedDateAndTime; 
	
	@Column(nullable = false)
	private String orderStatus;
	
	@Column(nullable = false)
	@OneToMany(mappedBy = "orderClass", cascade = CascadeType.ALL, orphanRemoval = true)
	@ToString.Exclude
	private List<OrderItemsClass> orderItemsClass;
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "orderClass") //mappedBy 
	@JoinColumn(name = "orderPaymentMode_id", nullable = false)
	@ToString.Exclude
	private OrderPaymentModeClass orderPaymentMode;
	
	@ManyToOne
	@JoinColumn(name = "Address_id")
	@ToString.Exclude
	private AddressClass addressClass;
	
	@Column(nullable = false)
	private double totalSum;
	
	@ManyToOne
	@JoinColumn(name = "userDetails_id")
	@ToString.Exclude
	private UserClass userDetails;
	

	public OrderClass(String orderStatus, List<OrderItemsClass> orderItemsClass, OrderPaymentModeClass orderPaymentMode,
			AddressClass addressClass, double totalSum, UserClass userDetails) {
		super();
		this.orderStatus = orderStatus;
		this.orderItemsClass = orderItemsClass;
		this.orderPaymentMode = orderPaymentMode;
		this.addressClass = addressClass;
		this.totalSum = totalSum;
		this.userDetails = userDetails;
	}
	
	
}
