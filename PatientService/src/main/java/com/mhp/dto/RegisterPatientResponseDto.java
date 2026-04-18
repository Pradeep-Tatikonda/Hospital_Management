package com.mhp.dto;

import java.time.LocalDate;

import com.mhp.utils.Gender;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
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
	
	@NotEmpty
	private String patientName;
	
	@NotNull(message = "Please select Gender")
	private Gender gender;
	
	@Email
	private String patientEmail;
	
	@Size(min = 10, max = 10, message = "Please enter 10 dit phone number")
	private String patientPhoneNumber;
	
	@NotEmpty
	@PastOrPresent(message = "DOB shouldn't be future dates")
	private LocalDate dateOfBirth;

	private PatientAddressResponseDto patientAddress;

}
