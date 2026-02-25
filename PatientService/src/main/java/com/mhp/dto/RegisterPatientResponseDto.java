package com.mhp.dto;

import java.time.LocalDate;

import com.mhp.utils.Gender;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterPatientResponseDto {
	
	private String patientId;
	
	private String patientName;
	
	private Gender gender;
	
	private String patientEmail;
	
	private String patientPhoneNumber;
	
	private LocalDate dateOfBirth;

	private PatientAddressResponseDto patientAddress;

}
