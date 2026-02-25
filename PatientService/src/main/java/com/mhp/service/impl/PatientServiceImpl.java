package com.mhp.service.impl;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.mhp.builder.PatientBuilder;
import com.mhp.dao.PatientRepository;
import com.mhp.dto.RegisterPatientRequestDto;
import com.mhp.dto.RegisterPatientResponseDto;
import com.mhp.dto.builder.PatientResponseDtoBuilder;
import com.mhp.model.Patient;
import com.mhp.service.PatientService;
import com.mhp.utils.PatientIdGenerator;

import jakarta.transaction.Transactional;

@Service
public class PatientServiceImpl implements PatientService{
	
	private final PatientRepository patientRepository;
	
	private final PatientIdGenerator patientIdGenerator;
	
	public PatientServiceImpl(PatientRepository patientRepository, PatientIdGenerator patientIdGenerator){
		this.patientRepository = patientRepository;
		
		this.patientIdGenerator = patientIdGenerator;
	}

	@Override
	@Transactional
	public RegisterPatientResponseDto registerPatient(RegisterPatientRequestDto dto) {

	    try {

	        // Build entity (ID generated here if needed)
	        Patient patient = PatientBuilder.buildPatientFromPatientRequestDto(
	                dto,
	                patientIdGenerator.generateNextPatientId()
	        );

	        Patient savedPatient = patientRepository.save(patient);

	        return PatientResponseDtoBuilder
	                .buildPatientResponseDtoFromPatient(savedPatient);

	    } catch (DataIntegrityViolationException ex) {

	        // Fetch existing record after duplicate error
	        Patient existingPatient = patientRepository
	                .findByPatientPhoneNumber(dto.getPatientPhoneNumber())
	                .orElseThrow(() ->
	                        new IllegalStateException("Patient exists but could not be retrieved")
	                );

	        return PatientResponseDtoBuilder
	                .buildPatientResponseDtoFromPatient(existingPatient);
	    }
	}

}
