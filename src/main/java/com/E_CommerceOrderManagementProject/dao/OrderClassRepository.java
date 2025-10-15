package com.E_CommerceOrderManagementProject.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.E_CommerceOrderManagementProject.model.OrderClass;

import jakarta.transaction.Transactional;

public interface OrderClassRepository extends JpaRepository<OrderClass, Long> {

	@Modifying
	@Transactional
	@Query("update OrderClass oc set oc.totalSum = :stock where oc.id = :id")
	int updateOrderPriceWhenAProductRemoved(@Param("id")long id, @Param("stock") double totalSum);
	
}
