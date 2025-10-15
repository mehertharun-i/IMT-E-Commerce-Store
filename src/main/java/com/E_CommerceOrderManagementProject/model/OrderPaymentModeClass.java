package com.E_CommerceOrderManagementProject.model;

import java.time.LocalDateTime;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderPaymentModeClass {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long orderPaymentId;

	@ManyToOne
	@JoinColumn(name = "orderPaymentModeTypeClass.id", nullable = false)
	private OrderPaymentModeTypeClass orderPaymentModeTypeClass;
	
	@Column(nullable = false)
	private double orderPaymentAmount;
	
	
	@Column(nullable = false)
	private LocalDateTime paymentDateAndTime;
	
	@Column(nullable = false)
	private String status;
	
	@JoinColumn(name = "orderClass_id", nullable = false)
	@OneToOne(cascade = CascadeType.ALL)   // can orderPaymentModeClass Deleted then the Order Class should not be deleted?
	private OrderClass orderClass;

	public OrderPaymentModeClass(OrderPaymentModeTypeClass orderPaymentModeTypeClass, LocalDateTime paymentDateAndTime,
			String status, OrderClass orderClass) {
		super();
		this.orderPaymentModeTypeClass = orderPaymentModeTypeClass;
		this.paymentDateAndTime = paymentDateAndTime;
		this.status = status;
		this.orderClass = orderClass;
	}
	

}
