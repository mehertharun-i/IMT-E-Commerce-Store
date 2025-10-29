package com.E_CommerceOrderManagementProject.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.E_CommerceOrderManagementProject.dao.UserClassRepository;
import com.E_CommerceOrderManagementProject.model.Authorities;
import com.E_CommerceOrderManagementProject.model.UserClass;

@Component
public class SecurityUserDetailsFromDB implements UserDetailsService{

	private final UserClassRepository userClassRepository;
	
	public SecurityUserDetailsFromDB(UserClassRepository userClassRepository) {
		this.userClassRepository = userClassRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserClass user = userClassRepository.findByUserLoginId(username);
		
		List<GrantedAuthority> authoritiesList = new ArrayList<>();
		
		List<Authorities> userAuthoritiesList = user.getAuthorities();
		for(Authorities authority : userAuthoritiesList) {
			authoritiesList.add(new SimpleGrantedAuthority("ROLE_"+authority.getAuthorityRole()));
		}
		return User.builder()
					.username(user.getUserLoginId())
					.password(user.getUserPassword())
					.authorities(authoritiesList)
					.build();
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	//Database login Process using DAO Authentication Provider
	

//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//		UserClass userLoginId = userClassRepository.findByUserLoginId(username);
//		
//		return User.builder()
//					.username(userLoginId.getUserLoginId())
//					.password(userLoginId.getUserPassword())
//					.build();
//	}

	
	
}
