package com.E_CommerceOrderManagementProject.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	
	@Bean
	public PasswordEncoder passwordEncoder() { 
		return new BCryptPasswordEncoder();
	}
	
	
	@Bean
	public SecurityFilterChain secuirtyFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity
						.authorizeHttpRequests(auth -> auth.requestMatchers("/user/insertuserdetails")
															.permitAll()
															.requestMatchers("/user/**", "/order/**")
															.authenticated()
															.anyRequest()
															.permitAll())
						.csrf(csrf -> csrf.disable())
						.formLogin(Customizer.withDefaults())
						.httpBasic(Customizer.withDefaults())
						.build();
															
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// DataBases Login Process using DAO Authentication Provider 
	
	/*
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
		return httpSecurity
						.authorizeHttpRequests(auth -> auth.requestMatchers("/user/insertuserdetails")
															.permitAll()
															.requestMatchers("/user/**", "/order/**")
															.authenticated()
															.anyRequest()
															.permitAll())
						.csrf(csrf -> csrf.disable())
						.httpBasic(Customizer.withDefaults())
						.formLogin(Customizer.withDefaults())
						.build();
	}
	*/
	
		
		
	
	//IN Memory User Details Store Process 
	
/*	@Bean
	public UserDetailsService userDetailsService() {
		
		String pass1 = passwordEncoder().encode("enduku");
		String pass2 = passwordEncoder().encode("cheppa");
		System.out.println("password 1 -> "+pass1);
		System.out.println("password 2 -> "+pass2);
		
		UserDetails user1 = User.builder()
								.username("meher")
								.password(pass1)
								.build();
		
		UserDetails user2 = User.builder()
								.username("tharun")
								.password(pass2)
								.build();
		
		return new InMemoryUserDetailsManager(user1, user2);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity.authorizeHttpRequests(auth -> auth
													.requestMatchers("/order/**","/user/getuserorderdetails/{id}")
													.authenticated()
													.requestMatchers("/product/**")
													.permitAll()
													.requestMatchers("/user/**")
													.denyAll())
					.formLogin(Customizer.withDefaults())
					.csrf(cs -> cs.disable())
					.httpBasic(Customizer.withDefaults())
					.build();
					
	}
	*/
}
