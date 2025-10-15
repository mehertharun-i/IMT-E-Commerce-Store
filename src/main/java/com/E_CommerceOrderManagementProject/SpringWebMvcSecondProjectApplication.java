package com.E_CommerceOrderManagementProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.E_CommerceOrderManagementProject.model.UserDetails;

@SpringBootApplication
@EnableConfigurationProperties({UserDetails.class})
public class SpringWebMvcSecondProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringWebMvcSecondProjectApplication.class, args);
	}

}