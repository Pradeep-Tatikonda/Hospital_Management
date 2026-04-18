package com.mhp.service.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import com.mhp.dao.PatientRepository;
import com.mhp.dto.PatientAddressRequestDto;
import com.mhp.dto.RegisterPatientRequestDto;
import com.mhp.dto.RegisterPatientResponseDto;
import com.mhp.exception.PatientNotFoundException;
import com.mhp.model.Patient;
import com.mhp.model.PatientAddress;
import com.mhp.utils.PatientIdGenerator;

@ExtendWith(MockitoExtension.class)
class PatientServiceImplTest {
	
	@Mock
	private PatientRepository patientRepository;
	
	@Mock
	private PatientIdGenerator patientIdGenerator;
	
	@InjectMocks
	private PatientServiceImpl patientService;
	
	private RegisterPatientRequestDto requestDto;
	
	private Patient savedPatient;
	
	private PatientAddressRequestDto patientAddressRequestDto;
	
	private PatientAddress patientAddress;
	
	
	@BeforeEach
	void setup() {
		requestDto = new RegisterPatientRequestDto();
		requestDto.setPatientPhoneNumber("9999999999");
		
		savedPatient = new Patient();
		savedPatient.setPatientPhoneNumber("9999999999");
		patientAddress = new PatientAddress();
		patientAddress.setCountry("inida");
		savedPatient.setPatientAddress(patientAddress);
		patientAddressRequestDto = new PatientAddressRequestDto();
		requestDto.setPatientAddressRequestDto(patientAddressRequestDto);
		
		
	}
	
    // ✅ 1. Success case
    @Test
    void registerPatient_shouldSaveAndReturnResponse_whenNewPatient() {

        when(patientIdGenerator.generateNextPatientId())
                .thenReturn("P123");

        when(patientRepository.save(any(Patient.class)))
                .thenReturn(savedPatient);

        RegisterPatientResponseDto response =
                patientService.registerPatient(requestDto);

        assertNotNull(response);

        verify(patientRepository, times(1)).save(any(Patient.class));
        verify(patientIdGenerator, times(1)).generateNextPatientId();
    }

    // ✅ 2. Duplicate case (exception flow)
    @Test
    void registerPatient_shouldReturnExistingPatient_whenDuplicate() {

        when(patientIdGenerator.generateNextPatientId())
                .thenReturn("P123");

        when(patientRepository.save(any(Patient.class)))
                .thenThrow(DataIntegrityViolationException.class);

        when(patientRepository.findByPatientPhoneNumber("9999999999"))
                .thenReturn(Optional.of(savedPatient));

        RegisterPatientResponseDto response =
                patientService.registerPatient(requestDto);

        assertNotNull(response);

        verify(patientRepository).findByPatientPhoneNumber("9999999999");
    }

    // ❌ 3. Duplicate but not found (edge case)
    @Test
    void registerPatient_shouldThrowException_whenDuplicateButNotFound() {

        when(patientIdGenerator.generateNextPatientId())
                .thenReturn("P123");

        when(patientRepository.save(any(Patient.class)))
                .thenThrow(DataIntegrityViolationException.class);

        when(patientRepository.findByPatientPhoneNumber("9999999999"))
                .thenReturn(Optional.empty());

        assertThrows(IllegalStateException.class, () ->
                patientService.registerPatient(requestDto));
    }

    // ✅ 4. Get patient success
    @Test
    void getPatientByPhoneNumber_shouldReturnPatient() {

        when(patientRepository.findByPatientPhoneNumber("9999999999"))
                .thenReturn(Optional.of(savedPatient));

        RegisterPatientResponseDto response =
                patientService.getPatientByPhoneNumber("9999999999");

        assertNotNull(response);
    }

    // ❌ 5. Patient not found
    @Test
    void getPatientByPhoneNumber_shouldThrowException_whenNotFound() {

        when(patientRepository.findByPatientPhoneNumber("9999999999"))
                .thenReturn(Optional.empty());

        assertThrows(PatientNotFoundException.class, () ->
                patientService.getPatientByPhoneNumber("9999999999"));
    }

}
