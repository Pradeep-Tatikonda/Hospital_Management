package com.mhp.dto.builder;

import org.springframework.beans.BeanUtils;

import com.mhp.dto.PatientAddressResponseDto;
import com.mhp.dto.RegisterPatientResponseDto;
import com.mhp.model.Patient;
import com.mhp.model.PatientAddress;

public class PatientResponseDtoBuilder {
	
	public static RegisterPatientResponseDto buildPatientResponseDtoFromPatient(Patient patient) {
		return RegisterPatientResponseDto.builder()
					.patientId(patient.getPatientId())
					.patientName(patient.getPatientName())
					.patientPhoneNumber(patient.getPatientPhoneNumber())
					.patientEmail(patient.getPatientEmail())
					.gender(patient.getGender())
					.dateOfBirth(patient.getDateOfBirth())
					.patientAddress(buildPatientAddressResponseDtoFromPatientAddress(patient.getPatientAddress()))
					.build();
	}
	
	private static PatientAddressResponseDto buildPatientAddressResponseDtoFromPatientAddress(PatientAddress patientAddress) {
		PatientAddressResponseDto patientResponseDto = new PatientAddressResponseDto();
		BeanUtils.copyProperties(patientAddress, patientResponseDto);
		return patientResponseDto;
	}

}
