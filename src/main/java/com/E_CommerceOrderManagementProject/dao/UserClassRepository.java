package com.E_CommerceOrderManagementProject.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.E_CommerceOrderManagementProject.model.UserClass;

public interface UserClassRepository extends JpaRepository<UserClass, Long>{
	
	UserClass findByUserLoginId(String userLoginId);

}
