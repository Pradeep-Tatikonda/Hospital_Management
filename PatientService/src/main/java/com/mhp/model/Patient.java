package com.mhp.model;

import java.time.LocalDate;

import com.mhp.utils.Gender;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "patients")
@Builder
public class Patient {
	
	@Id
	@Column(name = "patient_id", nullable = false, unique = true)
	private String patientId;
	
	private String patientName;
	
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	private String patientEmail;
	
	@Column(name = "patient_phone_number", nullable = false, unique = true)
	private String patientPhoneNumber;
	
	private LocalDate dateOfBirth;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "patient_address_id")
	private PatientAddress patientAddress;

}
