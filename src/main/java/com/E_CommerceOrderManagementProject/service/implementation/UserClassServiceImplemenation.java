package com.E_CommerceOrderManagementProject.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.E_CommerceOrderManagementProject.dao.AddressClassRepository;
import com.E_CommerceOrderManagementProject.dao.UserClassRepository;
import com.E_CommerceOrderManagementProject.dto.AddressClassResponseDto;
import com.E_CommerceOrderManagementProject.dto.OrderClassResponseDto;
import com.E_CommerceOrderManagementProject.dto.OrderItemsCLassResponseDto;
import com.E_CommerceOrderManagementProject.dto.UserClassRequestDto;
import com.E_CommerceOrderManagementProject.dto.UserClassResponseDto;
import com.E_CommerceOrderManagementProject.exceptions.AddressClassIdNotFoundException;
import com.E_CommerceOrderManagementProject.exceptions.UserClassIdNotFoundException;
import com.E_CommerceOrderManagementProject.model.AddressClass;
import com.E_CommerceOrderManagementProject.model.OrderClass;
import com.E_CommerceOrderManagementProject.model.OrderItemsClass;
import com.E_CommerceOrderManagementProject.model.UserClass;
import com.E_CommerceOrderManagementProject.service.UserClassService;

@Service
public class UserClassServiceImplemenation implements UserClassService {

	private final UserClassRepository userClassRepository;
	private final AddressClassRepository addressClassRepository;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserClassServiceImplemenation.class);
	
	public UserClassServiceImplemenation(UserClassRepository userClassRepository, AddressClassRepository addressClassRepository) {
		this.userClassRepository = userClassRepository;
		this.addressClassRepository = addressClassRepository;
	}
	
	@Override
	public ResponseEntity<UserClassResponseDto> addUserDetailsIntoDB(UserClassRequestDto userClassRequestDto) {
		LOGGER.debug("AddUserDetailsIntoDB method called : {}",userClassRequestDto);
		UserClass userClass = new UserClass();
		BeanUtils.copyProperties(userClassRequestDto, userClass);
		userClass.setAddressClass(userClassRequestDto.getAddressClass());
		for (AddressClass address : userClass.getAddressClass()) {
            address.setUserClass(userClass);
        }
		
		UserClass savedDetails = userClassRepository.save(userClass);
		
		UserClassResponseDto userClassResponseDto = new UserClassResponseDto();
		
		BeanUtils.copyProperties(savedDetails, userClassResponseDto);		
		List<AddressClassResponseDto> addressClassResponseDtoList = new ArrayList<AddressClassResponseDto>();
		for(AddressClass address : savedDetails.getAddressClass()) {
			AddressClassResponseDto addressClassResponseDtos  = new AddressClassResponseDto();
			BeanUtils.copyProperties(address,addressClassResponseDtos);
			addressClassResponseDtoList.add(addressClassResponseDtos);
		}
		
		userClassResponseDto.setAddressClass(addressClassResponseDtoList);
		LOGGER.debug("Returning from AddUserDetailsIntoDB : {}", userClassResponseDto);
		return ResponseEntity.status(HttpStatus.OK).body(userClassResponseDto);
	}

	@Override
	public ResponseEntity<Void> deleteUserAddressDetails(int id) {
		LOGGER.debug("DeleteUserDetailsIntoDB method called : {}",id);
		AddressClass userClass = addressClassRepository.findById(id).orElseThrow( () -> {
			LOGGER.error("Exception occured for AddressClassIdNotFoundException : {}", id);
			return new AddressClassIdNotFoundException("invalid Id given By Client : "+id);
		});
		addressClassRepository.deleteById(userClass.getUserAddressId());
		LOGGER.debug("Returning from AddUserDetailsIntoDB : {}", userClass.getUserAddressId());
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<Void> deleteUserDetails(long id) {
		LOGGER.debug("DeleteUserDetails method called : {}",id);
		UserClass userClass = userClassRepository.findById(id).orElseThrow( ()-> { 
			LOGGER.error("Exception occured for DeleteUserDetails : {}", id);
			return new UserClassIdNotFoundException("Invalid user Id given By the Client : "+id);
		});
		
		userClassRepository.deleteById(userClass.getUserId());
		LOGGER.debug("Returning from AddUserDetailsIntoDB : {}", userClass.getUserId());
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<List<UserClassResponseDto>> addAllUserDetailsIntoDB(List<UserClassRequestDto> userClassRequestDto) {
		LOGGER.debug("AddAllUserDetailsIntoDB method called : {}",userClassRequestDto);
		List<UserClass> userClassList = new ArrayList<UserClass>();
		
		for(UserClassRequestDto userDetails : userClassRequestDto) {
			
			UserClass userClass = new UserClass();
			BeanUtils.copyProperties(userDetails, userClass);			
			List<AddressClass> addressClassList = new ArrayList<AddressClass>();
			
			for(AddressClass userAddressDetails : userDetails.getAddressClass()) {
				AddressClass addressClass = new AddressClass();
				BeanUtils.copyProperties(userAddressDetails, addressClass);
				addressClass.setUserClass(userClass);
				addressClassList.add(addressClass);
			}
			userClass.setAddressClass(addressClassList);			
			userClassList.add(userClass);
		}
		
		List<UserClass> saveAllUserDetails = userClassRepository.saveAll(userClassList);
		List<UserClassResponseDto> userClassListResponse = new ArrayList<UserClassResponseDto>();
		
		for(UserClass userDetails : saveAllUserDetails) {
			
			UserClassResponseDto userClassResponseDto = new UserClassResponseDto();
			BeanUtils.copyProperties(userDetails, userClassResponseDto);
			
			List<AddressClassResponseDto> addressClassResponseDtoList = new ArrayList<AddressClassResponseDto>();
			
			for(AddressClass userAddressDetails : userDetails.getAddressClass()) {
				AddressClassResponseDto addressClassResponseDto = new AddressClassResponseDto();
				BeanUtils.copyProperties(userAddressDetails, addressClassResponseDto);
				addressClassResponseDtoList.add(addressClassResponseDto);
			}
			
			userClassResponseDto.setAddressClass(addressClassResponseDtoList);
			userClassListResponse.add(userClassResponseDto);
		}
		LOGGER.debug("Returning from AddAllUserDetailsIntoDB : {}", userClassListResponse);
		return ResponseEntity.status(HttpStatus.OK).body(userClassListResponse);
	}

	@Override
	public ResponseEntity<List<OrderClassResponseDto>> userOrderDetails(long id) {
		LOGGER.debug("UserOrderDetails method called : {}",id);
		UserClass userClass = userClassRepository.findById(id).orElseThrow( () -> {
			LOGGER.error("Exception occured for UserClassIdNotFoundException : {}", id);
			return new UserClassIdNotFoundException(" Invalid User Id : "+id);
		});
		
		List<OrderClassResponseDto> userClassOrderDetailsList = new ArrayList<OrderClassResponseDto>();
		for(OrderClass userClassOrderDetails : userClass.getUserOrdersClass()) {
			OrderClassResponseDto orderDetails = new OrderClassResponseDto();
			orderDetails.setOrderId(userClassOrderDetails.getOrderId());
			orderDetails.setPaymentDateAndTime(userClassOrderDetails.getOrderPaymentMode().getPaymentDateAndTime());
			orderDetails.setPaymentMode(userClassOrderDetails.getOrderPaymentMode().getOrderPaymentModeTypeClass().getOrderPaymentModeTypeName());
			orderDetails.setPaymentStatus(userClassOrderDetails.getOrderPaymentMode().getStatus());
			orderDetails.setTotalPrice(userClassOrderDetails.getTotalSum());
			orderDetails.setStatus(userClassOrderDetails.getOrderStatus());
//			orderDetails.setOrderItems(userClassOrderDetails.getOrderItemsClass());
			List<OrderItemsCLassResponseDto> orderItemsList = new ArrayList<OrderItemsCLassResponseDto>();
			
			for(OrderItemsClass orderItem : userClassOrderDetails.getOrderItemsClass()) {
				OrderItemsCLassResponseDto orderItems = new OrderItemsCLassResponseDto();
				orderItems.setProductName(orderItem.getProducts().getProductName());
				orderItems.setQuantity(orderItem.getQuantity());
				double discountedProductPrice = (orderItem.getProducts().getProductPrice()) * (100 - orderItem.getProducts().getProductDiscount())/100;
				orderItems.setProductPrice(discountedProductPrice);
				orderItems.setTotalQuantityProductPrice(discountedProductPrice * orderItem.getQuantity());
				orderItemsList.add(orderItems);
			}

			orderDetails.setOrderItems(orderItemsList);
			userClassOrderDetailsList.add(orderDetails);
		}
		LOGGER.debug("Returning from UserOrderDetails : {}", userClassOrderDetailsList);
		return ResponseEntity.status(HttpStatus.OK).body(userClassOrderDetailsList);
	}	
}
