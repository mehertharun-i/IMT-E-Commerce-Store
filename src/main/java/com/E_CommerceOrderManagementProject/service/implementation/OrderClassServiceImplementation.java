package com.E_CommerceOrderManagementProject.service.implementation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.E_CommerceOrderManagementProject.dao.OrderClassRepository;
import com.E_CommerceOrderManagementProject.dao.OrderItemsClassRepository;
import com.E_CommerceOrderManagementProject.dao.OrderPaymentModeClassRepository;
import com.E_CommerceOrderManagementProject.dao.OrderPaymentModeTypeClassRepository;
import com.E_CommerceOrderManagementProject.dao.ProductRepository;
import com.E_CommerceOrderManagementProject.dao.UserClassRepository;
import com.E_CommerceOrderManagementProject.dto.OrderClassRequestDto;
import com.E_CommerceOrderManagementProject.dto.OrderClassResponseDto;
import com.E_CommerceOrderManagementProject.dto.OrderItemsCLassResponseDto;
import com.E_CommerceOrderManagementProject.dto.OrderItemsClassRequestDto;
import com.E_CommerceOrderManagementProject.exceptions.OrderClassIdNotFoundException;
import com.E_CommerceOrderManagementProject.exceptions.OrderItemIdNotFound;
import com.E_CommerceOrderManagementProject.exceptions.OrderPaymentModeTypeClassIdNotFoundException;
import com.E_CommerceOrderManagementProject.exceptions.ProductIdNotFoundException;
import com.E_CommerceOrderManagementProject.exceptions.ProductQuantityExceededException;
import com.E_CommerceOrderManagementProject.exceptions.UserClassIdNotFoundException;
import com.E_CommerceOrderManagementProject.model.OrderClass;
import com.E_CommerceOrderManagementProject.model.OrderItemsClass;
import com.E_CommerceOrderManagementProject.model.OrderPaymentModeClass;
import com.E_CommerceOrderManagementProject.model.OrderPaymentModeTypeClass;
import com.E_CommerceOrderManagementProject.model.ProductsClass;
import com.E_CommerceOrderManagementProject.model.UserClass;
import com.E_CommerceOrderManagementProject.service.OrderClassService;

@Service
public class OrderClassServiceImplementation implements OrderClassService{

    private final OrderItemsClassRepository orderItemsClassRepository;
	private final OrderClassRepository orderClassRepository;
	private final ProductRepository productRepository;
	private final OrderPaymentModeTypeClassRepository orderPaymentModeTypeClassRepository;
	private final UserClassRepository userClassRepository;
	private final OrderPaymentModeClassRepository orderPaymentModeClassRepository;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderClassServiceImplementation.class);
	
	public OrderClassServiceImplementation(OrderClassRepository orderClassRepository, ProductRepository productRepository, OrderPaymentModeClassRepository orderPaymentModeClassRepository, UserClassRepository userClassRepository, OrderPaymentModeTypeClassRepository orderPaymentModeTypeClassRepository, OrderItemsClassRepository orderItemsClassRepository) {
		LOGGER.debug("OrderClassServiceImplementation class Constructor is called");
		this.orderClassRepository = orderClassRepository;
		this.productRepository = productRepository;
		this.userClassRepository = userClassRepository; 
		this.orderPaymentModeTypeClassRepository = orderPaymentModeTypeClassRepository; 
		this.orderItemsClassRepository = orderItemsClassRepository;
		this.orderPaymentModeClassRepository = orderPaymentModeClassRepository;
		LOGGER.trace("OrderClassServiceImplementation class Constructor has initialized the values");
		LOGGER.debug("Exited from Constructor");
	}

	@Override
	public ResponseEntity<OrderClassResponseDto> orderProduct(OrderClassRequestDto orderClassRequestDto) {
		
		LOGGER.trace("OrderProduct method called : {} ", orderClassRequestDto);
		
		UserClass userClassId = userClassRepository.findById(orderClassRequestDto.getUserClassId()).orElseThrow( () -> {
			LOGGER.error("Exception occurred for UserIdNotFoundException : {} ", orderClassRequestDto.getUserClassId());
			return new UserClassIdNotFoundException("Invalid User Id : "+orderClassRequestDto.getUserClassId());
		});
		OrderPaymentModeTypeClass paymentModeTypeId = orderPaymentModeTypeClassRepository.findById(orderClassRequestDto.getOrderPaymentModeCode()).orElseThrow( () -> {
			LOGGER.error("Exception occured for PaymentModeTypeIdNotFoundException : {} ", orderClassRequestDto.getOrderPaymentModeCode());
			return new OrderPaymentModeTypeClassIdNotFoundException("Invalid Payment Mode Type Code : "+orderClassRequestDto.getOrderPaymentModeCode());
		});
		
		OrderClass orderClass = new OrderClass();		
		List<OrderItemsClass> orderItemsClass = new ArrayList<OrderItemsClass>();
		
		for(OrderItemsClassRequestDto order : orderClassRequestDto.getOrderItemsList()) {
			OrderItemsClass orderItem = new OrderItemsClass();
			ProductsClass productsClass = productRepository.findById(order.getProductId()).orElseThrow( () -> {
				LOGGER.error("Exception occured for ProductIdNotFoundException : {}", order.getProductId());
				return new ProductIdNotFoundException("Invalid Product have Selected By the user : "+order.getProductId());
			});	
				orderItem.setProducts(productsClass);
				if(order.getQuantity() <= productsClass.getProductStock()) {
					orderItem.setQuantity(order.getQuantity());
					productsClass.setProductStock(productsClass.getProductStock() - order.getQuantity());
				}else {
					LOGGER.error("Exception occured for ProductQuantityExceededException : Ordered Quantity -> {} , Available Quantity of Particular Product -> {}", order.getQuantity(), productsClass.getProductStock());
					throw new ProductQuantityExceededException("Selected Quantity is Greater than Available Stock, Please choice the less Quantity : "+order.getQuantity());
				}
				orderItem.setOrderClass(orderClass);
				orderItem.setProducts(productsClass);
				LOGGER.trace("Individual Order Item Details : {}", orderItem);
				orderItemsClass.add(orderItem);
			}
				LOGGER.trace("Complete Order Item Details : {} ", orderItemsClass);
		OrderPaymentModeClass orderPaymentDetails = new OrderPaymentModeClass();
		orderPaymentDetails.setOrderPaymentModeTypeClass(paymentModeTypeId);
		
		double totalProductsPrice = 0;
		for(OrderItemsClass orderItems : orderItemsClass) {
			double eachPrice = 0;
			int quantity = orderItems.getQuantity();
			double productPrice = orderItems.getProducts().getProductPrice();
			double discountValue = orderItems.getProducts().getProductDiscount();
			double discountValuePrice = (productPrice * discountValue)/100;
			double discountProductPrice = productPrice - discountValuePrice;
			eachPrice = discountProductPrice * quantity;
			totalProductsPrice += eachPrice;
		}
		orderPaymentDetails.setOrderPaymentAmount(totalProductsPrice);
		orderPaymentDetails.setPaymentDateAndTime(LocalDateTime.now());
		orderPaymentDetails.setStatus("Payment Success");
		orderPaymentDetails.setOrderClass(orderClass);
		
		LOGGER.trace("OrderPaymentDetails : {}", orderPaymentDetails);
		
		orderClass.setOrderItemsClass(orderItemsClass);	
		orderClass.setOrderedDateAndTime(LocalDateTime.now());
		orderClass.setOrderPaymentMode(orderPaymentDetails);
		orderClass.setUserDetails(userClassId);
		orderClass.setAddressClass(userClassId.getAddressClass().get(orderClassRequestDto.getAddress()));
		orderClass.setOrderStatus("Ordered And Ready for Shipment");
		orderClass.setTotalSum(totalProductsPrice);
		
		LOGGER.trace("Order Class Details : {}", orderClass);
		
		OrderClass savedOrder = orderClassRepository.save(orderClass);
		LOGGER.debug("Order Details store in Database Successfully");
		OrderClassResponseDto orderedClassProducts = buildOrderItemFromOrderClass(savedOrder);
		LOGGER.debug("Returning from OrderProduct Method : {}",orderedClassProducts);
		return ResponseEntity.status(HttpStatus.OK).body(orderedClassProducts);
	}
	
	@Override
	public ResponseEntity<String> deleteSingleOrderItem(long id) {

		LOGGER.debug("DeleteSingleOrderItem method called");
		
		OrderItemsClass deletedItem = orderItemsClassRepository.findById(id).orElseThrow(()-> {
			LOGGER.error("Exception occured at OrderItemIdNotFoundException : {}", id);
			return new OrderItemIdNotFound("Order Item Id is Not Found in the Data Base, Please provide the Valid Details");
		});
		
		orderItemsClassRepository.deleteById(deletedItem.getOrderItemId());
		LOGGER.debug("Deleted the OrderItem from Database : {}", id);
		
		long productId = deletedItem.getProducts().getProductId();
		int stock = deletedItem.getProducts().getProductStock();
		long orderId = deletedItem.getOrderClass().getOrderId();
		double totalSum = deletedItem.getOrderClass().getTotalSum();
		int quantity = deletedItem.getQuantity();
		double eachProductQuantityPrice = 0;
		double discountedProductPrice = discountedCalculation(deletedItem);
		
		eachProductQuantityPrice = discountedProductPrice * quantity;
		
		double totalSumAfterRemovingOrderItemFromOrders = totalSum - eachProductQuantityPrice;
		
		productRepository.updateProductStockDetails((stock + quantity), productId);
		LOGGER.debug("Updated the Product Stock Details when a OrderItem Deleted");
		orderClassRepository.updateOrderPriceWhenAProductRemoved(orderId, totalSumAfterRemovingOrderItemFromOrders);
		LOGGER.debug("Updated the Order Total Price Details when a OrderItem Deleted");
		orderPaymentModeClassRepository.updateOrderPaymentModeClassPriceWhenAProductRemoved(orderId, totalSumAfterRemovingOrderItemFromOrders);
		LOGGER.debug("Updated the Order Payment Price Details when a OrderItem Deleted");
		
		OrderClass orderClass = orderClassRepository.findById(deletedItem.getOrderClass().getOrderId()).orElseThrow(() -> {
			LOGGER.error("Exception Occured for OrderClassIdNotFoundException : {}", deletedItem.getOrderClass().getOrderId());
			return new OrderClassIdNotFoundException("No Order Id With : "+deletedItem.getOrderClass().getOrderId());
		});
		if(orderClass.getOrderItemsClass().size() == 0) {
			orderClassRepository.delete(orderClass);
			LOGGER.debug("Deleted Complete Order due to No Order Items available in that order");
			LOGGER.debug("Returning from DeleteSingleOrderItem method and Deleted Complete Order");
			return ResponseEntity.status(HttpStatus.OK).body("No OrderItems are Available with this orderId, So this Order is Deleted");
		}
		LOGGER.debug("Returning from DeleteSingleOrderItem method");
		return ResponseEntity.noContent().build();
	}

	@Override
	public OrderClassResponseDto getOrderItemsById(long id) {
		
		OrderClass orderClass = orderClassRepository.findById(id).orElseThrow(() -> {
		LOGGER.error("Exception Occured for OrderClassIdNotFoundException : {}", id);
		return new OrderClassIdNotFoundException("Invalid Id : "+id);
		});
		
		OrderClassResponseDto orderItemFromTheOrderClass = buildOrderItemFromOrderClass(orderClass);
		LOGGER.debug("Returning from GetOrderItemsById : {}", orderItemFromTheOrderClass);
		return orderItemFromTheOrderClass;
	}

	
	
	// Helpers Methods
	
	// Helper Method for the OrderClassResponseDTO for Output Display to the FrontEnd 
	
	private OrderClassResponseDto buildOrderItemFromOrderClass(OrderClass savedOrder) {
		LOGGER.debug("BuildOrderItemFromOrderClass Called");
		LOGGER.trace("Order Details : {}", savedOrder);
		OrderClassResponseDto orderClassResponseDto = new OrderClassResponseDto();
		
		orderClassResponseDto.setStatus(savedOrder.getOrderStatus());
		orderClassResponseDto.setTotalPrice(savedOrder.getTotalSum());
		orderClassResponseDto.setPaymentStatus(savedOrder.getOrderPaymentMode().getStatus());
		orderClassResponseDto.setPaymentMode(savedOrder.getOrderPaymentMode().getOrderPaymentModeTypeClass().getOrderPaymentModeTypeName());
		orderClassResponseDto.setPaymentDateAndTime(LocalDateTime.now());
		
		List<OrderItemsCLassResponseDto> orderItemsClassResponseDtoList = new ArrayList<OrderItemsCLassResponseDto>();
		for(OrderItemsClass orderItemsClasses : savedOrder.getOrderItemsClass()) {
			LOGGER.trace("Order Item Details : {}", savedOrder.getOrderItemsClass());
			OrderItemsCLassResponseDto orderItemsClassResponseDto = new OrderItemsCLassResponseDto();
			
			orderItemsClassResponseDto.setProductName(orderItemsClasses.getProducts().getProductName());
			orderItemsClassResponseDto.setQuantity(orderItemsClasses.getQuantity());
			
			double eachProductQuantityPrice = 0;
			int quantity = orderItemsClasses.getQuantity();
			
			double discountedProductValue = discountedCalculation(orderItemsClasses);
	
			eachProductQuantityPrice = discountedProductValue * quantity;
			
			orderItemsClassResponseDto.setProductPrice(discountedProductValue);
			orderItemsClassResponseDto.setTotalQuantityProductPrice(eachProductQuantityPrice);
			orderItemsClassResponseDtoList.add(orderItemsClassResponseDto);
		}
		LOGGER.trace("Order Item Details Added to OrderItemClassResponseDto : {}", orderItemsClassResponseDtoList);
		orderClassResponseDto.setOrderItems(orderItemsClassResponseDtoList);
		orderClassResponseDto.setOrderId(savedOrder.getOrderId());
		LOGGER.trace("Ordered Details added to OrderClassResponseDto : {}", orderClassResponseDto);
		return orderClassResponseDto;
	}

	// Helper Method for the Calculating the Discount Value for the Actual Price of the Price
	
	public double discountedCalculation(OrderItemsClass orderItemsClass) {
		LOGGER.debug("Product Discount Calculation Method called");
		double productPrice = orderItemsClass.getProducts().getProductPrice();
		double discountValue = orderItemsClass.getProducts().getProductDiscount();
		double discountValuePrice = (productPrice * discountValue)/100;
		double discountedProductValue = productPrice - discountValuePrice;
		
		LOGGER.debug("Returning from Product Discount Calculation : {}", discountedProductValue);
		return discountedProductValue;
	}
	
}
