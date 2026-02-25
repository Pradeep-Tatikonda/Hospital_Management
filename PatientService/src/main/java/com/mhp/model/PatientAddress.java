package com.mhp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "patient_address")
@Builder
public class PatientAddress {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long patientAddressId;
	
	private String landmark;
	
	private String city;
	
	private String state;
	
	private String  pincode;
	
	private String country;

}
