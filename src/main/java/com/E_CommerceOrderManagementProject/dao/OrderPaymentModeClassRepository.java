package com.E_CommerceOrderManagementProject.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.E_CommerceOrderManagementProject.model.OrderPaymentModeClass;

import jakarta.transaction.Transactional;

public interface OrderPaymentModeClassRepository extends JpaRepository<OrderPaymentModeClass, Long>{

	@Modifying
	@Transactional
	@Query("update OrderPaymentModeClass opmc set opmc.orderPaymentAmount = :amount , opmc.paymentDateAndTime = CURRENT_TIMESTAMP where opmc.orderPaymentId = :id")
	int updateOrderPaymentModeClassPriceWhenAProductRemoved(@Param("id") long orderPaymentId, @Param("amount")double remainingTotalSum);

}
