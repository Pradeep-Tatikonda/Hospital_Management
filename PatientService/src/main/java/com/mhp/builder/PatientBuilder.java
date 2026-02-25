package com.mhp.builder;

import com.mhp.dto.PatientAddressRequestDto;
import com.mhp.dto.RegisterPatientRequestDto;
import com.mhp.model.Patient;
import com.mhp.model.PatientAddress;


public class PatientBuilder {
		
	public static Patient buildPatientFromPatientRequestDto(RegisterPatientRequestDto registerPatientRequestDto, String patientId) {
		return Patient.builder()
					.patientId(patientId)
					.patientName(registerPatientRequestDto.getPatientName())
					.gender(registerPatientRequestDto.getGender())
					.patientEmail(registerPatientRequestDto.getPatientEmail())
					.patientPhoneNumber(registerPatientRequestDto.getPatientPhoneNumber())
					.dateOfBirth(registerPatientRequestDto.getDateOfBirth())
					.patientAddress(buildPatientAddressFromPatientAddressRequestDto(registerPatientRequestDto.getPatientAddressRequestDto()))
					.build();
	}
	
	private static PatientAddress buildPatientAddressFromPatientAddressRequestDto(PatientAddressRequestDto patientAddressRequestDto) {
		return PatientAddress.builder()
					.landmark(patientAddressRequestDto.getLandmark())
					.city(patientAddressRequestDto.getCity())
					.state(patientAddressRequestDto.getState())
					.pincode(patientAddressRequestDto.getPinCode())
					.country(patientAddressRequestDto.getCountry())
					.build();
					
	}

}
