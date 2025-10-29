package com.E_CommerceOrderManagementProject.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Authority")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Authorities {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long authorityId;
	
	@Column(unique = true, nullable = false)
	private String authorityRole;
	
	
	
}
