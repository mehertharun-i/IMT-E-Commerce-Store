package com.E_CommerceOrderManagementProject.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.E_CommerceOrderManagementProject.dao.UserClassRepository;
import com.E_CommerceOrderManagementProject.model.UserClass;

@Component
public class SecurityUserDetailsFromDB implements UserDetailsService{

	private final UserClassRepository userClassRepository;
	
	public SecurityUserDetailsFromDB(UserClassRepository userClassRepository) {
		this.userClassRepository = userClassRepository;
	}

//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		
////		UserClass user = userClassRepository.findByUserLoginId(username);
//
//			
//		
//		return null;
//	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	//Database login Process using DAO Authentication Provider
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserClass userLoginId = userClassRepository.findByUserLoginId(username);
		
		return User.builder()
					.username(userLoginId.getUserLoginId())
					.password(userLoginId.getUserPassword())
					.build();
	}

	
	
}
