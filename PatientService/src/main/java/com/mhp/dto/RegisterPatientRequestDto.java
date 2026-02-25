package com.mhp.dto;

import java.time.LocalDate;

import com.mhp.utils.Gender;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterPatientRequestDto {
	
	private String patientName;
	
	private Gender gender; 		
	
	private String patientEmail;
	
	private String patientPhoneNumber;
	
	private LocalDate dateOfBirth;

	private PatientAddressRequestDto patientAddressRequestDto;

}
